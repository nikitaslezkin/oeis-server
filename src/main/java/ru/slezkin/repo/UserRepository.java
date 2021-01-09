package ru.slezkin.repo;

import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}