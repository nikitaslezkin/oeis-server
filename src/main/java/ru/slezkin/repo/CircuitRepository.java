package ru.slezkin.repo;

import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Circuit;

public interface CircuitRepository extends CrudRepository<Circuit, Integer> {

}