package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.Paper;
import ru.slezkin.models.PaperTag;
import ru.slezkin.models.Tag;
import ru.slezkin.repo.PaperRepository;
import ru.slezkin.repo.PaperTagRepository;
import ru.slezkin.repo.TagRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paper_tag")
public class PaperTagController {
    @Autowired
    private PaperTagRepository paperTagRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllPaperTag() {
        List<PaperTag> result = paperTagRepository.findAllPaperTag();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_all_tags_by_paper_id")
    public ResponseEntity<?> getAllTagsByPaperId(@RequestParam(value = "paper_id") Integer paper_id) {
        Optional<Paper> paper = paperRepository.findById(paper_id);
        if (paper.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Tag> result = paperTagRepository.findAllTagsByPaper(paper.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_all_papers_by_tag_id")
    public ResponseEntity<?> getAllPapersByTagId(@RequestParam(value = "tag_id") Integer tag_id) {
        Optional<Tag> tag = tagRepository.findById(tag_id);
        if (tag.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Paper> result = paperTagRepository.findAllPapersByTag(tag.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addPaperTag(@RequestParam(value = "paper_id", defaultValue = "") Integer paper_id,
                                         @RequestParam(value = "tag_id", defaultValue = "") Integer tag_id) {
        Optional<Paper> paper = paperRepository.findById(paper_id);
        if (paper.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Optional<Tag> tag = tagRepository.findById(tag_id);
        if (tag.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        PaperTag result = paperTagRepository.save(new PaperTag(paper.get(), tag.get()));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
