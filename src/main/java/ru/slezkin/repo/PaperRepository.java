package ru.slezkin.repo;

import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Paper;
import ru.slezkin.models.User;

public interface PaperRepository extends CrudRepository<Paper, Integer> {

}