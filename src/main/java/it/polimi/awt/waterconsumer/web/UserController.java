package it.polimi.awt.waterconsumer.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import it.polimi.awt.waterconsumer.domain.*;
import it.polimi.awt.waterconsumer.service.UserService;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
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
		
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String getRegistrationPage(Model model){
		return "registration";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String showRegistrationResult(Model model, Integer householdId){
		User user = userService.findUserbyHouseholdId(householdId);
		
		System.out.print("++++++++++++++++++++++" + user.getNeutralUser());
		return "user/registrationHome";
	}	

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginPage(Model model){
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String showHomepage(HttpSession session, Model model, String username, String password){
		User user = userService.findUserbyUsername(username, password);
		
		if(user == null){
			model.addAttribute("message", "Invalid Username/Password !");
			return "login";
		}
		
		session.setAttribute("user", user);
		
		model.addAttribute("user", user);
		
		System.out.print("++++++++++++++++++++++" + user.getNeutralUser());
		return "user/home";
	}	
	
	@RequestMapping(value="/user/home", method=RequestMethod.GET)
	public String showMeterReading(HttpSession session, Model model, Date startDate, Date endDate){
		User sessionUser = (User) session.getAttribute("user");
		List<MeterReading> meterReading = userService.selectMeterReadingbyDate(sessionUser.getNeutralUser().getHousehold().getSmartMeter().getOid(), startDate, endDate);
		
		model.addAttribute("meterReading", meterReading);
		
		return "user/home";
	}

	@ResponseBody
	@RequestMapping(value="/getConsumption", method=RequestMethod.GET)
	public Map<String,Object> showGetConsumption(HttpSession session, @RequestParam(name="from",required=true) String startDate, @RequestParam(name="to",required=true)String endDate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		
		User sessionUser = (User) session.getAttribute("user");
		List<MeterReading> meterReading = userService.selectMeterReadingbyDate(sessionUser.getNeutralUser().getHousehold().getSmartMeter().getOid(), sdf.parse(startDate), sdf.parse(endDate));
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("name", "amin");
		map.put("meters", meterReading);
		return map;
	}

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String getAllUsers(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute(users);
		
		return "user/list";
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public String getUserDetails(@PathVariable Integer id, Model model) {
/*		User user = userService.findUserById(id);
		model.addAttribute(user);
*/		return "user/homepage";
	}
}