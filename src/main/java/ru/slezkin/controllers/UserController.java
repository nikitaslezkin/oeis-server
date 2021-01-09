package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.slezkin.models.User;
import ru.slezkin.repo.UserRepository;
import java.util.LinkedList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/all_users")
    public List<User> getAllCircuits() {
        Iterable<User> users = userRepository.findAll();

        List<User> result = new LinkedList<>();
        for (User circuit : users) {
            result.add(circuit);
        }

        return result;
    }
}
