package it.polimi.awt.waterconsumer.repository;

import java.util.Date;
import java.util.List;

import it.polimi.awt.waterconsumer.domain.*;

public interface UserRepository {
	public List<User> findAll();
	public User findUserById(Integer id);
	public Integer findUserforLogin(String username, String password);
//	public User findByIsbn(String isbn);
//	public void persistBook(Book book);
//	public void saveOrUpdateBook(Book book);
//	public void removeBook(Book book);
//	public User findUserbyUsername(String username, String password);	
	
	public User findUserbyHouseholdId(Integer id);
	public User findUserbyBuildingId(Integer id);
	public User findUserbySmartMeterId(Integer id);
	public User findUserbyZipcode(String id);
	
	public List<MeterReading> selectMeterReadingbyDate(Integer smartMeterId, Date startDate, Date endDate);
	
}
