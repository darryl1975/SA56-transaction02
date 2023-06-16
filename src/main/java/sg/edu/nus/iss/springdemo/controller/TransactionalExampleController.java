package sg.edu.nus.iss.springdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.springdemo.model.Note;
import sg.edu.nus.iss.springdemo.model.User;
import sg.edu.nus.iss.springdemo.service.TransactionalExampleService;
import sg.edu.nus.iss.springdemo.service.UserService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionalExampleController {
    @Autowired
    TransactionalExampleService transExampleService;

    @Autowired
    UserService userService;

    @PostMapping("/example01")
    public ResponseEntity<Boolean> example01() throws Exception {

        User newUser = new User();
        newUser.setEmail("darrenkok@ite.edu.sg");
        newUser.setFullName("Darren Kok Wee Beng");

        Note newNote01 = new Note();
        newNote01.setContent("darren new note 01");

        Note newNote02 = new Note();
        newNote02.setContent("darren new note 02");

        List<Note> userNotes = new ArrayList<Note>();
        userNotes.add(newNote01);
        userNotes.add(newNote02);

        Boolean result = transExampleService.addUserWithNotes(newUser, userNotes);

        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @PostMapping("/example02")
    public ResponseEntity<Boolean> example02() {

        Boolean result = false;
        User retrievedUser = userService.findUserById(1L);

        if (retrievedUser != null) {
            Note newNote01 = new Note();
            newNote01.setContent("darren new note 03");
            newNote01.setUser(retrievedUser);

            Note newNote02 = new Note();
            newNote02.setContent("darren new note 04");
            newNote02.setUser(retrievedUser);

            List<Note> userNotes = new ArrayList<Note>();
            userNotes.add(newNote01);
            userNotes.add(newNote02);

            result = transExampleService.batchAddNotes(userNotes);
        }

        if (result) {
            return new ResponseEntity<Boolean>(result, HttpStatus.OK);
        }

        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }
}
