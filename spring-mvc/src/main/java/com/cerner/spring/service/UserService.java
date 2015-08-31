package com.cerner.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cerner.spring.model.Blog;
import com.cerner.spring.model.Item;
import com.cerner.spring.model.Role;
import com.cerner.spring.model.User;
import com.cerner.spring.repositories.BlogRepository;
import com.cerner.spring.repositories.ItemRepository;
import com.cerner.spring.repositories.RoleRepository;
import com.cerner.spring.repositories.UserRepository;

@Transactional
@Service
public class UserService {

		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private BlogRepository blogRepository;
		
		@Autowired
		private ItemRepository itemRepository;
		
		@Autowired
		private RoleRepository roleRepository;
		
		public List<User> findAll()
		{
			return userRepository.findAll();
		}

		public User findOne(int id) {
			return userRepository.findOne(id);
			
		}
		
		@Transactional
		public User findOneWithBlogs(int id) {
			User user=findOne(id);
			List<Blog> blogs=blogRepository.findByUser(user);
			for(Blog bl:blogs)
			{
				List<Item> item=itemRepository.findByBlog(bl,new PageRequest(0, 10, Direction.DESC, "publishDate"));
				bl.setItems(item);
			}
			user.setBlogs(blogs);
			return user;
		}

		public void save(User user) {
			user.setEnabled(true);
			BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			List<Role> roles=new ArrayList<Role>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
		
			user.setRoles(roles);
			userRepository.save(user);
			userRepository.flush();
			
		}
}
