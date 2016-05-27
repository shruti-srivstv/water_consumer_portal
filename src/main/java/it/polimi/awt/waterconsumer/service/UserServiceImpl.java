package it.polimi.awt.waterconsumer.service;

import it.polimi.awt.waterconsumer.domain.User;
import it.polimi.awt.waterconsumer.exception.DuplicatedUsernameException;
import it.polimi.awt.waterconsumer.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
//	@Override
//	public Book findBookByIsbn(String isbn) {
//		return bookRepository.findBookByIsbn(isbn);
//	}
//	
//	@Override
//	public void saveOrUpdateBook(Book book) throws DuplicatedIsbnException {
//		Book existentBook = this.findBookByIsbn(book.getIsbn());
//				
//		if (existentBook == null){
//			if (book.getId() == null){
//				bookRepository.persistBook(book);
//			}
//			else{
//				bookRepository.saveOrUpdateBook(book);
//			}
//		}
//		else{
//			if (book.getId() == existentBook.getId()){
//				bookRepository.saveOrUpdateBook(book);
//			}
//			else{
//				throw new DuplicatedIsbnException();
//			}
//		}
//	}
//	
//	@Override
//	public void removeBook(Integer id) {
//		Book book = bookRepository.findBookById(id);
//		bookRepository.removeBook(book);
//	}
	
	public User findUserById(Integer id) {
		return userRepository.findUserById(id);
	}

	}
