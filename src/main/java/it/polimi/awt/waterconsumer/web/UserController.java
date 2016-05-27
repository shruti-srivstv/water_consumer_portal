package it.polimi.awt.waterconsumer.web;

import java.util.List;

import javax.validation.Valid;

import it.polimi.awt.waterconsumer.domain.User;
import it.polimi.awt.waterconsumer.exception.DuplicatedUsernameException;
import it.polimi.awt.waterconsumer.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(method=RequestMethod.GET)
	public String getAllUsers(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute(users);
		
		return "user/list";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
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