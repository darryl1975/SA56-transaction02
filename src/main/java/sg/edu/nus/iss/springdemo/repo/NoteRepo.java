package sg.edu.nus.iss.springdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.iss.springdemo.model.Note;

public interface NoteRepo extends JpaRepository<Note, Long> {
    
}
