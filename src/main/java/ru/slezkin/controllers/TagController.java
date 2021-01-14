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
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping(path="/get_all_tags")
    public ResponseEntity<?> getAllTags() {
        List<Tag> result = tagRepository.findAllTags();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_tag_by_id")
    public ResponseEntity<?> getTagById(@RequestParam(value = "id") Integer id) {
        Optional<Tag> result = tagRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
