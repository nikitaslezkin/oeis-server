package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.slezkin.models.Paper;
import ru.slezkin.repo.PaperRepository;
import java.util.LinkedList;
import java.util.List;

@RestController
public class PaperController {
    @Autowired
    private PaperRepository paperRepository;

    @GetMapping(path="/all_papers")
    public List<Paper> getAllCircuits() {
        Iterable<Paper> papers = paperRepository.findAll();

        List<Paper> result = new LinkedList<>();
        for (Paper paper : papers) {
            result.add(paper);
        }

        return result;
    }
}
