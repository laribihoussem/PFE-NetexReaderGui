package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.Role;
import com.Netex.Reader.Gui.models.Users;
import com.Netex.Reader.Gui.repository.RoleRepo;
import com.Netex.Reader.Gui.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@Slf4j
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepo.findByEmail(email);
        if(user == null|| !user.getIsEnabled()) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the data base");
        } else {
            log.info("User found in the database: {}", email);
            return user;
        }
    }

    @Override
    public Users saveUser(Users user) throws Exception{
        Users temp = userRepo.findByEmail(user.getEmail());
        if(temp==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        }else {
            throw new Exception("There is an account with that email address: " + user.getEmail());
        }
    }
    @Override
    public Users updateUser(Users user){
        Users us1 = userRepo.findById(user.getId());
        us1.setEmail(user.getEmail());
        us1.setFirstName(user.getFirstName());
        us1.setLastName(user.getLastName());
        us1.setPhoneNumber(user.getPhoneNumber());
        return userRepo.save(us1);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public Page<Users> findByName(String name, Pageable pageable) {
        return userRepo.findByName(name,pageable);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        Users user = userRepo.findByEmail(email);
        Role role = roleRepo.findByRoleName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public Users getUser(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<Role> getRoles() {return roleRepo.findAll();}

    @Override
    public List<Users> getUsers() {
        return userRepo.findAll();
    }


    @Override
    public Users enableUser(Long id) {
        Users user=userRepo.findById(id).get();
        user.setIsEnabled(true);
        return userRepo.save(user);
    }

    @Override
    public Users desableUser(Long id) {
        Users user=userRepo.findById(id).get();
        user.setIsEnabled(false);
        return userRepo.save(user);
    }


}
