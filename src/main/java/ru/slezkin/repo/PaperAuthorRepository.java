package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Author;
import ru.slezkin.models.Paper;
import ru.slezkin.models.PaperAuthor;
import java.util.List;

public interface PaperAuthorRepository extends CrudRepository<PaperAuthor, Integer> {

    @Query("select pa from PaperAuthor pa where True = True")
    List<PaperAuthor> findAllPaperAuthor();

    @Query("select pa.author from PaperAuthor pa where pa.paper=?1")
    List<Author> findAllAuthorsByPaper(Paper paper);

    @Query("select pa.paper from PaperAuthor pa where pa.author=?1")
    List<Paper> findAllPapersByAuthor(Author author);
}