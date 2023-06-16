package sg.edu.nus.iss.springdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.iss.springdemo.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    
}
