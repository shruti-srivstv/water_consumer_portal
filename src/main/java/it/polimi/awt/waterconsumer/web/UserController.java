package it.polimi.awt.waterconsumer.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import it.polimi.awt.waterconsumer.domain.*;
import it.polimi.awt.waterconsumer.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// @RequestMapping(value="/registration", method=RequestMethod.GET)
	// public String getRegistrationPage(Model model){
	// return "registration";
	// }

	// @RequestMapping(value="/register", method=RequestMethod.POST)
	// public String showRegistrationResult(Model model, Integer householdId){
	// User user = userService.findUserbyHouseholdId(householdId);
	//
	// System.out.print("++++++++++++++++++++++" + user.getNeutralUser());
	// return "user/registrationHome";
	// }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(Model model, HttpSession session) {
		Integer userid = (Integer) session.getAttribute("userid");
		if (userid == null) {
			return "login";
		}

		return "redirect:/portal";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showHomepage(HttpSession session, Model model,
			String username, String password) {
		Integer userid = userService.findUserbyUsername(username, password);
		if (userid == null) {
			model.addAttribute("message", "Invalid Username/Password !");
			return "login";
		}
		session.setAttribute("userid", userid);

		return "redirect:/portal";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String showHomepage(HttpSession session, Model model) {
		session.setAttribute("userid", null);
		return "redirect:/login";
	}

	@RequestMapping(value = "/portal", method = RequestMethod.GET)
	public String showMeterReading(HttpSession session, Model model,
			Date startDate, Date endDate) {
		Integer userid = (Integer) session.getAttribute("userid");

		if (userid != null) {
			User user = userService.findUserById(userid);
			model.addAttribute("user", user);

			Date maxDate = userService.getMaxDate(user.getNeutralUser()
					.getHousehold().getSmartMeter().getOid());
			Date minDate = userService.getMinDate(user.getNeutralUser()
					.getHousehold().getSmartMeter().getOid());

			model.addAttribute("maxDate", maxDate);
			model.addAttribute("minDate", minDate);
			return "user/home";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String showMap(HttpSession session, Model model) {
		Integer userid = (Integer) session.getAttribute("userid");
		if (userid != null) {
			User user = userService.findUserById(userid);
			model.addAttribute("user", user);
			
			return "user/map";
		} else {
			return "redirect:/login";
		}
	}

	public static Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	@RequestMapping(value = "/consumption", method = RequestMethod.GET)
	public @ResponseBody
	List<Object> showGetConsumption(HttpSession session,
			@RequestParam(name = "gran", required = true) String granularity,
			@RequestParam(name = "date", required = false) String date)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateValue = null;

		if (date != null) {
			dateValue = sdf.parse(date);
		}

		Integer userid = (Integer) session.getAttribute("userid");

		User sessionUser = userService.findUserById(userid);
		SmartMeter sessionSmartMeter = sessionUser.getNeutralUser()
				.getHousehold().getSmartMeter();
		Integer smartMeterId = sessionSmartMeter.getOid();

		List<MeterReading> meterReading;
		meterReading = userService.selectMeterReadingbyDate(smartMeterId,
				granularity, dateValue);

		List<Object> json = new ArrayList<Object>();
		for (MeterReading mr : meterReading) {
			List<Object> row = new ArrayList<Object>();
			if (!("hour").equals(granularity)) {
				row.add(removeTime(mr.getReadingDateTime()));
			} else {
				row.add(mr.getReadingDateTime());
			}

			row.add(mr.getTotalConsumptionAdjusted());

			json.add(row);
		}

		return json;
	}

	@RequestMapping(value = "/user/average", method = RequestMethod.GET)
	public @ResponseBody
	Float getDailyAverage(HttpSession session, @RequestParam(name = "startDate", required = true) String startDate, @RequestParam(name = "endDate", required = true) String endDate) throws Exception {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Date startDateValue = sdf.parse(startDate);
		
		//Date endDateValue = sdf.parse(endDate);

		Integer userid = (Integer) session.getAttribute("userid");

		User user = userService.findUserById(userid);
		SmartMeter sm = user.getNeutralUser().getHousehold().getSmartMeter();
		Float dailyAverage = userService.getDailyAverage(sm.getOid());

		return dailyAverage;
	}

	@RequestMapping(value = "/mapData", method = RequestMethod.GET)
	public @ResponseBody
	List<Object> getMapData() {
		List<Object> newList = userService.getMapData();

		return newList;
	}

	@RequestMapping(value = "/locality/average", method = RequestMethod.GET)
	public @ResponseBody
	Float getLocalityAverage(HttpSession session, @RequestParam(name = "startDate", required = true) String startDate, @RequestParam(name = "endDate", required = true) String endDate) throws Exception {
		System.out.print("getting locality average for" + startDate + " to " + endDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDateValue = sdf.parse(startDate);
		
		Date endDateValue = sdf.parse(endDate);
		Integer userid = (Integer) session.getAttribute("userid");

		User user = userService.findUserById(userid);
		Household household = user.getNeutralUser().getHousehold();
		Building building = household.getBuilding();
		District district = building.getDistrict();

		String zipcode = district.getZipcode();
		System.out.println("~~~~~~~~~~~~Zipcode = " + zipcode);

		Float dailyAverage = userService.getDailyLocality(zipcode);
		// dailyAverage = userService.getLocalityAverage(user, startDateValue,
		// endDateValue);

		return dailyAverage;
	}

	/*
	 * @RequestMapping(value="/locality/average", method=RequestMethod.GET)
	 * public @ResponseBody Float getLocalityAverage(HttpSession session,
	 * @RequestParam(name="startDate", required = true) String startDate,
	 * @RequestParam(name="endDate", required = true) String endDate) throws
	 * Exception{ System.out.print("getting locality average for" + startDate +
	 * " to " + endDate); SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy-MM-dd"); Date startDateValue =
	 * sdf.parse(startDate);; Date endDateValue = sdf.parse(endDate); Integer
	 * userid = (Integer) session.getAttribute("userid"); User user
	 * =userService.findUserById(userid); Float dailyAverage; //dailyAverage =
	 * userService.getLocalityAverage(user, startDateValue, endDateValue);
	 * 
	 * return dailyAverage; }
	 */

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute(users);

		return "user/list";
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String getUserDetails(@PathVariable Integer id, Model model) {
		/*
		 * User user = userService.findUserById(id); model.addAttribute(user);
		 */return "user/homepage";
	}
}