package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private RoleRepository roleRepository;
    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
