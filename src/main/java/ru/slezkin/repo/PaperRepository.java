package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Paper;
import java.util.List;

public interface PaperRepository extends CrudRepository<Paper, Integer> {

    @Query("select p from Paper p where True = True")
    List<Paper> findAllPapers();
}