package com.cybershield.repository; import com.cybershield.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface EvidenceRepository extends JpaRepository<Evidence,Long>{ List<Evidence> findByIncidentIdOrderByUploadedAtDesc(Long id); Optional<Evidence> findByIdAndIncidentId(Long id,Long incidentId); }
