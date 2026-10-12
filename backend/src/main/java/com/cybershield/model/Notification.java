package com.cybershield.model;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Table(name="notifications") @Getter @Setter @NoArgsConstructor
public class Notification { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @ManyToOne @JoinColumn(name="user_id") User user; String title; @Column(length=1000) String message; boolean readFlag=false; LocalDateTime createdAt=LocalDateTime.now(); }
