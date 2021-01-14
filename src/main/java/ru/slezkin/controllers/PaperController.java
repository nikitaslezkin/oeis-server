package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.slezkin.models.Paper;
import ru.slezkin.repo.PaperRepository;
import java.util.List;

@RestController
public class PaperController {
    @Autowired
    private PaperRepository paperRepository;

    @GetMapping(path="/all_papers")
    public ResponseEntity<?> getAllPapers() {
        List<Paper> result = paperRepository.findAllPapers();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
