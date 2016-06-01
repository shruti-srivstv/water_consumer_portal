package it.polimi.awt.waterconsumer.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import it.polimi.awt.waterconsumer.domain.User;
import it.polimi.awt.waterconsumer.domain.*;
import it.polimi.awt.waterconsumer.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("session")
//@RequestMapping("/users")
public class UserController {
			
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
		
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String getRegistrationPage(Model model){
		return "registration";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String showRegistrationResult(Model model, Integer householdId){
		User user = userService.findUserbyHouseholdId(householdId);
//		System.out.print("++++++++++++++++++++++" + user.getNeutralUser());
		return "user/registrationHome";
	}	

//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public String getLoginPage(Model model, HttpServletRequest request){
//		Integer userid = (Integer) request.getSession().getAttribute("userid");
//		if (userid!=null){
//			return "redirect:/portal";
//		}
//		else{
//			return "login";
//		}
//	}
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public String return403Page(Model model){
		return "403";
	}
	
	@RequestMapping(value="/portal", method=RequestMethod.GET)
	public String showHomepage(Model model, HttpServletRequest request){
		Integer userid = (Integer) request.getSession().getAttribute("userid");
		if (userid!=null){
			User user = userService.findUserById(userid);
			model.addAttribute(user);
			return "user/home";
		}
		else {
			return "redirect:/login";
		}
	}
	

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String validateLogin(Model model, String username, String password, HttpServletRequest request){
		Integer userid = userService.findUserforLogin(username, password);
		if (userid!=null){
			request.getSession().setAttribute("userid", userid);
			return "redirect:/portal";
		}
		else{
			model.addAttribute("message", "Invalid Username/Password !");
			return "redirect:/403";
		}
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
*/		return "user/home";
	}
}