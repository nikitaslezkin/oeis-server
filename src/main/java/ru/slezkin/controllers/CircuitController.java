package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.*;
import ru.slezkin.repo.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@RestController
@RequestMapping("/circuit")
public class CircuitController {
    @Autowired
    private CircuitRepository circuitRepository;

    @Autowired
    private CircuitTagRepository circuitTagRepository;

    @Autowired
    private CircuitPaperRepository circuitPaperRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private BasisRepository basisRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllCircuits() {
        List<FullCircuit> result = new LinkedList<>();
        List<Circuit> circuits = circuitRepository.findAllCircuits();
        for (Circuit circuit : circuits) {
            List<Tag> tags = circuitTagRepository.findAllTagsByCircuit(circuit);
            List<Paper> papers = circuitPaperRepository.findAllPapersByCircuit(circuit);
            result.add(new FullCircuit(circuit, tags, papers));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_all_checked")
    public ResponseEntity<?> getAllCheckedCircuits() {
        List<Circuit> result = circuitRepository.findAllCheckedCircuits();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_all_unchecked")
    public ResponseEntity<?> getAllUncheckedCircuits() {
        List<Circuit> result = circuitRepository.findAllUncheckedCircuits();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> getCircuitById(@PathVariable Integer id) {
        Optional<Circuit> circuit = circuitRepository.findById(id);
        if (circuit.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Tag> tags = circuitTagRepository.findAllTagsByCircuit(circuit.get());
        List<Paper> papers = circuitPaperRepository.findAllPapersByCircuit(circuit.get());
        return new ResponseEntity<>(new FullCircuit(circuit.get(), tags, papers), HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addCircuit(@RequestParam(value = "name", defaultValue = "") String name,
                                        @RequestParam(value = "description", defaultValue = "") String description,
                                        @RequestParam(value = "ckt", defaultValue = "") String ckt,
                                        @RequestParam(value = "basis_id", defaultValue = "") Integer basis_id,
                                        @RequestParam(value = "truth_table", defaultValue = "") String truth_table,
                                        @RequestParam(value = "user_id", defaultValue = "") Integer user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Optional<Basis> basis = basisRepository.findById(basis_id);
        if (basis.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Circuit circuit = new Circuit(name, description, ckt, basis.get(), truth_table, user.get(), false);
        Circuit result = circuitRepository.save(circuit);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/check_by_id")
    public ResponseEntity<?> checkCircuitById(@RequestParam(value = "id") Integer id) {
        Optional<Circuit> result = circuitRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        result.get().setChecked(Boolean.TRUE);
        circuitRepository.save(result.get());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/delete_by_id")
    public ResponseEntity<?> deleteCircuitById(@RequestParam(value = "id") Integer id) {
        Optional<Circuit> result = circuitRepository.findById(id);
        if (result.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        circuitRepository.delete(result.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/image")
    public ResponseEntity<?> getImageAsByteArray() {
        try {
            File file = new ClassPathResource("image.png").getFile();
            String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));
            Map<String, String> jsonMap = new HashMap<>();
            jsonMap.put("content", encodeImage);
            return new ResponseEntity<>(jsonMap, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(path="/search")
    public ResponseEntity<?> search(@RequestParam(value = "search_line") String find_text,
                                    @RequestParam(value = "query") String query) {


        List<Circuit> circuits = new LinkedList<>();

        if (query.equals("tags")) {
            List<Tag> tags = tagRepository.findAllTags();
            for(Tag tag : tags) {
                if (find_text.toLowerCase().contains(tag.getName().toLowerCase())) {
                    circuits.addAll(circuitTagRepository.findAllCircuitsByTag(tag));
                }
            }
        } else if (query.equals("tt")) {
            List<Circuit> circuits1 = circuitRepository.findAllCircuits();
            for(Circuit circuit : circuits1) {
                if (circuit.getTruth_table().toLowerCase().contains(find_text.toLowerCase())) {
                    circuits.add(circuit);
                }
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<FullCircuit> result = new LinkedList<>();
        for (Circuit circuit : circuits) {
            List<Tag> tags = circuitTagRepository.findAllTagsByCircuit(circuit);
            List<Paper> papers = circuitPaperRepository.findAllPapersByCircuit(circuit);
            result.add(new FullCircuit(circuit, tags, papers));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
