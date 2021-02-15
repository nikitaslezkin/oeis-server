package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.Circuit;
import ru.slezkin.models.Tag;
import ru.slezkin.repo.*;
import java.util.*;

@RestController
@RequestMapping("/search_line")
public class SearchLineController {
    @Autowired
    private CircuitRepository circuitRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CircuitTagRepository circuitTagRepository;

    @GetMapping(path="/get_all_circuits_by_tags")
    public ResponseEntity<?> getAllCircuitsByTags(@RequestParam(value = "search_line") String find_text) {
        List<Tag> tags = tagRepository.findAllTags();
        List<Circuit> circuits = new LinkedList<>();
        for(Tag tag : tags) {
            if (find_text.toLowerCase().contains(tag.getName().toLowerCase())) {
                circuits.addAll(circuitTagRepository.findAllCircuitsByTag(tag));
            }
        }
        return new ResponseEntity<>(circuits, HttpStatus.OK);
    }

    @GetMapping(path="/get_all_circuits_by_tt")
    public ResponseEntity<?> getAllCircuitsByTT(@RequestParam(value = "search_line") String find_text) {
        List<Circuit> circuits = circuitRepository.findAllCircuits();
        List<Circuit> needed_circuits = new LinkedList<>();
        for(Circuit circuit : circuits) {
            if (circuit.getTruth_table().toLowerCase().contains(find_text.toLowerCase())) {
                needed_circuits.add(circuit);
            }
        }
        return new ResponseEntity<>(needed_circuits, HttpStatus.OK);
    }
}
