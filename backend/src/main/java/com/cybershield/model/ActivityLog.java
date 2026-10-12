package com.cybershield.model;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Table(name="activity_logs") @Getter @Setter @NoArgsConstructor
public class ActivityLog { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @ManyToOne @JoinColumn(name="user_id") User user; String action; String entityType; Long entityId; @Column(length=1000) String details; LocalDateTime createdAt=LocalDateTime.now(); }
