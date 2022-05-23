package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.Role;
import com.Netex.Reader.Gui.models.Users;
import com.Netex.Reader.Gui.repository.RoleRepo;
import com.Netex.Reader.Gui.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Collection;
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
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the data base");
        } else {
            log.info("User found in the database: {}", email);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
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
        return userRepo.save(user);
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


}
