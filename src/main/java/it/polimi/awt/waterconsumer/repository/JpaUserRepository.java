package it.polimi.awt.waterconsumer.repository;

import it.polimi.awt.waterconsumer.domain.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRepository implements UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);		
		List<User> alluser = query.getResultList();
		
		return alluser;
	}
	
	public User findUserById(Integer id) {
		return em.find(User.class, id);
	}	
	
	public User findUserbyUsername(String username, String password){
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username and u.password = :password", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		User result;
		try{
			result = query.getSingleResult();
		}
		catch(NoResultException e){
			result = null;
		}
		
		return result;
	}	
		
	public Date getMaxDate(Integer smartMeterOid){
		TypedQuery<Date> query = em.createQuery("SELECT MAX(DATE(m.readingDateTime)) FROM MeterReading m WHERE m.smartMeterOid = :smartID",Date.class);
		query.setParameter("smartID", smartMeterOid);
		
		Date result = query.getSingleResult();
		
		return result;
	}
	
	public Date getMinDate(Integer smartMeterOid){
		TypedQuery<Date> query = em.createQuery("SELECT MIN(DATE(m.readingDateTime)) FROM MeterReading m WHERE m.smartMeterOid = :smartID",Date.class);
		query.setParameter("smartID", smartMeterOid);
		
		Date result = query.getSingleResult();
		
		return result;
	}
	
	public List<Object[]> selectMeterReadingbyDate(Integer smartMeterId, String granularity, Date dateValue){
		TypedQuery<Object[]> query;
		System.out.println("granularity");
		if ("week".equals(granularity)){
			System.out.println("week");
			query = em.createQuery("SELECT mrs.readingDateTime, MAX(mrs.totalConsumptionAdjusted) FROM MeterReading mrs WHERE mrs.smartMeterOid = :smartID GROUP BY YEARWEEK(mrs.readingDateTime)",
					Object[].class);
		}
		else if ("month".equals(granularity)){
			System.out.println("month");
			query = em.createQuery("SELECT mrs.readingDateTime, MAX(mrs.totalConsumptionAdjusted) FROM MeterReading mrs WHERE mrs.smartMeterOid = :smartID GROUP BY YEAR(mrs.readingDateTime), MONTH(mrs.readingDateTime)",
					Object[].class);
		}
		else if ("hour".equals(granularity) && dateValue !=null){
			System.out.println("day and date = " + dateValue);
			query = em.createQuery("SELECT mrs.readingDateTime, mrs.totalConsumptionAdjusted FROM MeterReading mrs WHERE mrs.smartMeterOid = :smartID AND DATE(mrs.readingDateTime) = :dateValue",
					Object[].class);
			query.setParameter("dateValue", dateValue);
		}
		else{
			System.out.println("day");
			query = em.createQuery("SELECT mrs.readingDateTime, MAX(mrs.totalConsumptionAdjusted) FROM MeterReading mrs WHERE mrs.smartMeterOid = :smartID GROUP BY DATE(mrs.readingDateTime)",
					Object[].class);
		}
		query.setParameter("smartID", smartMeterId);		
	
		List<Object[]> results = query.getResultList();
				
		return results;
	}
	
	public Float getMinConsumptionByDate(Integer smartMeterId, Date startDate){
		TypedQuery<Float> query = em.createQuery("SELECT MIN(mrs.totalConsumptionAdjusted) FROM MeterReading mrs "
				+ "WHERE mrs.smartMeterOid = :smartID "
				+ "AND DATE(mrs.readingDateTime) = :startDate", Float.class); 
		query.setParameter("smartID", smartMeterId);
		query.setParameter("startDate", startDate);
		
		Float minConsumption = query.getSingleResult();
		
		return minConsumption;
	}
	
	public Float getMaxConsumptionByDate(Integer smartMeterId, Date endDate){
		TypedQuery<Float> query = em.createQuery("SELECT MAX(mrs.totalConsumptionAdjusted) FROM MeterReading mrs "
				+ "WHERE mrs.smartMeterOid = :smartID "
				+ "AND DATE(mrs.readingDateTime) = :endDate", Float.class);														
		query.setParameter("smartID", smartMeterId);
		query.setParameter("endDate", endDate);
		
		Float maxConsumption = query.getSingleResult();
		
		return maxConsumption;		
	}
	
	
	
	public Integer findCommonSM(Integer buildingOid){
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(h) FROM Household h WHERE :buildingOid = h.building.oid",Long.class); 
		query.setParameter("buildingOid", buildingOid);
		
		
		Integer result = (int)(long) query.getSingleResult();
				 
		return result;
	}
	
	public Object[] getConsumptionBySMId(Integer smartMeterId){
		TypedQuery<Object[]> query = em.createQuery("SELECT MIN(mrs.readingDateTime), MIN(mrs.totalConsumptionAdjusted), MAX(mrs.readingDateTime), MAX(mrs.totalConsumptionAdjusted) FROM MeterReading mrs WHERE mrs.smartMeterOid = :smartID",Object[].class); 
		query.setParameter("smartID", smartMeterId);
		
		Object[] result = query.getSingleResult();
		
		return result;
	}
	
	public Object[] getMeterReadingConBySMId(Integer smartMeterOid){
		TypedQuery<Object[]> query = em.createQuery("SELECT MIN(mrs.readingDateTime), MAX(mrs.readingDateTime), MAX(mrs.totalConsumptionAdjusted)-MIN(mrs.totalConsumptionAdjusted) FROM MeterReading mrs WHERE mrs.smartMeterOid = :smartID",Object[].class); 
		query.setParameter("smartID", smartMeterOid);
		
		Object[] result = query.getSingleResult();
		
		return result;
	}
	
