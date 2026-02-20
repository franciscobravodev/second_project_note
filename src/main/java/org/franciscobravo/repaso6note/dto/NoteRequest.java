package org.franciscobravo.repaso6note.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para crear o actualizar una nota (usado en POST /notes y PUT /notes/{id}).
 * Todos los campos son obligatorios en creación y actualización completa.
 */
public record NoteRequest(

        @NotBlank(message = "El título es obligatorio y no puede estar vacío")
        @Size(max = 255, message = "El título no puede exceder los 255 caracteres")
        String title,

        @NotBlank(message = "El contenido es obligatorio y no puede estar vacío")
        @Size(max = 1000, message = "El contenido no puede exceder los 1000 caracteres")
        String content

) {
}