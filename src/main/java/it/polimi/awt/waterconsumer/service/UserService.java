package it.polimi.awt.waterconsumer.service;

import it.polimi.awt.waterconsumer.domain.*;

import java.util.Date;
import java.util.List;

public interface UserService {	
	public List<User> findAll();
	public User findUserById(Integer id);
	public User findUserbyUsername(String username, String password);
	
	public User findUserbyHouseholdId(Integer id);
	public User findUserbyBuildingId(Integer id);
	public User findUserbySmartMeterId(Integer id);
	public User findUserbyZipcode(String id);
	
	public List<MeterReading> selectMeterReadingbyDate(Integer smartMeterId, Date startDate, Date endDate);
}