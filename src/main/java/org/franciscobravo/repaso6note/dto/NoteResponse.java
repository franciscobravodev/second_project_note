package org.franciscobravo.repaso6note.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.franciscobravo.repaso6note.model.Note;

import java.time.LocalDateTime;

/**
 * DTO de respuesta para una nota individual.
 * Contiene todos los datos visibles al cliente (incluyendo version para optimistic locking).
 */
@JsonPropertyOrder({"id", "title", "content", "createdAt", "lastModified", "version"})
public record NoteResponse(

        Long id,

        String title,

        String content,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime lastModified,

        Long version

) {
}
