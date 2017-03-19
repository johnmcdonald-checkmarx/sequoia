package com.sqribl.sequoia.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freeform.core.model.User;
import com.freeform.core.service.EntityService;
 
@Controller
class IndexController {
	
	@Autowired
	@Lazy
	private EntityService<User> userService;
 
	@RequestMapping("/")
    String index() {
        return "index";
    }
	
	@ModelAttribute("userCount")
	public Long populateSeedStarters() {
	    return this.userService.count();
	}
}