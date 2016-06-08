package it.polimi.awt.waterconsumer.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import it.polimi.awt.waterconsumer.domain.User;
import it.polimi.awt.waterconsumer.domain.*;
import it.polimi.awt.waterconsumer.service.UserService;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("session")
//@RequestMapping("/users")
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
//		System.out.print("++++++++++++++++++++++" + user.getNeutralUser());
		return "user/registrationHome";
	}	

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginPage(Model model, HttpServletRequest request){
		Integer userid = (Integer) request.getSession().getAttribute("userid");
		if (userid!=null){
			return "redirect:/portal";
		}
		else{
			return "login";
		}
	}
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public String return403Page(Model model){
		return "403";
	}
	
	@RequestMapping(value="/portal", method=RequestMethod.GET)
	public String showHomepage(Model model, HttpServletRequest request){
		Integer userid = (Integer) request.getSession().getAttribute("userid");
		if (userid!=null){
			User user = userService.findUserById(userid);
			model.addAttribute(user);
			return "user/home";
		}
		else {
			return "redirect:/login";
		}
	}
	

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String validateLogin(Model model, String username, String password, HttpServletRequest request){
		Integer userid = userService.findUserforLogin(username, password);
		if (userid!=null){
			request.getSession().setAttribute("userid", userid);
			return "redirect:/portal";
		}
		else{
			model.addAttribute("message", "Invalid Username/Password !");
			return "redirect:/403";
		}
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
*/		return "user/home";
	}
}