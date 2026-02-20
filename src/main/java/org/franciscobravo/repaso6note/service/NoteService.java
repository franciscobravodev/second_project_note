package org.franciscobravo.repaso6note.service;

import org.franciscobravo.repaso6note.dto.NoteRequest;
import org.franciscobravo.repaso6note.dto.NoteResponse;
import org.franciscobravo.repaso6note.model.Note;

import java.util.List;

public interface NoteService {
    List<NoteResponse>findAll();
    NoteResponse findById(Long id);
    NoteResponse create (NoteRequest note);
    void delete (Long id);
    NoteResponse update(Long id, NoteRequest note);
    List<NoteResponse> findByTitle(String title);
    List<NoteResponse> findByContent(String content);
}
