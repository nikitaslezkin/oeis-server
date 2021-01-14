package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.User;
import ru.slezkin.repo.UserRepository;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/get_all_users")
    public ResponseEntity<?> getAllUsers() {
        List<User> result = userRepository.findAllUsers();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping(path="/get_user_by_id")
//    public User getUserById(@RequestParam(value = "id") Integer id) {
//        User result = userRepository.findById(id).orElse(new User());
//
//        return result;
//    }
//
//    @PostMapping(path="/add_user")
//    public User addUser(@RequestParam(value = "login", defaultValue = "") String login,
//                              @RequestParam(value = "password", defaultValue = "") String password,
//                              @RequestParam(value = "permission", defaultValue = "") String permission) {
//        User user = new User(login, password, permission);
//        User result = userRepository.save(user);
//
//        return result;
//    }
//
//    @PutMapping(path="/change_user_permission_by_id")
//    public ResponseEntity<?> changeUserPermissionById(@RequestParam(value = "id") Integer id,
//                                                      @RequestParam(value = "permission") String permission) {
//        Optional<User> user = userRepository.findById(id);
//        if (user.isEmpty()) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//        user.get().setPermission(permission);
//        userRepository.save(user.get());
//
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
}
