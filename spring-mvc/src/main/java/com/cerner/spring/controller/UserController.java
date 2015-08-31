package com.cerner.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cerner.spring.model.User;
import com.cerner.spring.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public String users(Model model)
	{
		model.addAttribute("users",userService.findAll());
		return "users";
	}
	
	@RequestMapping("/users/{id}")
	public String details(Model model, @PathVariable int id)
	{
		model.addAttribute("user",userService.findOneWithBlogs(id));
		return "user-detail";
	}
	
	@ModelAttribute("user")
	public User construct()
	{
		return new User();
	}
	@RequestMapping("/register")
	public String showRegister()
	{
		return "user-register";
	}
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String doRegister(@ModelAttribute User user)
	{
		userService.save(user);
		return "user-register";
	}
}
