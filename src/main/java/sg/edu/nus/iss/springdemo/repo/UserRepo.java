package sg.edu.nus.iss.springdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.iss.springdemo.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    
}
