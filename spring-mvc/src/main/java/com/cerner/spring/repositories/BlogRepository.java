package com.cerner.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cerner.spring.model.Blog;
import com.cerner.spring.model.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUser(User user);
}
