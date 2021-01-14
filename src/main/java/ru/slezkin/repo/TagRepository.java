package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Tag;
import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Integer> {

    @Query("select t from Tag t where True = True")
    List<Tag> findAllTags();
}