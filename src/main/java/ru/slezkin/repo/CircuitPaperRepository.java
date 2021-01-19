package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Circuit;
import ru.slezkin.models.CircuitPaper;
import ru.slezkin.models.Paper;
import java.util.List;

public interface CircuitPaperRepository extends CrudRepository<CircuitPaper, Integer> {

    @Query("select cp from CircuitPaper cp where True = True")
    List<CircuitPaper> findAllCircuitPaper();

    @Query("select cp.paper from CircuitPaper cp where cp.circuit=?1")
    List<Paper> findAllPapersByCircuit(Circuit circuit);

    @Query("select cp.circuit from CircuitPaper cp where cp.paper=?1")
    List<Circuit> findAllCircuitsByPaper(Paper paper);
}