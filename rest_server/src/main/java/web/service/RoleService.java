package web.service;

import web.model.Role;
import web.repository.RoleRepository;

import java.util.List;


public interface RoleService {
   Role findByRole(String role);
   List<Role> findAllRoles();
}
