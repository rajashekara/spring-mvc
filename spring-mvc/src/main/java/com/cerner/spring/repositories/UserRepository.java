package com.cerner.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cerner.spring.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

}
