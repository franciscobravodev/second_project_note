package org.franciscobravo.repaso6note.exception;

public class NoteNotFoundException extends  RuntimeException{

    public NoteNotFoundException(Long id){
        super("Nota no encontrada con id " + id);
    }
}
