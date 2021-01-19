package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Circuit;
import ru.slezkin.models.CircuitTag;
import ru.slezkin.models.Tag;
import java.util.List;

public interface CircuitTagRepository extends CrudRepository<CircuitTag, Integer> {

    @Query("select ct from CircuitTag ct where True = True")
    List<CircuitTag> findAllCircuitTag();

    @Query("select ct.tag from CircuitTag ct where ct.circuit=?1")
    List<Tag> findAllTagsByCircuit(Circuit circuit);

    @Query("select ct.circuit from CircuitTag ct where ct.tag=?1")
    List<Circuit> findAllCircuitsByTag(Tag tag);
}