package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.Role;
import com.Netex.Reader.Gui.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserService {
    Users saveUser(Users user) throws Exception;
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    Users getUser(String email);
    List<Users> getUsers();
    List<Role> getRoles();
    Users updateUser(Users user);
    void deleteUser(Long id);
    public Page<Users> findByName(String name, Pageable pageable);


    Users enableUser(Long id);

    Users desableUser(Long id);
}
