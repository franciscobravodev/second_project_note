package org.franciscobravo.repaso6note.mapper;

import org.franciscobravo.repaso6note.dto.NoteRequest;
import org.franciscobravo.repaso6note.dto.NoteResponse;
import org.franciscobravo.repaso6note.model.Note;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class NoteMapper {

    /**
     * Convierte el DTO de entrada (llega del cliente) en una entidad JPA lista para persistir.
     * Solo se copian los campos modificables por el usuario.
     */
    public Note toEntity(NoteRequest request) {
//        if (request == null) {
//            return null;
//        }
        Objects.requireNonNull(request, "note no puede ser null");
        return new Note(
                request.title(),
                request.content()
        );
    }

    /**
     * Actualiza una entidad existente con los datos que vienen del cliente (PUT completo).
     * Útil para operaciones de actualización total.
     */
    public void updateEntityFromRequest(Note entity, NoteRequest request) {

        Objects.requireNonNull(entity, "Note entity no puede ser null");
        Objects.requireNonNull(request, "NoteRequest no puede ser null");
//        if (entity == null || request == null) {
//            return;
//        }
        entity.setTitle(request.title());
        entity.setContent(request.content());
    }

    /**
     * Convierte una entidad persistida en el DTO de respuesta para el cliente.
     * Incluye todos los campos de lectura (incluyendo version para optimistic locking).
     */
    public NoteResponse toResponse(Note note) {
        Objects.requireNonNull(note, "Note no puede ser null");
//        if (note == null) {
//            return null;
//        }

        return new NoteResponse(
                note.getId(),
                note.getTitle(),           // ← corregido: title va aquí
                note.getContent(),         // ← corregido: content va aquí
                note.getCreatedAt(),
                note.getLastModified(),    // ← nombre corregido + campo incluido
                note.getVersion()          // ← muy importante para concurrencia
        );
    }
}