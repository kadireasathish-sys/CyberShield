package com.cybershield.controller;

import com.cybershield.model.Incident;
import com.cybershield.model.Role;
import com.cybershield.model.User;
import com.cybershield.repository.ActivityLogRepository;
import com.cybershield.repository.IncidentRepository;
import com.cybershield.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final IncidentRepository incidents;
    private final UserRepository users;
    private final ActivityLogRepository logs;

    public DashboardController(IncidentRepository incidents, UserRepository users, ActivityLogRepository logs) {
        this.incidents = incidents;
        this.users = users;
        this.logs = logs;
    }

    @GetMapping
    public Map<String, Object> stats(Authentication authentication) {
        User viewer = users.findByEmail(authentication.getName()).orElseThrow();
        List<Incident> visible = incidents.findAll().stream()
                .filter(incident -> canRead(incident, viewer))
                .toList();

        long open = countByStatus(visible, "OPEN");
        long investigating = countByStatus(visible, "INVESTIGATING");
        long resolved = countByStatus(visible, "RESOLVED");
        long critical = visible.stream().filter(i -> "CRITICAL".equals(i.getSeverity())).count();
        long high = visible.stream().filter(i -> "HIGH".equals(i.getSeverity())).count();
        List<Incident> recent = visible.stream()
                .sorted(Comparator.comparing(Incident::getCreatedAt).reversed())
                .limit(10)
                .toList();

        var response = new java.util.HashMap<String, Object>();
        response.put("total", visible.size());
        response.put("open", open);
        response.put("investigating", investigating);
        response.put("resolved", resolved);
        response.put("critical", critical);
        response.put("high", high);
        response.put("recentIncidents", recent);
        if (viewer.getRole() == Role.ADMIN) {
            response.put("users", users.count());
            response.put("recentActivity", logs.findTop50ByOrderByCreatedAtDesc());
        } else {
            Set<Long> visibleIds = visible.stream().map(Incident::getId).collect(Collectors.toSet());
            response.put("recentActivity", logs.findTop50ByOrderByCreatedAtDesc().stream()
                    .filter(log -> "INCIDENT".equals(log.getEntityType()) && visibleIds.contains(log.getEntityId()))
                    .toList());
        }
        return response;
    }

    private boolean canRead(Incident incident, User viewer) {
        return viewer.getRole() == Role.ADMIN
                || incident.getReporter().getId().equals(viewer.getId())
                || (incident.getAssignee() != null && incident.getAssignee().getId().equals(viewer.getId()));
    }

    private long countByStatus(List<Incident> visible, String status) {
        return visible.stream().filter(i -> status.equals(i.getStatus())).count();
    }
}
