package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Paper;
import ru.slezkin.models.Tag;
import ru.slezkin.models.PaperTag;
import java.util.List;

public interface PaperTagRepository extends CrudRepository<PaperTag, Integer> {

    @Query("select pt from PaperTag pt where True = True")
    List<PaperTag> findAllPaperTag();

    @Query("select pt.tag from PaperTag pt where pt.paper=?1")
    List<Tag> findAllTagsByPaper(Paper paper);

    @Query("select pt.paper from PaperTag pt where pt.tag=?1")
    List<Paper> findAllPapersByTag(Tag tag);
}