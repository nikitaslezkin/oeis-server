package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.CircuitTag;
import java.util.List;

public interface CircuitTagRepository extends CrudRepository<CircuitTag, Integer> {

    @Query("select ct from CircuitTag ct where True = True")
    List<CircuitTag> findAllCircuitTag();
}