package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.Permission;
import ru.slezkin.repo.PermissionRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllPermissions() {
        List<Permission> result = permissionRepository.findAllPermissions();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> getPermissionById(@PathVariable Integer id) {
        Optional<Permission> result = permissionRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addPermission(@RequestParam(value = "name", defaultValue = "") String name) {
        Permission result = permissionRepository.save(new Permission(name));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/delete_by_id")
    public ResponseEntity<?> deletePermissionById(@RequestParam(value = "id") Integer id) {
        Optional<Permission> result = permissionRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        permissionRepository.delete(result.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