//	public List<User> getUserFromZipcode(Integer zipcode){
//		TypedQuery<Integer> buildingQuery = em.createQuery("SELECT oid FROM building WHERE districtOid = :districtID", Integer.class);
//	}
	
//	public Float getLocalityAverage(User user, Date startDate, Date endDate){
//		Integer districtOid = user.getNeutralUser().getHousehold().getBuilding().getDistrict().getOid();
//		TypedQuery<Integer> buildingQuery = em.createQuery("SELECT oid FROM building WHERE districtOid = :districtID", Integer.class);
//		buildingQuery.setParameter("districtID", districtOid);
//		List<Integer> buildingIds = buildingQuery.getResultList();
//		TypedQuery<Integer> smQuery = em.createQuery("SELECT DISTINCT (smartMeterOid) FROM household WHERE buildingOid IN (:buildingIdList)", Integer.class);
//		smQuery.setParameter("buildingIdList", buildingIds);
//		List<Integer> smIds = smQuery.getResultList();
//		Float startConsumption, endConsumption;
//		TypedQuery<Float> query;
//		query = em.createQuery("SELECT MIN(mrs.totalConsumptionAdjusted) FROM SmartMeter sm JOIN sm.meterReadings mrs WHERE sm.oid = :smartID AND DATE(mrs.readingDateTime) = :dateValue",
//				Float.class);
//		//query.setParameter("smartID", smOid);
//		query.setParameter("dateValue", startDate);
//		startConsumption = query.getSingleResult();
//		query = em.createQuery("SELECT MAX(mrs.totalConsumptionAdjusted) FROM SmartMeter sm JOIN sm.meterReadings mrs WHERE sm.oid = :smartID AND DATE(mrs.readingDateTime) = :dateValue",
//				Float.class);
//		//query.setParameter("smartID", smOid);
//		query.setParameter("dateValue", endDate);
//		endConsumption  = query.getSingleResult();
//		
//		Integer days = daysBetween(startDate, endDate);
//		Float dailyAverage;
//		dailyAverage =  (endConsumption -startConsumption)/days;
//		return dailyAverage;
//	}

}
