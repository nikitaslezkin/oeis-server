package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.Author;
import ru.slezkin.repo.AuthorRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllAuthors() {
        List<Author> result = authorRepository.findAllAuthors();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_by_id")
    public ResponseEntity<?> getAuthorById(@RequestParam(value = "id") Integer id) {
        Optional<Author> result = authorRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addAuthor(@RequestParam(value = "name", defaultValue = "") String name,
                                       @RequestParam(value = "description", defaultValue = "") String description) {
        Author result = authorRepository.save(new Author(name, description));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/delete_by_id")
    public ResponseEntity<?> deleteAuthorById(@RequestParam(value = "id") Integer id) {
        Optional<Author> result = authorRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        authorRepository.delete(result.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
