package com.cybershield.controller;

import com.cybershield.model.Incident;
import com.cybershield.model.Role;
import com.cybershield.model.User;
import com.cybershield.repository.IncidentRepository;
import com.cybershield.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final IncidentRepository incidents;
    private final UserRepository users;

    public ReportController(IncidentRepository incidents, UserRepository users) {
        this.incidents = incidents;
        this.users = users;
    }

    @GetMapping(value = "/incidents.csv", produces = "text/csv")
    public ResponseEntity<byte[]> csv(Authentication authentication) {
        User viewer = users.findByEmail(authentication.getName()).orElseThrow();
        StringBuilder csv = new StringBuilder("ID,Title,Type,Severity,Priority,Status,Detected At\n");
        incidents.findAll().stream().filter(incident -> canRead(incident, viewer)).forEach(incident -> append(csv, incident));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cybershield-incidents.csv")
                .body(csv.toString().getBytes(StandardCharsets.UTF_8));
    }

    private boolean canRead(Incident incident, User viewer) {
        return viewer.getRole() == Role.ADMIN
                || incident.getReporter().getId().equals(viewer.getId())
                || (incident.getAssignee() != null && incident.getAssignee().getId().equals(viewer.getId()));
    }

    private void append(StringBuilder csv, Incident incident) {
        csv.append(incident.getId()).append(',')
                .append(quote(incident.getTitle())).append(',')
                .append(quote(incident.getIncidentType())).append(',')
                .append(quote(incident.getSeverity())).append(',')
                .append(quote(incident.getPriority())).append(',')
                .append(quote(incident.getStatus())).append(',')
                .append(quote(String.valueOf(incident.getDetectedAt()))).append('\n');
    }

    private String quote(String value) {
        return "\"" + (value == null ? "" : value.replace("\"", "\"\"")) + "\"";
    }
}
