package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.PaperTag;
import java.util.List;

public interface PaperTagRepository extends CrudRepository<PaperTag, Integer> {

    @Query("select pt from PaperTag pt where True = True")
    List<PaperTag> findAllPaperTag();
}