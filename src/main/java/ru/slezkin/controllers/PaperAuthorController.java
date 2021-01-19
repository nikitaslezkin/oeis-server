package ru.slezkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slezkin.models.*;
import ru.slezkin.repo.AuthorRepository;
import ru.slezkin.repo.PaperAuthorRepository;
import ru.slezkin.repo.PaperRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paper_author")
public class PaperAuthorController {
    @Autowired
    private PaperAuthorRepository paperAuthorRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllPaperAuthor() {
        List<PaperAuthor> result = paperAuthorRepository.findAllPaperAuthor();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_all_authors_by_paper_id")
    public ResponseEntity<?> getAllAuthorsByPaperId(@RequestParam(value = "paper_id") Integer paper_id) {
        Optional<Paper> paper = paperRepository.findById(paper_id);
        if (paper.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Author> result = paperAuthorRepository.findAllAuthorsByPaper(paper.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/get_all_papers_by_author_id")
    public ResponseEntity<?> getAllPapersByAuthorId(@RequestParam(value = "author_id") Integer author_id) {
        Optional<Author> author = authorRepository.findById(author_id);
        if (author.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Paper> result = paperAuthorRepository.findAllPapersByAuthor(author.get());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addPaperAuthor(@RequestParam(value = "paper_id", defaultValue = "") Integer paper_id,
                                            @RequestParam(value = "author_id", defaultValue = "") Integer author_id) {
        Optional<Paper> paper = paperRepository.findById(paper_id);
        if (paper.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Optional<Author> author = authorRepository.findById(author_id);
        if (author.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        PaperAuthor result = paperAuthorRepository.save(new PaperAuthor(paper.get(), author.get()));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
