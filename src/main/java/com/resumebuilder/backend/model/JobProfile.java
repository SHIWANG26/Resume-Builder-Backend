package com.resumebuilder.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_profiles")
public class JobProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_resume_id")
    private Resume baseResume;

    @Column(nullable = false)
    private String targetTitle;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String targetDescription;

    @Column(columnDefinition = "TEXT")
    private String tailoredText;

    private String tailoredFileUrl;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public JobProfile() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Resume getBaseResume() { return baseResume; }
    public void setBaseResume(Resume baseResume) { this.baseResume = baseResume; }
    public String getTargetTitle() { return targetTitle; }
    public void setTargetTitle(String targetTitle) { this.targetTitle = targetTitle; }
    public String getTargetDescription() { return targetDescription; }
    public void setTargetDescription(String targetDescription) { this.targetDescription = targetDescription; }
    public String getTailoredText() { return tailoredText; }
    public void setTailoredText(String tailoredText) { this.tailoredText = tailoredText; }
    public String getTailoredFileUrl() { return tailoredFileUrl; }
    public void setTailoredFileUrl(String tailoredFileUrl) { this.tailoredFileUrl = tailoredFileUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
