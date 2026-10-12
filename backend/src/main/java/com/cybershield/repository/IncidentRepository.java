package com.cybershield.repository; import com.cybershield.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface IncidentRepository extends JpaRepository<Incident,Long>{ long countByStatus(String status); long countBySeverity(String severity); List<Incident> findTop10ByOrderByCreatedAtDesc(); }
