package sg.edu.nus.iss.springdemo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.springdemo.model.Note;
import sg.edu.nus.iss.springdemo.repo.NoteRepo;

@Service
public class NoteService {
    @Autowired
    NoteRepo noteRepo;

    public Note saveNote(Note note) {
        return noteRepo.save(note);
    }

    public List<Note> getNoteList() {
        return noteRepo.findAll();
    }

    public Note updateNote(Note note, Long noteId) {
        Note noteEnt = noteRepo.findById(noteId).get();

        if (Objects.nonNull(note.getContent()) && !"".equalsIgnoreCase(note.getContent())) {
            noteEnt.setContent(note.getContent());
        }

        return noteRepo.save(noteEnt);
    }

    public Boolean deleteNoteById(Long noteId) {
        try {
            noteRepo.deleteById(noteId);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
