package com.cybershield.model;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Table(name="incident_comments") @Getter @Setter @NoArgsConstructor
public class IncidentComment { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @ManyToOne @JoinColumn(name="incident_id") Incident incident; @ManyToOne @JoinColumn(name="author_id") User author; @Column(columnDefinition="TEXT",nullable=false) String comment; LocalDateTime createdAt=LocalDateTime.now(); }
