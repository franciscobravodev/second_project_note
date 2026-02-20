package org.franciscobravo.repaso6note.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que representa una nota en la base de datos.
 * Usa timestamps automáticos y control de concurrencia optimista (@Version).
 */
@Entity
@Table(name = "notes6")
@JsonPropertyOrder({"id", "title", "content", "createdAt", "lastModified", "version"})
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_modified", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastModified;

    @Version
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long version;

    // Constructor vacío necesario para JPA/Hibernate
    protected Note() {
    }

    // Constructor útil para crear notas nuevas
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public Long getVersion() {
        return version;
    }

    // Setters (solo para los campos modificables por el usuario)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        String contentPreview = content != null
                ? content.substring(0, Math.min(50, content.length())) + (content.length() > 50 ? "..." : "")
                : null;

        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + contentPreview + '\'' +
                ", createdAt=" + createdAt +
                ", lastModified=" + lastModified +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        // Dos entidades son iguales si tienen el mismo ID (y no es null)
        return getId() != null && getId().equals(note.getId());
    }

    @Override
    public int hashCode() {
        // Implementación segura cuando se usa igualdad basada en ID
        return getClass().hashCode();
    }
}