package com.cerner.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
public class InitDbService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void init()
	{
		Role roleUser=new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		
		Role roleAdmin=new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		
		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
		userAdmin.setPassword(bcrypt.encode("admin"));
		List<Role> roles=new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		
		Blog rajBlog=new Blog();
		rajBlog.setName("RajBlog");
		rajBlog.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		rajBlog.setUser(userAdmin);
		blogRepository.save(rajBlog);
		
		
		Item item1 = new Item();
		item1.setBlog(rajBlog);
		item1.setTitle("First Item");
		item1.setLink("http://www.javavids.com");
		item1.setPublishDate(new Date());
		
		Item item2 = new Item();
		item2.setBlog(rajBlog);
		item2.setTitle("Second Item");
		item2.setLink("http://www.javavids.com");
		item1.setPublishDate(new Date());
		
		itemRepository.save(item1);
		itemRepository.save(item2);
		itemRepository.flush();
		blogRepository.flush();
		userRepository.flush();
		roleRepository.flush();
	}
}
