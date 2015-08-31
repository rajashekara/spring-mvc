package com.cerner.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cerner.spring.model.Role;
import com.cerner.spring.model.User;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
