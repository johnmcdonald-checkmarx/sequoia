package com.sqribl.sequoia.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.freeform.core.model.User;
import com.freeform.core.service.EntityService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	@Lazy
	private EntityService<User> userService;
	
	@RequestMapping("/list")
    String getListUser() {
		System.out.println("requst: /user/list");
        return "userlist";
    }
	
	@ModelAttribute("userList")
	public List<User> populateSeedStarters() {
		System.out.println("model:userList");
	    return this.userService.findAll();
	}
	
	@GetMapping
    public String newUser(final User user) {
		System.out.println("model:newUser");
        return "user";
    }

	@ModelAttribute("user")
	public User getUser(Long id) {
		System.out.println("getUser");
		if( id == null)
			return new User();
		else
			return this.userService.findById(id);
	}
	
	/*
	@ModelAttribute("/user/{id}")
	public User new(Long id) {
		System.out.println("getUserObjectThingy");
	    return this.userService.findById(id);
	}*/
}
