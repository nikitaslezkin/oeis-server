package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.Paper;
import ru.slezkin.repo.PaperRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    private PaperRepository paperRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllPapers() {
        List<Paper> result = paperRepository.findAllPapers();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_by_id")
    public ResponseEntity<?> getPaperById(@RequestParam(value = "id") Integer id) {
        Optional<Paper> result = paperRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addPaper(@RequestParam(value = "name", defaultValue = "") String name,
                                      @RequestParam(value = "description", defaultValue = "") String description,
                                      @RequestParam(value = "place", defaultValue = "") String place,
                                      @RequestParam(value = "doi", defaultValue = "") String doi) {
        Paper result = paperRepository.save(new Paper(name, description, place, doi));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/delete_by_id")
    public ResponseEntity<?> deletePaperById(@RequestParam(value = "id") Integer id) {
        Optional<Paper> result = paperRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        paperRepository.delete(result.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
