package com.sqribl.sequoia.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.freeform.core.model.User;
import com.freeform.core.service.EntityService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final String _VIEW = "/userView";
	private static final String _EDIT = "/userEdit";
	private static final String _LIST = "/userList1";
	
	@Autowired
	@Lazy
	private EntityService<User> userService;
	
	@RequestMapping("/list")
    String getListUser(Model model) {
		System.out.println("requst: list");
		model.addAttribute("userList",userService.findAll());
        return _LIST;
    }
	
	@GetMapping("/add")
    public String add(Model model) {
		System.out.println("request:add");
		model.addAttribute(new User());
		return _EDIT;
    }
	
	@GetMapping("/edit/{userId}")
    public String edit(@PathVariable Long userId, Model model) {
		System.out.println("request:edit");
		User user = this.userService.findById(userId);
		model.addAttribute(user);
        return _EDIT;
    }
	
	@GetMapping("/view/{userId}")
    public String view(@PathVariable Long userId, Model model) {
		System.out.println("request:view");
		User user = this.userService.findById(userId);
		model.addAttribute(user);
        return _VIEW;
    }
	
	@PostMapping("/save")
    public String save(@ModelAttribute User user) {
		System.out.println("post:save:"+user.getId());
		try {
			if( user.getId() != null ) {
				System.out.println("updating");
				userService.update(user);
			} else {
				System.out.println("creating");
				userService.create(user);
			}	
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/user/"+_VIEW+"/"+user.getId();
    }
}
