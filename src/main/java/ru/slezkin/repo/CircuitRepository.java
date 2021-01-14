package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Circuit;
import java.util.List;

public interface CircuitRepository extends CrudRepository<Circuit, Integer> {

    @Query("select c from Circuit c where True = True")
    List<Circuit> findAllCircuits();

    @Query("select c from Circuit c where c.checked = True")
    List<Circuit> findAllCheckedCircuits();

    @Query("select c from Circuit c where c.checked = False")
    List<Circuit> findAllUncheckedCircuits();
}