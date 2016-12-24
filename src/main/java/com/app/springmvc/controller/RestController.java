package com.app.springmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.springmvc.service.UserProfileService;
import com.app.springmvc.service.UserService;



@Controller
@RequestMapping("/ws/v1")
@SessionAttributes("roles")
public class RestController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserProfileService userProfileService;
	
	
        
	@Autowired
	MessageSource messageSource;


}
