package com.example.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.persistence.domain.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	Set<Role> findByName(String name);

}
