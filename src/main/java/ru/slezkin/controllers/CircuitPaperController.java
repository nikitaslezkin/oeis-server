package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.*;
import ru.slezkin.repo.CircuitPaperRepository;
import ru.slezkin.repo.CircuitRepository;
import ru.slezkin.repo.PaperRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/circuit_paper")
public class CircuitPaperController {
    @Autowired
    private CircuitPaperRepository circuitPaperRepository;

    @Autowired
    private CircuitRepository circuitRepository;

    @Autowired
    private PaperRepository paperRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllCircuitPaper() {
        List<CircuitPaper> result = circuitPaperRepository.findAllCircuitPaper();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_all_papers_by_circuit_id")
    public ResponseEntity<?> getAllPapersByCircuitId(@RequestParam(value = "circuit_id") Integer circuit_id) {
        Optional<Circuit> circuit = circuitRepository.findById(circuit_id);
        if (circuit.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Paper> result = circuitPaperRepository.findAllPapersByCircuit(circuit.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_all_circuits_by_paper_id")
    public ResponseEntity<?> getAllCircuitsByPaperId(@RequestParam(value = "paper_id") Integer paper_id) {
        Optional<Paper> paper = paperRepository.findById(paper_id);
        if (paper.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Circuit> result = circuitPaperRepository.findAllCircuitsByPaper(paper.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addCircuitPaper(@RequestParam(value = "circuit_id", defaultValue = "") Integer circuit_id,
                                             @RequestParam(value = "paper_id", defaultValue = "") Integer paper_id) {
        Optional<Circuit> circuit = circuitRepository.findById(circuit_id);
        if (circuit.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Optional<Paper> paper = paperRepository.findById(paper_id);
        if (paper.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        CircuitPaper result = circuitPaperRepository.save(new CircuitPaper(circuit.get(), paper.get()));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
