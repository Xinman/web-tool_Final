package com.neu.project;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.project.dao.AppointmentDAO;
import com.neu.project.dao.DoctorDAO;
import com.neu.project.dao.PatientDAO;
import com.neu.project.dao.UserAccountDAO;
import com.neu.project.model.Appointment;
import com.neu.project.model.Doctor;
import com.neu.project.model.Patient;
import com.neu.project.model.ResultDTO;
import com.neu.project.model.ResultEmail;
import com.neu.project.model.UserAccount;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private PatientDAO paDAO;

	@Autowired
	private DoctorDAO docDAO;

	@Autowired
	private AppointmentDAO appointDAO;

	@Autowired
	private UserAccountDAO uaDAO;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpServletRequest req) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Cookie[] cookies = req.getCookies();
		HttpSession session = req.getSession();
		String name = "";
		String password = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username"))
					name = cookie.getValue();
				if (cookie.getName().equals("password"))
					password = cookie.getValue();
			}
			try {
				UserAccount ua = uaDAO.findByUsernameAndPassword(name, password);
				if (ua != null) {
					int role_id = ua.getRole_id();
					//System.out.println("?????????????????????????? "+role_id);
					if (1 == role_id) {
						try {
							// if("Patient".equals(role)){
							Patient pa = paDAO.findUserByNameAndPassword(name, password);
							if (pa != null) {
								//System.out.println("1111111111111111111111111111111111111");
								session.setAttribute("patient", pa);
								List appoints = appointDAO.ListAppointmentByPatientName(pa.getPaUsername());
								List doctors = docDAO.ListDoctorByDegreeInDsc();
								model.addAttribute("appointmentList", appoints);
								model.addAttribute("patient", pa);
								model.addAttribute("docList", doctors);
								return "patientHome";
								// }
							}

							}catch (Exception e) {
								e.printStackTrace();
							} 
					}
						else if(2==role_id) {
								try{
								//System.out.println("222222222222222222222222222222");
								Doctor doc = docDAO.findDoctorByNameAndPassword(name,password);
								if (doc != null) {
									session.setAttribute("doctor", doc);
									List<Appointment> sentAppoints = new ArrayList<Appointment>();
									List appoints = appointDAO.findAppointmentByDoctorNameMAX10(name);
									for (int i = 0; i < appoints.size(); i++) {
										Appointment app = (Appointment) appoints.get(i);
										if ("Sent".equals(app.getStatus())) {
											sentAppoints.add(app);

											
											// }
										}
										
									}
									model.addAttribute("appoints", sentAppoints);
									model.addAttribute("doctor", doc);
									return "doctorHome";
								}

							}catch (Exception e) {
								e.printStackTrace();
							}
						
					}
				}
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submitForm(Model model, HttpServletRequest req, @Valid Patient patient,BindingResult result) throws Exception {
		//String submission = req.getParameter("submit");

		//if ("Join us".equals(submission)) {
		if (result.hasErrors()) {
			return "home";
		}else{
			String username = patient.getPaUsername();
			String email = patient.getContactemail();
			String password1 = patient.getPapassword();
			// String password2 = req.getParameter("password2");
			uaDAO.saveUserAccount(username, password1,email);
			paDAO.addUserAccount(patient);
			List doctors = docDAO.ListDoctorByDegreeInDsc();
			model.addAttribute("docList", doctors);

			return "patientHome";

		//}
		//if ("Aleardy have Account. Go >>".equals(submission)) {

		//	return "userLogin";
		//}
		//return "home";
	}
	}
	
	@RequestMapping(value = "/isExist",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResultDTO isExist(Model model,@RequestBody String userName) {
	    
		ResultDTO result = new ResultDTO();
		//System.out.println("    "+email);
		String username= userName.replace("username=", "");
		System.out.println("   name    jquery   "+username);
		boolean success = uaDAO.isExistUserName(username);
        System.out.println("success       "+success);
		result.setSuccess(success);
			
		if(success) {
			result.setMessage("<font>Username Exists, Login?</font>");
		} else {
			result.setMessage("<font></font>");
		}
		return result;
	}
	
	@RequestMapping(value = "/checkEmail",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResultEmail checkEmail(Model model,@RequestBody String email) {
		ResultEmail results = new ResultEmail();
		System.out.println("    "+email);
		String username= email.replace("email=", "").replace("%40", "@");
		boolean success = uaDAO.isExistUserName(username);
		System.out.println("    "+username);
		results.setSuccess(success);
		
		if(success) {
			results.setMessage("<font>Email Exsits, Login?</font>");
		} else {
			results.setMessage("<font></font>");
		}
		return results;
	}
	
	@RequestMapping(value = "/checkSSN",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody ResultEmail checkSSN(Model model,@RequestBody String ssn) throws Exception {
		ResultEmail results = new ResultEmail();
		System.out.println("    "+ssn);
		String s= ssn.replace("ssn=", "");
		System.out.println("    "+ssn);
		boolean success = paDAO.isExistSSN(s);
		results.setSuccess(success);
		
		if(success) {
			results.setMessage("<font>Invalid SSN, Try Again?</font>");
		} else {
			results.setMessage("<font></font>");
		}
		return results;
	}

	@RequestMapping(value = "/accountLogin", method = RequestMethod.GET)
	public String accountLogin(){
		
		return "userLogin";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest req,HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		String name = req.getParameter("euAddress");
		//System.out.println("!!!!!!!!!!!!!!!!!!!!! "+name);
		String password = req.getParameter("password");
		String remember = req.getParameter("checkbox");
		// String role = req.getParameter("role");
		UserAccount ua = uaDAO.findByUsernameAndPassword(name, password);
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+ua);
		if (ua != null) {
			int role_id = ua.getRole_id();
			//System.out.println("?????????????????????????? "+role_id);
			if (1 == role_id) {
				try {
					// if("Patient".equals(role)){
					Patient pa = paDAO.findUserByNameAndPassword(name, password);
					if (pa != null) {
						//System.out.println("1111111111111111111111111111111111111");
						session.setAttribute("patient", pa);
						List appoints = appointDAO.ListAppointmentByPatientName(pa.getPaUsername());
						List doctors = docDAO.ListDoctorByDegreeInDsc();
						if ("rememberme".equals(remember)) {
							
							//System.out.println("/.///////////////////////////////////////////");

							Cookie c1 = new Cookie("username", pa.getPaUsername());
							Cookie c2 = new Cookie("password", pa.getPapassword());
							c1.setMaxAge(60);
							c2.setMaxAge(60);
							res.addCookie(c1);
							res.addCookie(c2);

						}
						model.addAttribute("appointmentList", appoints);
						model.addAttribute("patient", pa);
						model.addAttribute("docList", doctors);
						return "patientHome";
						// }
					}

					}catch (Exception e) {
						e.printStackTrace();
					} 
			}
				else if(2==role_id) {
						try{
						//System.out.println("222222222222222222222222222222");
						Doctor doc = docDAO.findDoctorByNameAndPassword(name,password);
						if (doc != null) {
							session.setAttribute("doctor", doc);
							List<Appointment> sentAppoints = new ArrayList<Appointment>();
							List appoints = appointDAO.findAppointmentByDoctorNameMAX10(name);
							for (int i = 0; i < appoints.size(); i++) {
								Appointment app = (Appointment) appoints.get(i);
								if ("Sent".equals(app.getStatus())) {
									sentAppoints.add(app);

									
									// }
								}
								
							}
							if ("rememberme".equals(remember)) {
								
								//System.out.println("/.///////////////////////////////////////////");

								Cookie c1 = new Cookie("username", doc.getDocUsername());
								Cookie c2 = new Cookie("password", doc.getDocPassword());
								c1.setMaxAge(60);
								c2.setMaxAge(60);
								res.addCookie(c1);
								res.addCookie(c2);

							}
							model.addAttribute("appoints", sentAppoints);
							model.addAttribute("doctor", doc);
							return "doctorHome";
						}

					}catch (Exception e) {
						e.printStackTrace();
					}
				
			}
		}
		return "userLogin";

	}

	@RequestMapping(value = "/user/patientHomePage", method = RequestMethod.GET)
	public String returnUserHomePage(Model model, HttpServletRequest req)
			throws Exception {

		HttpSession session = req.getSession();
		Patient p = (Patient) session.getAttribute("patient");
		List appoints = appointDAO.ListAppointmentByPatientNameMAX10(p.getPaUsername());
		List doctors = docDAO.ListDoctorByDegreeInDsc();
		model.addAttribute("appointmentList", appoints);
		model.addAttribute("patient", p);
		model.addAttribute("docList", doctors);
		return "userPatientHome";
	}
	
	@RequestMapping(value = "/patientHomePage", method = RequestMethod.GET)
	public String returnHomePage(Model model, HttpServletRequest req)
			throws Exception {

		HttpSession session = req.getSession();
		Patient p = (Patient) session.getAttribute("patient");
		List appoints = appointDAO.ListAppointmentByPatientNameMAX10(p.getPaUsername());
		List doctors = docDAO.ListDoctorByDegreeInDsc();
		model.addAttribute("appointmentList", appoints);
		model.addAttribute("patient", p);
		model.addAttribute("docList", doctors);
		return "patientHome";
	}

	@RequestMapping(value = "/user/doctorHome", method = RequestMethod.GET)
	public String returnUserDoctorHomePage(Model model, HttpServletRequest req)
			throws Exception {

		HttpSession session = req.getSession();
		Doctor d = (Doctor) session.getAttribute("doctor");
		List<Appointment> sentAppoints = new ArrayList<Appointment>();
		List appoints = appointDAO.findAppointmentByDoctorNameMAX10(d
				.getDocUsername());
		for (int i = 0; i < appoints.size(); i++) {
			Appointment app = (Appointment) appoints.get(i);
			if ("Sent".equals(app.getStatus())) {
				sentAppoints.add(app);
			}
		}
		model.addAttribute("appoints", sentAppoints);
		model.addAttribute("doctor", d);
		return "userDoctorHome";
	}
	
	@RequestMapping(value = "/doctorHome", method = RequestMethod.GET)
	public String returnDoctorHomePage(Model model, HttpServletRequest req)
			throws Exception {

		HttpSession session = req.getSession();
		Doctor d = (Doctor) session.getAttribute("doctor");
		List<Appointment> sentAppoints = new ArrayList<Appointment>();
		List appoints = appointDAO.findAppointmentByDoctorNameMAX10(d
				.getDocUsername());
		for (int i = 0; i < appoints.size(); i++) {
			Appointment app = (Appointment) appoints.get(i);
			if ("Sent".equals(app.getStatus())) {
				sentAppoints.add(app);
			}
		}
		model.addAttribute("appoints", sentAppoints);
		model.addAttribute("doctor", d);
		return "doctorHome";
	}
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest req,HttpServletResponse res){
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				cookies[i].setValue("");
				cookies[i].setPath("/");
	    		cookies[i].setMaxAge(-1);
	    		res.addCookie(cookies[i]);
	    		//System.out.println("cookie "+cookies[i].getValue());
			}
		}
		session.invalidate();
		
		return "Login";
	}
	
	
}
