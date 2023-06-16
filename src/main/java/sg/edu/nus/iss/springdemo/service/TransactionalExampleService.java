package sg.edu.nus.iss.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.springdemo.model.Note;
import sg.edu.nus.iss.springdemo.model.User;
import sg.edu.nus.iss.springdemo.repo.NoteRepo;
import sg.edu.nus.iss.springdemo.repo.UserRepo;

@Service
public class TransactionalExampleService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    NoteRepo noteRepo;

    @Transactional
    public Boolean addUserWithNotes(User user, List<Note> notes) throws Exception {

        User createdUser = userRepo.save(user);

        List<Note> noteListEnt = notes;
        for (Note note : noteListEnt) {
            note.setUser(createdUser);
        }

        // throw new Exception("Simulate error after creating user");
        
        List<Note> savedNoteList = noteRepo.saveAll(noteListEnt);

        // throw new Exception("Simulate error after creating user notes");

        if (savedNoteList.size() > 0) {
            return true;
        }

        return false;
    }

    @Transactional
    public Boolean batchAddNotes(List<Note> notes) {

        List<Note> savedNoteList = noteRepo.saveAll(notes);

        // throw new Exception("Simulate error after creating user notes");
        
        if (savedNoteList.size() > 0) {
            return true;
        }

        return false;
    }
}
