package com.cybershield.model;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Table(name="evidence") @Getter @Setter @NoArgsConstructor
public class Evidence { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @ManyToOne @JoinColumn(name="incident_id") Incident incident; String originalName; String storedName; String contentType; Long sizeBytes; @ManyToOne @JoinColumn(name="uploaded_by") User uploadedBy; LocalDateTime uploadedAt=LocalDateTime.now(); }
