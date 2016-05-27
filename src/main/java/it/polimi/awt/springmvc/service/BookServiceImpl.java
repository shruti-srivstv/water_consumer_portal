package it.polimi.awt.springmvc.service;

import it.polimi.awt.springmvc.domain.Book;
import it.polimi.awt.springmvc.exception.DuplicatedIsbnException;
import it.polimi.awt.springmvc.repository.BookRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	@Override
	public Book findBookByIsbn(String isbn) {
		return bookRepository.findBookByIsbn(isbn);
	}
	
	@Override
	public void saveOrUpdateBook(Book book) throws DuplicatedIsbnException {
		Book existentBook = this.findBookByIsbn(book.getIsbn());
				
		if (existentBook == null){
			if (book.getId() == null){
				bookRepository.persistBook(book);
			}
			else{
				bookRepository.saveOrUpdateBook(book);
			}
		}
		else{
			if (book.getId() == existentBook.getId()){
				bookRepository.saveOrUpdateBook(book);
			}
			else{
				throw new DuplicatedIsbnException();
			}
		}
	}
	
	@Override
	public void removeBook(Integer id) {
		Book book = bookRepository.findBookById(id);
		bookRepository.removeBook(book);
	}
}
