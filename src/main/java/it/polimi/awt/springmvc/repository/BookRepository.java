package it.polimi.awt.springmvc.repository;

import java.util.List;

import it.polimi.awt.springmvc.domain.Book;

public interface BookRepository {
	public List<Book> findAll();
	public Book findBookById(Integer id);
	public Book findBookByIsbn(String isbn);
	public void persistBook(Book book);
	public void saveOrUpdateBook(Book book);
	public void removeBook(Book book);
}
