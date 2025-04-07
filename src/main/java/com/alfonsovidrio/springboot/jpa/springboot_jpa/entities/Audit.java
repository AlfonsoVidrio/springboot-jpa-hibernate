package com.alfonsovidrio.springboot.jpa.springboot_jpa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Embeddable
public class Audit {

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        System.out.println("entity pre-persist lifecycle event");
        this.createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void PreUpdate() {
        System.out.println("entity pre-update lifecycle event");
        this.updatedAt = LocalDateTime.now();
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
