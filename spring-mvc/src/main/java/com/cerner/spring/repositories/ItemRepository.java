package com.cerner.spring.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cerner.spring.model.Blog;
import com.cerner.spring.model.Item;
import com.cerner.spring.model.User;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByBlog(Blog blog,Pageable pageable);
}
