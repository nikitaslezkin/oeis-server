package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.CircuitPaper;
import java.util.List;

public interface CircuitPaperRepository extends CrudRepository<CircuitPaper, Integer> {

    @Query("select cp from CircuitPaper cp where True = True")
    List<CircuitPaper> findAllCircuitPaper();
}