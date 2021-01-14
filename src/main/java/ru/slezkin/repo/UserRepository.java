package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.User;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where True = True")
    List<User> findAllUsers();
}