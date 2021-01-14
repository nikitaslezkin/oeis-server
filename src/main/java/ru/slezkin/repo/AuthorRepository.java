package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Author;
import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    @Query("select a from Author a where True = True")
    List<Author> findAllAuthors();
}