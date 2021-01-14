package ru.slezkin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.slezkin.models.Permission;
import java.util.List;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {

    @Query("select p from Permission p where True = True")
    List<Permission> findAllPermissions();
}