package it.polimi.awt.waterconsumer.service;

import it.polimi.awt.waterconsumer.domain.NeutralUser;
import it.polimi.awt.waterconsumer.domain.User;
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

	public User findUserbyUsername(String username, String password) {
		return userRepository.findUserbyUsername(username, password);
	}

	public User findUserById(Integer id) {
		return userRepository.findUserById(id);
	}

	public NeutralUser findNeutralUserbyId(Integer id) {
		return userRepository.findNeutralUserbyId(id);
	}
	
	public NeutralUser findNeutralUserbyHouseholdId(Integer id){
		return userRepository.findNeutralUserbyHouseholdId(id);
	}
}
