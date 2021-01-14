package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Basis;
import java.util.List;

public interface BasisRepository extends CrudRepository<Basis, Integer> {

    @Query("select b from Basis b where True = True")
    List<Basis> findAllBases();
}