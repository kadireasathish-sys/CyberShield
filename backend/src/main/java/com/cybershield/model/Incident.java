package com.cybershield.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="incidents") @Getter @Setter @NoArgsConstructor
public class Incident {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(nullable=false) private String incidentType;
 @Column(nullable=false) private String severity;
 @Column(nullable=false) private String priority;
 @Column(nullable=false) private String status="OPEN";
 private String affectedSystem;
 private String affectedUsers;
 @Column(nullable=false, columnDefinition="TEXT") private String description;
 @Column(nullable=false) private LocalDateTime detectedAt;
 private LocalDateTime resolvedAt;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="reporter_id", nullable=false) private User reporter;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="assignee_id") private User assignee;
 private LocalDateTime createdAt=LocalDateTime.now();
 private LocalDateTime updatedAt=LocalDateTime.now();
 @PreUpdate void touch(){updatedAt=LocalDateTime.now();}
}
