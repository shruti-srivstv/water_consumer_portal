package it.polimi.awt.springmvc.service;

import it.polimi.awt.springmvc.domain.Book;
import it.polimi.awt.springmvc.exception.DuplicatedIsbnException;

import java.util.List;

public interface BookService {	
	public List<Book> findAll();
	public Book findBookByIsbn(String isbn);
	public void saveOrUpdateBook(Book book) throws DuplicatedIsbnException;
	public void removeBook(Integer id);
}