package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.*;
import ru.slezkin.repo.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PaperTagRepository paperTagRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PaperAuthorRepository paperAuthorRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllPapers() {
        List<Paper> result = paperRepository.findAllPapers();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> getPaperById(@PathVariable Integer id) {
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

    @GetMapping(path="/search")
    public ResponseEntity<?> search(@RequestParam(value = "search") String find_text,
                                    @RequestParam(value = "kind") Integer kind) {


        List<Paper> papers = new LinkedList<>();

        if (kind == 1) {
            List<Tag> tags = tagRepository.findAllTags();
            for(Tag tag : tags) {
                if (find_text.toLowerCase().contains(tag.getName().toLowerCase())) {
                    papers.addAll(paperTagRepository.findAllPapersByTag(tag));
                }
            }
        } else if (kind == 2) {
            List<Author> authors = authorRepository.findAllAuthors();
            for(Author author : authors) {
                if (author.getName().toLowerCase().contains(find_text.toLowerCase())) {
                    papers.addAll(paperAuthorRepository.findAllPapersByAuthor(author));
                }
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<FullPaper> result = new LinkedList<>();
        for (Paper paper : papers) {
            List<Tag> tags = paperTagRepository.findAllTagsByPaper(paper);
            List<Author> authors = paperAuthorRepository.findAllAuthorsByPaper(paper);
            result.add(new FullPaper(paper, tags, authors));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
