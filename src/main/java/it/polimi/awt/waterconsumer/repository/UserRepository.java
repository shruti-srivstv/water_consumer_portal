package it.polimi.awt.waterconsumer.repository;

import java.util.Date;
import java.util.List;

import it.polimi.awt.waterconsumer.domain.*;

public interface UserRepository {
	public List<User> findAll();
	public User findUserById(Integer id);
	public User findUserbyUsername(String username, String password);	
		
	public Date getMaxDate(Integer smartMeterOid);
	public Date getMinDate(Integer smartMeterOid);
	
	public List<Object[]> selectMeterReadingbyDate(Integer smartMeterId, String granularity, Date dateValue);
	
	public Float getMinConsumptionByDate(Integer smartMeterId, Date startDate);
	public Float getMaxConsumptionByDate(Integer smartMeterId, Date endDate);
	
	public Object[] getConsumptionBySMId(Integer smartMeterId);
	
	//public List<MeterReading> getMeterReadingByListSMId(List<Object> smartMeterOid);
	public Object[] getMeterReadingConBySMId(Integer smartMeterOid);
	
	public Integer findCommonSM(Integer buildingId);
}
