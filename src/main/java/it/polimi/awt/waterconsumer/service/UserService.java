package it.polimi.awt.waterconsumer.service;

import it.polimi.awt.waterconsumer.domain.User;
import java.util.List;

public interface UserService {	
	public List<User> findAll();
	public User findUserById(Integer id);
	
}