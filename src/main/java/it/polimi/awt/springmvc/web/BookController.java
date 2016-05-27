package it.polimi.awt.springmvc.web;

import java.util.List;

import javax.validation.Valid;

import it.polimi.awt.springmvc.domain.Book;
import it.polimi.awt.springmvc.exception.DuplicatedIsbnException;
import it.polimi.awt.springmvc.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@RequestMapping(method=RequestMethod.GET)
	public String getAllBooks(Model model) {
		List<Book> books = bookService.findAll();
		model.addAttribute(books);
		
		return "book/list";
	}
	
	@RequestMapping(value="{isbn}", method=RequestMethod.GET)
	public String getBookDetails(@PathVariable String isbn, Model model) {
		Book book = bookService.findBookByIsbn(isbn);
		model.addAttribute(book);
		return "book/edit";
	}
	
	@RequestMapping(value="{isbn}", params="save", method=RequestMethod.POST)
	public String saveBookDetails(Model model, @Valid Book book, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors())	
			return "book/edit";
		
		try {
			bookService.saveOrUpdateBook(book);
		} catch (DuplicatedIsbnException e) {
			bindingResult.rejectValue("isbn", "Unique.book.isbn", "the isbn already exists");
			return "book/edit";
		}
		
		return "redirect:/books";
	}
	
	@RequestMapping(value="{isbn}", params="cancel", method=RequestMethod.POST)
	public String cancelUpdateBookDetails() {
		return "redirect:/books";
	}
	
	@RequestMapping(value="{isbn}", params="remove", method=RequestMethod.POST)
	public String removeBookDetails(@RequestParam("id") Integer id) {
		bookService.removeBook(id);
		return "redirect:/books";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String createNew(Model model) {
		Book book = new Book();
		model.addAttribute(book);
		return "book/edit";
	}
}