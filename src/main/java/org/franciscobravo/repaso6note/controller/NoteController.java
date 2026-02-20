package org.franciscobravo.repaso6note.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.franciscobravo.repaso6note.dto.NoteRequest;
import org.franciscobravo.repaso6note.dto.NoteResponse;
import org.franciscobravo.repaso6note.service.NoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/notes6")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNotes(@RequestParam(required = false)String title) {
        List<NoteResponse> notes = noteService.findByTitle(title);
        return notes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(
            @PathVariable @Positive(message = "El ID no puede ser negativo o cero") Long id) {

        log.debug("Buscando nota con id: {}", id);
        NoteResponse note = noteService.findById(id);  // ← puede lanzar NoteNotFoundException
        return ResponseEntity.ok(note);
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(
            @Valid @RequestBody NoteRequest noteRequest,
            UriComponentsBuilder uriBuilder) {

        log.info("Creando nota - título: '{}'", noteRequest.title()); // pon más campos si puedes
        NoteResponse created = noteService.create(noteRequest);

        URI location = uriBuilder
                .path("/api/v1/notes6/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable @Positive(message = "El ID no puede ser negativo o cero") Long id,
            @Valid @RequestBody NoteRequest noteRequest) {

        log.info("Actualizando nota id: {}", id);
        NoteResponse updated = noteService.update(id, noteRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable @Positive(message = "El ID no puede ser negativo o cero") Long id) {

        log.info("Eliminando nota id: {}", id);
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filterContent")
    public ResponseEntity<List<NoteResponse>>getNotes(@RequestParam(required = false)String content){
        List<NoteResponse> notes = noteService.findByContent(content);

        return ResponseEntity.ok(notes);
    }
}
