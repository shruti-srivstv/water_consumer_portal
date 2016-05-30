package it.polimi.awt.waterconsumer.service;

import it.polimi.awt.waterconsumer.domain.User;
import it.polimi.awt.waterconsumer.exception.DuplicatedUsernameException;
import java.util.List;

public interface UserService {	
	public List<User> findAll();
	public User findUserById(Integer id);
	public User findUserbyUsername(String username);
//	public void saveOrUpdateBook(Book book) throws DuplicatedIsbnException;
//	public void removeBook(Integer id);
}