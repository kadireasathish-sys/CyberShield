package com.cybershield.repository; import com.cybershield.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface NotificationRepository extends JpaRepository<Notification,Long>{ List<Notification> findTop30ByUserIdOrderByCreatedAtDesc(Long id); long countByUserIdAndReadFlagFalse(Long id); }
