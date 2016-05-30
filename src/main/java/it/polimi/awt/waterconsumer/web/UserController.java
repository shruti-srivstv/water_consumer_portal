package it.polimi.awt.waterconsumer.web;

import java.util.List;

import it.polimi.awt.waterconsumer.domain.User;
import it.polimi.awt.waterconsumer.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginPage(Model model){
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String showHomepage(Model model, String username){
		User user = userService.findUserbyUsername(username);
		model.addAttribute(user);
		return "user/homepage";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String getAllUsers(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute(users);
		
		return "user/list";
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public String getUserDetails(@PathVariable Integer id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute(user);
		return "user/homepage";
	}
}