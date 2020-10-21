package com.blazdemsar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazdemsar.domain.Role;
import com.blazdemsar.domain.User;
import com.blazdemsar.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="login")
	public String login(@RequestParam(value="logout", required=false) String logout,
			@RequestParam(value="error", required=false) String error,
			HttpServletRequest request, HttpServletResponse response, Model model
			)
	
	{
		
		String message = null;
		System.out.println("@HomeController.login() ....");

		if(logout != null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication !=null ) {
				new SecurityContextLogoutHandler().logout(request, response, authentication);
			}
			message = "You have been logged out successfully.";
			return "login";
		}

		if(error != null) {
			message = "Your username and/or password do not match.";
		}

		model.addAttribute("message", message);
		return "login";

	}
	
	@RequestMapping(value= {"/", "/hotel", "/home"}, method=RequestMethod.GET)
	public String getHotelView(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String username = auth.getName();
		
		User user = userService.findByUsername(username);
		
		model.addAttribute("currentUser", user);
		
		return "hotel";
		
	}
	
	@RequestMapping(value="admin")
	public String getAdminForm() {
		
		
		return "admin";
	}
	
}
