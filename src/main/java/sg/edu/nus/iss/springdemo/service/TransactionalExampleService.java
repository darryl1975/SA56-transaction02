package sg.edu.nus.iss.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.transaction.annotation.Isolation;
// import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.springdemo.model.Note;
import sg.edu.nus.iss.springdemo.model.User;
import sg.edu.nus.iss.springdemo.repo.NoteRepo;
import sg.edu.nus.iss.springdemo.repo.UserRepo;

// Propagation level
// REQUIRED  (Default)
// SUPPORTS
// NOT_SUPPORTED
// REQUIRES_NEW
// NEVER
// MANDATORY

// Isolation rules --> ACID (Atomicity, consistency, Isolation, Durability)
// SERIALIZABLE
// REPEATABLE_READ
// READ_COMMITED

@Service
@Transactional
public class TransactionalExampleService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    NoteRepo noteRepo;

    // @Transactional
    public Boolean addUserWithNotes(User user, List<Note> notes) throws Exception {

        // record is saved and created in user table
        User createdUser = userRepo.save(user);

        List<Note> noteListEnt = notes;
        for (Note note : noteListEnt) {
            note.setUser(createdUser);
        }

        // throw new Exception("Simulate error after creating user");
        
        // save all notes (linked to a user) in Note table
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
