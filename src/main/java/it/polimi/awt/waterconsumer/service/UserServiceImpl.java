package it.polimi.awt.waterconsumer.service;

import it.polimi.awt.waterconsumer.domain.*;
import it.polimi.awt.waterconsumer.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
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
		List<User> user = userRepository.findAll();

		return user;
	}

	public User findUserById(Integer id) {
		User user = userRepository.findUserById(id);

		return user;
	}

	public Integer findUserbyUsername(String username, String password) {
		User user = userRepository.findUserbyUsername(username, password);

		Integer userId = user.getOid();

		return userId;
	}

	public Date getMaxDate(Integer smartMeterOid) {
		Date date = userRepository.getMaxDate(smartMeterOid);

		return date;
	}

	public Date getMinDate(Integer smartMeterOid) {
		Date date = userRepository.getMinDate(smartMeterOid);

		return date;
	}

	public List<MeterReading> selectMeterReadingbyDate(Integer smartMeterId, String granularity, Date dateValue) {
		List<Object[]> results = userRepository.selectMeterReadingbyDate(smartMeterId, granularity, dateValue);

		List<MeterReading> final_result = new ArrayList<MeterReading>(results.size());

		Float previous_meter_reading = new Float("0.0");
		Boolean count = false;

		for (Object[] result : results) {
			Float new_reading = (Float) result[1];
			MeterReading new_meter_reading = new MeterReading();
			new_meter_reading.setTotalConsumptionAdjusted(new_reading - previous_meter_reading);

			new_meter_reading.setReadingDateTime((Date) result[0]);
			if (count && (new_reading > previous_meter_reading)) {
				final_result.add(new_meter_reading);
			}

			previous_meter_reading = new_reading;
			count = true;
		}

		return final_result;
	}

	public Float getDailyAverage(Integer smartMeterId) {
		Object[] consumption = userRepository.getConsumptionBySMId(smartMeterId);
		
		Float minConsumption = (Float) consumption[1];
		Float maxConsumption = (Float) consumption[3];
		
		Date startDate = (Date) consumption[0];
		Date endDate = (Date) consumption[2];

		Float totalConsumption = maxConsumption - minConsumption;
		
		Integer totalDays = daysBetween(startDate, endDate);

		Float dailyAverage = totalConsumption / totalDays;

		return dailyAverage;
	}

	public int daysBetween(Date d1, Date d2) {
		int days = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
		return days;
	}

	public List<User> getNeighbourhood(String zipcode) {
		List<User> alluser = userRepository.findAll();

		List<User> newList = new ArrayList<User>();
		
		int i = 0;
		for (User us : alluser) {
			NeutralUser neutralUser = us.getNeutralUser();			
			try {
				Household household = neutralUser.getHousehold();
				SmartMeter smartmeter = household.getSmartMeter();
				Building building = household.getBuilding();
				District district = building.getDistrict();
				
				Integer householdId = household.getOid();
				Integer smartmeterId = smartmeter.getOid();
				Integer buildingId = building.getOid();
				Integer districtId = district.getOid();
				
				if(zipcode.equals(district.getZipcode())){
//					System.out.println(i+") Zipcode = " +district.getZipcode());
//					System.out.println(i+") smartMeter id  = "+smartmeter.getOid());
					newList.add(us);
					i++;
				}
			} catch (Exception e) {
				
			}

		}
		return newList;
	}
	
	public Float getDailyLocality(String zipcode){
		List<User> allNeighbour= getNeighbourhood(zipcode);
		
		System.out.println("Local Size = "+allNeighbour.size());
		Float totalNCon = (float) 0;
		int i = 0;
		for(User u : allNeighbour){
			try {
				i++;
				NeutralUser neutralUser = u.getNeutralUser();
				Household household = neutralUser.getHousehold();
				SmartMeter smartMeter = household.getSmartMeter();
				
				System.out.println(i+") smartMeter id  = "+smartMeter.getOid());
				
				Object[] consumption = userRepository.getMeterReadingConBySMId(smartMeter.getOid());
				Float totalCon = (Float) consumption[2];
				
				Date startDate = (Date) consumption[0];
				Date endDate = (Date) consumption[1];
				
				Integer dayDiff = daysBetween(startDate, endDate);
				
				Float dailyAve = totalCon / dayDiff;
				
				System.out.println(i+") smartMeter id  = "+smartMeter.getOid());
				System.out.println(i+") dailyAve  = "+dailyAve);
				
				totalNCon = totalNCon + dailyAve;
			} catch (Exception e) {
				
			}
		}
		
		Float localAve = totalNCon / allNeighbour.size();
		System.out.println("&&&&&&&&&&&& - localAve = "+localAve);
		
		Float localityAverage = (float) 5.1;
		return localAve;
	}

	public List<User> getUserMapView(){
		List<User> alluser = userRepository.findAll();

		List<User> newList = new ArrayList<User>();

		for (User us : alluser) {
			NeutralUser neutralUser = us.getNeutralUser();			
			try {
				Household household = neutralUser.getHousehold();
				SmartMeter smartmeter = household.getSmartMeter();
				Building building = household.getBuilding();
				District district = building.getDistrict();
				
				if(!building.getAddress().isEmpty()){					
					newList.add(us);
				}
			} catch (Exception e) {
				
			}

		}
		return newList;
	}
	

	public List<Object> getMapData(){
		List<User> user = getUserMapView();
		
		List<Object> newList = new ArrayList<Object>();		
		List<Object> row = new ArrayList<Object>();
		
		int i = 0;
		for(User us : user){
			i++;
			NeutralUser neutralUser = us.getNeutralUser();
			Household household = neutralUser.getHousehold();
			SmartMeter smartMeter = household.getSmartMeter();
			Building building = household.getBuilding();
			
			Integer r = userRepository.findCommonSM(building.getOid());
			System.out.println(i+") User id = "+us.getOid());
			
			String commonS;
			
			if(r > 1)
				commonS = "Common Smartmeter";
			else
				commonS = "Individual Smartmeter";
			
			Integer smartMeterOid = smartMeter.getOid();
			
			Object[] consumption = userRepository.getMeterReadingConBySMId(smartMeterOid);
			Float totalCon = (Float) consumption[2];
			
			Date startDate = (Date) consumption[0];
			Date endDate = (Date) consumption[1];
			
			Integer dayDiff = daysBetween(startDate, endDate);
			
			Float dailyAve = totalCon / dayDiff;
			Float weeklyAve = dailyAve / 7;
			Float monthlyAve = dailyAve / 30;
			
			row.add(commonS);
			row.add(totalCon);
			row.add(dailyAve);
			row.add(weeklyAve);
			row.add(monthlyAve);
		}		
		newList.add(row);
		
		return newList;
	}
}
