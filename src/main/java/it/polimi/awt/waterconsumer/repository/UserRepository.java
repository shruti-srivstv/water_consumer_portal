package it.polimi.awt.waterconsumer.repository;

import java.util.List;

import it.polimi.awt.waterconsumer.domain.User;

public interface UserRepository {
	public List<User> findAll();
	public User findUserById(Integer id);
	public Integer findUserforLogin(String username, String password);
//	public User findByIsbn(String isbn);
//	public void persistBook(Book book);
//	public void saveOrUpdateBook(Book book);
//	public void removeBook(Book book);
}
