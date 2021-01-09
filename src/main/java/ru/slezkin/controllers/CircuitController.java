package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.slezkin.models.Circuit;
import ru.slezkin.repo.CircuitRepository;
import java.util.LinkedList;
import java.util.List;

@RestController
public class CircuitController {
    @Autowired
    private CircuitRepository circuitRepository;

    @GetMapping(path="/all_circuits")
    public List<Circuit> getAllCircuits() {
        Iterable<Circuit> circuits = circuitRepository.findAll();

        List<Circuit> result = new LinkedList<>();
        for (Circuit circuit : circuits) {
            result.add(circuit);
        }

        return result;
    }

    @GetMapping(path="/circuit/{id}")
    public Circuit getCircuitById(@PathVariable Integer id) {
        Circuit result = circuitRepository.findById(id).orElse(new Circuit());

        return result;
    }
}
