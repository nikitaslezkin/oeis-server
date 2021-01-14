package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.Circuit;
import ru.slezkin.repo.CircuitRepository;
import java.util.List;

@RestController
public class CircuitController {
    @Autowired
    private CircuitRepository circuitRepository;

    @GetMapping(path="/get_all_circuits")
    public ResponseEntity<?> getAllCircuits() {
        List<Circuit> result = circuitRepository.findAllCircuits();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping(path="/get_all_checked_circuits")
//    public List<Circuit> getAllCheckedCircuits() {
//        List<Circuit> result = circuitRepository.findAllCheckedCircuits();
//
//        return result;
//    }
//
//    @GetMapping(path="/get_all_unchecked_circuits")
//    public List<Circuit> getAllUncheckedCircuits() {
//        List<Circuit> result = circuitRepository.findAllUncheckedCircuits();
//
//        return result;
//    }
//
//    @GetMapping(path="/get_circuit_by_id")
//    public Circuit getCircuitById(@RequestParam(value = "id") Integer id) {
//        Circuit result = circuitRepository.findById(id).orElse(new Circuit());
//
//        return result;
//    }
//
//    @PostMapping(path="/add_circuit")
//    public Circuit addCircuit(@RequestParam(value = "name", defaultValue = "") String name,
//                              @RequestParam(value = "desc", defaultValue = "") String description,
//                              @RequestParam(value = "ckt", defaultValue = "") String ckt,
//                              @RequestParam(value = "basis_id", defaultValue = "") Integer basis_id,
//                              @RequestParam(value = "truth_table", defaultValue = "") String truth_table,
//                              @RequestParam(value = "author", defaultValue = "0") Integer circuit_author) {
//        Circuit circuit = new Circuit(name, description, ckt, basis_id, truth_table, false);
//        Circuit result = circuitRepository.save(circuit);
//        return result;
//    }
//
//    @PostMapping(path="/check_circuit_by_id")
//    public String checkingCircuitById(@RequestParam(value = "id") Integer id) {
//        Optional<Circuit> circuit = circuitRepository.findById(id);
//        circuit.get().setChecked(Boolean.TRUE);
//        circuitRepository.save(circuit.get());
//
//        return "OK";
//    }
}
