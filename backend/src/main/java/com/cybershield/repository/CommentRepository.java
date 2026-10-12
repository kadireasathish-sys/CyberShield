package com.cybershield.repository; import com.cybershield.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface CommentRepository extends JpaRepository<IncidentComment,Long>{ List<IncidentComment> findByIncidentIdOrderByCreatedAtAsc(Long id); }
