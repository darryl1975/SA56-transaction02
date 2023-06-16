package sg.edu.nus.iss.springdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.springdemo.model.User;
import sg.edu.nus.iss.springdemo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok().body("test API controller");
        // return new ResponseEntity<String>("test API controller", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        // try {
        List<User> users = new ArrayList<User>();
        users = userService.getUserList();

        // if (users.isEmpty()) {
        //     // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //     throw new MyResourceNotFoundException("No user record");

        // }
        return new ResponseEntity<>(users, HttpStatus.OK);
        // } catch (Exception e) {
        // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        // }
    }

    @PostMapping()
    public ResponseEntity<User> saveAnnouncement(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);

            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<User> updateAnnouncement(@RequestBody User user) {
        try {
            User savedUser = userService.updateUser(user, user.getId());

            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long id) {
        try {
            var isRemoved = userService.deleteUserById(id);

            if (!isRemoved) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Can you write the findUserById function?
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userService.findUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
