package it.polimi.awt.waterconsumer.service;

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
	
	public User findUserbyHouseholdId(Integer id){
		return userRepository.findUserbyHouseholdId(id);
	}
	
	public User findUserbyBuildingId(Integer id){
		return userRepository.findUserbyBuildingId(id);
	}
	public User findUserbySmartMeterId(Integer id){
		return userRepository.findUserbySmartMeterId(id);
	}
	public User findUserbyZipcode(String id){
		return userRepository.findUserbyZipcode(id);
	}
}
