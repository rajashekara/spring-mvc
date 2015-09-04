package com.cerner.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerner.spring.model.Blog;
import com.cerner.spring.model.User;
import com.cerner.spring.repositories.BlogRepository;
import com.cerner.spring.repositories.UserRepository;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void save(Blog blog, String name) {
		
		User user=userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
	}

}
