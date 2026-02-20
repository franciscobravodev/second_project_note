package org.franciscobravo.repaso6note.service;

import org.franciscobravo.repaso6note.dto.NoteRequest;
import org.franciscobravo.repaso6note.dto.NoteResponse;
import org.franciscobravo.repaso6note.exception.NoteNotFoundException;
import org.franciscobravo.repaso6note.mapper.NoteMapper;
import org.franciscobravo.repaso6note.model.Note;
import org.franciscobravo.repaso6note.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements  NoteService{

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteServiceImpl(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<NoteResponse> findAll() {
        return noteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(noteMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public NoteResponse findById(Long id) {
        return noteRepository.findById(id)
                .map(noteMapper::toResponse)
                .orElseThrow(()-> new NoteNotFoundException(id));
    }

    @Transactional
    @Override
    public NoteResponse create(NoteRequest note) {
        Note save = noteMapper.toEntity(note);
        Note saved = noteRepository.save(save);

        return  noteMapper.toResponse(saved);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(()-> new NoteNotFoundException(id));
        noteRepository.delete(note);
    }

    @Transactional
    @Override
    public NoteResponse update(Long id, NoteRequest note) {
        Note update = noteRepository.findById(id)
                .orElseThrow(()-> new NoteNotFoundException(id));
        noteMapper.updateEntityFromRequest(update,note);
        return  noteMapper.toResponse(update);
    }

    @Override
    public List<NoteResponse> findByTitle(String title) {
        List<Note> notes;
        if (title != null && !title.isBlank()) {
            notes = noteRepository.findByTitleContainingIgnoreCase(title);
        } else {
            notes = noteRepository.findAll();
        }

        return notes.stream()
                .map(noteMapper::toResponse)
                .toList();
    }

    @Transactional
    @Override
    public List<NoteResponse> findByContent(String content) {
        List<Note> notes;
        if (content != null && !content.isBlank()) {
            notes = noteRepository.findByContentContainingIgnoreCase(content);
        }else{
            notes = noteRepository.findAll();
        }
        return  notes.stream()
                .map(noteMapper::toResponse)
                .toList();
    }
}
