package sg.edu.nus.iss.springdemo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.springdemo.model.Role;
import sg.edu.nus.iss.springdemo.repo.RoleRepo;

@Service
public class RoleService {

    @Autowired
    RoleRepo roleRepo;

    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    public List<Role> getRoleList() {
        return roleRepo.findAll();
    }

    public Role updateRole(Role role, Long roleId) {
        Role roleEnt = roleRepo.findById(roleId).get();

        if (Objects.nonNull(role.getDescription()) && !"".equalsIgnoreCase(role.getDescription())) {
            roleEnt.setDescription(role.getDescription());
        }
        if (Objects.nonNull(role.getRoleName()) && !"".equalsIgnoreCase(role.getRoleName())) {
            roleEnt.setRoleName(role.getRoleName());
        }

        return roleRepo.save(roleEnt);
    }

    public Boolean deleteRoleById(Long roleId) {
        try {
            roleRepo.deleteById(roleId);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
