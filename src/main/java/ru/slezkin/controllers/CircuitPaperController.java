package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.slezkin.models.CircuitPaper;
import ru.slezkin.repo.CircuitPaperRepository;
import java.util.List;

@RestController
public class CircuitPaperController {
    @Autowired
    private CircuitPaperRepository circuitPaperRepository;

    @GetMapping(path="/get_all_circuit_paper")
    public ResponseEntity<?> getAllCircuitPaper() {
        List<CircuitPaper> result = circuitPaperRepository.findAllCircuitPaper();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping(path="/get_tag_by_id")
//    public ResponseEntity<?> getTagById(@RequestParam(value = "id") Integer id) {
//        Optional<Tag> result = tagRepository.findById(id);
//        if (result.isEmpty()) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
}
