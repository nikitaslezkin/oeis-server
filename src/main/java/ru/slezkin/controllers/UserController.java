package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.Permission;
import ru.slezkin.models.User;
import ru.slezkin.repo.PermissionRepository;
import ru.slezkin.repo.UserRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllUsers() {
        List<User> result = userRepository.findAllUsers();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_by_id")
    public ResponseEntity<?> getUserById(@RequestParam(value = "id") Integer id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addUser(@RequestParam(value = "login", defaultValue = "") String login,
                                     @RequestParam(value = "password", defaultValue = "") String password,
                                     @RequestParam(value = "permission_id", defaultValue = "") Integer permission_id) {
        Optional<Permission> permission = permissionRepository.findById(permission_id);
        if (permission.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        User user = new User(login, password, permission.get());
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path="/change_permission_by_id")
    public ResponseEntity<?> changeUserPermissionById(@RequestParam(value = "id") Integer id,
                                                      @RequestParam(value = "permission_id") Integer permission_id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Optional<Permission> permission = permissionRepository.findById(permission_id);
        if (permission.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        user.get().setPermission(permission.get());
        userRepository.save(user.get());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path="/delete_by_id")
    public ResponseEntity<?> deleteUserById(@RequestParam(value = "id") Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        userRepository.delete(user.get());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
