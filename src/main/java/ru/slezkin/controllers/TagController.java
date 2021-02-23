package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.Tag;
import ru.slezkin.repo.TagRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllTags() {
        List<Tag> result = tagRepository.findAllTags();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> getTagById(@PathVariable Integer id) {
        Optional<Tag> result = tagRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addTag(@RequestParam(value = "name", defaultValue = "") String name) {
        Tag result = tagRepository.save(new Tag(name));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/delete_by_id")
    public ResponseEntity<?> deleteTagById(@RequestParam(value = "id") Integer id) {
        Optional<Tag> result = tagRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        tagRepository.delete(result.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
