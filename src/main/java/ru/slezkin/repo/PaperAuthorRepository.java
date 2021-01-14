package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.PaperAuthor;
import java.util.List;

public interface PaperAuthorRepository extends CrudRepository<PaperAuthor, Integer> {

    @Query("select pa from PaperAuthor pa where True = True")
    List<PaperAuthor> findAllPaperAuthor();
}