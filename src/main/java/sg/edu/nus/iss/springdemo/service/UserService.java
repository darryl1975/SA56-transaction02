package sg.edu.nus.iss.springdemo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.springdemo.exception.ResourceNotFoundException;
import sg.edu.nus.iss.springdemo.model.User;
import sg.edu.nus.iss.springdemo.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getUserList() {

        List<User> users = userRepo.findAll();

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No User Record exists");
        }

        return users;
    }

    public User updateUser(User user, Long userId) {
        User userEnt = userRepo.findById(userId).get();

        if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
            userEnt.setEmail(user.getEmail());
        }
        if (Objects.nonNull(user.getFullName()) && !"".equalsIgnoreCase(user.getFullName())) {
            userEnt.setEmail(user.getFullName());
        }

        return userRepo.save(userEnt);
    }

    public Boolean deleteUserById(Long userId) {
        try {
            userRepo.deleteById(userId);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User findUserById(Long userId) {

        User userFound = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));

        return userFound;
    }

}
