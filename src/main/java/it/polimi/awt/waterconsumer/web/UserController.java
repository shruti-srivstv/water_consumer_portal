package it.polimi.awt.waterconsumer.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import it.polimi.awt.waterconsumer.domain.User;
import it.polimi.awt.waterconsumer.exception.DuplicatedUsernameException;
import it.polimi.awt.waterconsumer.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("session")
//@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginPage(Model model, HttpServletRequest request){
		Integer userid = (Integer) request.getSession().getAttribute("userid");
		if (userid!=null){
			return "redirect:/portal";
		}
		else{
			return "login";
		}
	}
	
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
			return "user/homepage";
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
		User user = userService.findUserById(id);
		model.addAttribute(user);
		return "user/homepage";
	}
	
//	@RequestMapping(value="{isbn}", params="save", method=RequestMethod.POST)
//	public String saveBookDetails(Model model, @Valid Book book, BindingResult bindingResult) {
//		
//		if (bindingResult.hasErrors())	
//			return "book/edit";
//		
//		try {
//			bookService.saveOrUpdateBook(book);
//		} catch (DuplicatedIsbnException e) {
//			bindingResult.rejectValue("isbn", "Unique.book.isbn", "the isbn already exists");
//			return "book/edit";
//		}
//		
//		return "redirect:/books";
//	}
//	
//	@RequestMapping(value="{isbn}", params="cancel", method=RequestMethod.POST)
//	public String cancelUpdateBookDetails() {
//		return "redirect:/books";
//	}
//	
//	@RequestMapping(value="{isbn}", params="remove", method=RequestMethod.POST)
//	public String removeBookDetails(@RequestParam("id") Integer id) {
//		bookService.removeBook(id);
//		return "redirect:/books";
//	}
//	
//	@RequestMapping(value="/create", method=RequestMethod.GET)
//	public String createNew(Model model) {
//		Book book = new Book();
//		model.addAttribute(book);
//		return "book/edit";
//	}
}