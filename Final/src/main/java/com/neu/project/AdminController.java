package com.neu.project;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.project.dao.AdminDAO;
import com.neu.project.dao.DoctorDAO;
import com.neu.project.dao.UserAccountDAO;
import com.neu.project.model.Admin;
import com.neu.project.model.Doctor;
import com.neu.project.model.UserAccount;

@Controller
public class AdminController {

	private static Log logger = LogFactory.getLog(AdminController.class);
	@Autowired
	private AdminDAO adDAO;

	@Autowired
	private DoctorDAO docDAO;

	@Autowired
	private UserAccountDAO uaDAO;

	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	public String adminLogin(Model model, HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		HttpSession session = req.getSession();
		String username = "";
		String password = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username"))
					username = cookie.getValue();
				if (cookie.getName().equals("password"))
					password = cookie.getValue();
			}
			try {
				Admin ad = adDAO.findByUsernaemAndPassword(username, password);
				if (ad != null) {
					session.setAttribute("admin", ad);
					model.addAttribute("admin", ad);
					return "adminHomePage";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Admin ad = new Admin();
		model.addAttribute("admin", ad);

		return "adminLogin";
	}

	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public ModelAndView adminLoginToHomePage(Model model, HttpServletRequest req,
			HttpServletResponse res) {

		HttpSession session = req.getSession();
		ModelAndView mv= new ModelAndView();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("checkbox");
		try {
			Admin ad = adDAO.findByUsernaemAndPassword(username, password);
			
			if (ad != null) {
				session.setAttribute("admin", ad);
				model.addAttribute("admin", ad);
				if ("rememberme".equals(remember)) {
					
					//System.out.println("/.///////////////////////////////////////////");

					Cookie c1 = new Cookie("username", ad.getAdminUsername());
					Cookie c2 = new Cookie("password", ad.getAdminPassword());
					c1.setMaxAge(60);
					c2.setMaxAge(60);
					res.addCookie(c1);
					res.addCookie(c2);

				}
				mv.addObject("admin", ad);
				mv.setViewName("adminHomePage");
			
				return mv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("adminLogin");
		
		return mv;
	}

	@RequestMapping(value = "/admin/adminManageDoctor", method = RequestMethod.GET)
	public String adminManageDoctor(Model model, HttpServletRequest req)
			throws Exception {

		List docList = docDAO.adminViewAllDoctor();
		if (!docList.isEmpty()) {
			model.addAttribute("docList", docList);
			return "adminManageDoctor";
		}
		return "adminHomePage";

	}

	@RequestMapping(value = "/admin/adminMakeNewDoctor", method = RequestMethod.GET)
	public String adminMakeNewDoctor(Model model, HttpServletRequest req)
			throws Exception {

		Doctor d = new Doctor();

		model.addAttribute("doctor", d);

		return "adminMakeNewDoctor";

	}

	@RequestMapping(value = "/admin/adminMakeNewDoctor", method = RequestMethod.POST)
	public String adminMakeDoctor(Model model, @Valid @ModelAttribute("doctor")Doctor doc,BindingResult res,
			HttpServletRequest req) throws Exception {

		if (res.hasErrors()) {
		
			return "adminMakeNewDoctor";
		}else{
		doc.setRole_id(2);
		UserAccount ua = new UserAccount();
		ua.setEmail(doc.getEmail());
		ua.setPassword(doc.getDocPassword());
		ua.setRole_id(2);
		ua.setUsername(doc.getDocUsername());
		uaDAO.saveDoctorUserAccount(ua);
		docDAO.SaveDoctor(doc);
		return adminManageDoctor(model, req);
	}
	}

	@RequestMapping(value = "/admin/adminDeleteDoctors", method = RequestMethod.GET)
	public String adminDeleteDoctors(Model model, HttpServletRequest req)
			throws NumberFormatException, Exception {
		String docID = req.getParameter("docID");
		int docId = Integer.parseInt(docID);
		Doctor d = docDAO.findDoctorByDocId(docId);
		// System.out.println(":::::::::::::::::::::::::::: "+d.getDocUsername());
		uaDAO.deleteUserAccountByDoctorname(d.getDocUsername());
		docDAO.deleteDoctor(docId);

		return adminManageDoctor(model, req);
	}

	@RequestMapping(value = "/admin/adminViewDetails", method = RequestMethod.GET)
	public String adminViewDetails(Model model, HttpServletRequest req)
			throws Exception {
		String docid = req.getParameter("docID");
		int docID = Integer.parseInt(docid);
		Doctor doc = docDAO.findDoctorByDocId(docID);

		Doctor changeDoctor = new Doctor();
		if (doc != null) {
			model.addAttribute("changeDoc", changeDoctor);
			model.addAttribute("doctor", doc);
			return "adminViewDoctorDetails";
		}
		return adminManageDoctor(model, req);
	}

	@RequestMapping(value = "/admin/adminChangeDocInformation", method = RequestMethod.POST)
	public String adminChangeDocInforamtion(Model model,
			@ModelAttribute Doctor changeDoctor, HttpServletRequest req)
			throws Exception {
		String description = req.getParameter("description");
		changeDoctor.setDescription(description);
		docDAO.updateDoctorInformation(changeDoctor);
		uaDAO.updateUserAccount(changeDoctor.getDocUsername(), changeDoctor.getEmail());
		return adminManageDoctor(model, req);
	}

	@RequestMapping(value = "/admin/adminLogout", method = RequestMethod.GET)
	public String adminLogout(Model model, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
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

		return "adminLogin";
	}
	
	@RequestMapping(value = "/adminLogout", method = RequestMethod.GET)
	public String Logout(Model model, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
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

		return "adminLogin";
	}
}
