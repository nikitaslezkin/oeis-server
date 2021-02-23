package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.Basis;
import ru.slezkin.repo.BasisRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/basis")
public class BasisController {
    @Autowired
    private BasisRepository basisRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllBases() {
        List<Basis> result = basisRepository.findAllBases();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> getBasisById(@PathVariable Integer id) {
        Optional<Basis> result = basisRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addBasis(@RequestParam(value = "name", defaultValue = "") String name,
                                      @RequestParam(value = "description", defaultValue = "") String description) {
        Basis result = basisRepository.save(new Basis(name, description));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/delete_by_id")
    public ResponseEntity<?> deleteBasisById(@RequestParam(value = "id") Integer id) {
        Optional<Basis> result = basisRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        basisRepository.delete(result.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
