package it.polimi.awt.waterconsumer.repository;

import java.util.List;

import it.polimi.awt.waterconsumer.domain.User;

public interface UserRepository {
	public List<User> findAll();
	public User findUserById(Integer id);
	public User findUserbyUsername(String username);
//	public User findByIsbn(String isbn);
//	public void persistBook(Book book);
//	public void saveOrUpdateBook(Book book);
//	public void removeBook(Book book);
}
