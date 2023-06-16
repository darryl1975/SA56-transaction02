package sg.edu.nus.iss.springdemo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(name = "User")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Full Name is a mandatory field")
    private String fullName;

    @Size(max = 120)
    @Pattern(regexp = ".+@.+\\..+", message = "Wrong email format")
    @NotBlank(message = "Email is a mandatory field")
    private String email;

    @OneToMany(targetEntity = Role.class)
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Note> notes = new ArrayList<>();

    // public void addNote(Note note) {
    // notes.add(note);
    // note.setUser(this);
    // }

    // public void removeNote(Note note) {
    // notes.remove(note);
    // note.setUser(null);
    // }
}
