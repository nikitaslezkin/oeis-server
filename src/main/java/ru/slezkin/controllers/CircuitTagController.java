package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.*;
import ru.slezkin.repo.CircuitRepository;
import ru.slezkin.repo.CircuitTagRepository;
import ru.slezkin.repo.TagRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/circuit_tag")
public class CircuitTagController {
    @Autowired
    private CircuitTagRepository circuitTagRepository;

    @Autowired
    private CircuitRepository circuitRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllCircuitTag() {
        List<CircuitTag> result = circuitTagRepository.findAllCircuitTag();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_all_tags_by_circuit_id")
    public ResponseEntity<?> getAllTagsByCircuitId(@RequestParam(value = "circuit_id") Integer circuit_id) {
        Optional<Circuit> circuit = circuitRepository.findById(circuit_id);
        if (circuit.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Tag> result = circuitTagRepository.findAllTagsByCircuit(circuit.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_all_circuits_by_tag_id")
    public ResponseEntity<?> getAllCircuitsByTagId(@RequestParam(value = "tag_id") Integer tag_id) {
        Optional<Tag> tag = tagRepository.findById(tag_id);
        if (tag.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Circuit> result = circuitTagRepository.findAllCircuitsByTag(tag.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addCircuitTag(@RequestParam(value = "circuit_id", defaultValue = "") Integer circuit_id,
                                           @RequestParam(value = "tag_id", defaultValue = "") Integer tag_id) {
        Optional<Circuit> circuit = circuitRepository.findById(circuit_id);
        if (circuit.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Optional<Tag> tag = tagRepository.findById(tag_id);
        if (tag.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        CircuitTag result = circuitTagRepository.save(new CircuitTag(circuit.get(), tag.get()));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
