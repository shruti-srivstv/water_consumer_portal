package it.polimi.awt.waterconsumer.repository;

import java.util.List;

import it.polimi.awt.waterconsumer.domain.*;

public interface UserRepository {
	public List<User> findAll();
	public User findUserById(Integer id);
	public User findUserbyUsername(String username, String password);	
	public NeutralUser findNeutralUserbyId(Integer id);	
	public NeutralUser findNeutralUserbyHouseholdId(Integer id);
	
}
