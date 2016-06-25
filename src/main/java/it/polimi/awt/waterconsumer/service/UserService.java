package it.polimi.awt.waterconsumer.service;

import it.polimi.awt.waterconsumer.domain.*;

import java.util.Date;
import java.util.List;

public interface UserService {	
	public List<User> findAll();
	public User findUserById(Integer id);
	public Integer findUserbyUsername(String username, String password);
	
	public List<MeterReading> selectMeterReadingbyDate(Integer smartMeterId, String granularity, Date dateValue);
	
	public Date getMaxDate(Integer smartMeterOid);
	public Date getMinDate(Integer smartMeterOid);
	
	public Float getDailyAverage(Integer smartMeterOid);
	
	public Float getDailyLocality(String zipcode);
		
	public List<Object> getMapData();
}