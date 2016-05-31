package it.polimi.awt.waterconsumer.service;

import it.polimi.awt.waterconsumer.domain.*;
import java.util.List;

public interface UserService {	
	public List<User> findAll();
	public User findUserById(Integer id);
	public User findUserbyUsername(String username, String password);
	public NeutralUser findNeutralUserbyId(Integer id);
	public NeutralUser findNeutralUserbyHouseholdId(Integer id);
}