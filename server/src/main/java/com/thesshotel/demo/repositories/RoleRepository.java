package com.thesshotel.demo.repositories;

import com.thesshotel.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "select * from role r where r.name = :name", nativeQuery = true)
    Role findByName(String name);
}
