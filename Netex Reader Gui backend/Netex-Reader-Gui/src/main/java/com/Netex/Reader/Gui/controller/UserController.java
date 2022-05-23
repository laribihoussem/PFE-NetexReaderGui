package com.Netex.Reader.Gui.controller;

import com.Netex.Reader.Gui.models.Role;
import com.Netex.Reader.Gui.models.Users;
import com.Netex.Reader.Gui.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<Users>>getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/getAll")
    public Page<Users> findByPage(
            @RequestParam Optional<String> name,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy,
            @RequestParam Optional<Integer> size) {
        // Sort by added
        return userService.findByName(name.orElse("_"),
                PageRequest.of(page.orElse(0), size.orElse(5), Sort.Direction.DESC, sortBy.orElse("id")));
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<Users> getUser(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(userService.getUser(email));
    }

    @PostMapping("/user/save")
    public ResponseEntity<Users> saveUser(@RequestBody Users user) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        //return userService.saveUser(user);
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    @PutMapping("/user/update")
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/update").toUriString());
        Users updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>>getRoles() {
        return ResponseEntity.ok().body(userService.getRoles());
    }

}
@Data
class RoleToUserForm {
    private String email;
    private String roleName;
}
