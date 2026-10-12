package com.cybershield.repository; import com.cybershield.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ActivityLogRepository extends JpaRepository<ActivityLog,Long>{ List<ActivityLog> findTop50ByOrderByCreatedAtDesc(); }
