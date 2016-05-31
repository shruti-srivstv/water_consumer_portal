package it.polimi.awt.waterconsumer.web;

import java.util.List;

import it.polimi.awt.waterconsumer.domain.*;
import it.polimi.awt.waterconsumer.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
			
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/idValidate", method=RequestMethod.GET)
	public String getRegisterIdPage(Model model){
		return "idValidate";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String getRegisterPage(Model model){
		return "registration";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginPage(Model model){
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String showHomepage(Model model, String username, String password){
		User user = userService.findUserbyUsername(username, password);
		
		if(user == null){
			model.addAttribute("message", "Invalid Username/Password !");
			return "login";
		}
		
		model.addAttribute("user", user);
		
		System.out.print("++++++++++++++++++++++" + user.getNeutralUser());
		return "user/home";
	}	
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String getAllUsers(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute(users);
		
		return "user/list";
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public String getUserDetails(@PathVariable Integer id, Model model) {
/*		User user = userService.findUserById(id);
		model.addAttribute(user);
*/		return "user/homepage";
	}
}