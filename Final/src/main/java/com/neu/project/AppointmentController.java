package com.neu.project;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;













import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.neu.project.dao.AppointmentDAO;
import com.neu.project.dao.DoctorDAO;
import com.neu.project.model.Appointment;
import com.neu.project.model.Doctor;
import com.neu.project.model.Patient;

@Controller
public class AppointmentController {
	private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);
	@Autowired
	private AppointmentDAO appointDAO;
	
	@Autowired
	private DoctorDAO docDAO;
	
	@RequestMapping(value = "/user/makeAppointment", method = RequestMethod.GET)
	public String PatientMakeAppointment(Model model, HttpServletRequest req){
		String docName = req.getParameter("docName");
		String docCode = req.getParameter("docCode");
		Appointment appoint = new Appointment();
		model.addAttribute("appointment", appoint);
		model.addAttribute("docName",docName);
		model.addAttribute("docCode",docCode);
		
		return "patientMakeAppointment";
	}
	
	@RequestMapping(value = "/user/patientViewAppointmentList", method = RequestMethod.GET)
	public String patientViewOwnAppointment(Model model, HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		Patient p = (Patient) session.getAttribute("patient");
		List appoints = appointDAO.ListAppointmentByPatientName(p.getPaUsername());
		model.addAttribute("appoints", appoints);
		
		return "patientViewOwnAppointmentList";
	}
	
	public String patientHomePage(Model model,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		Patient p = (Patient) session.getAttribute("patient");
		List appoints = appointDAO.ListAppointmentByPatientNameMAX10(p.getPaUsername());
		List doctors = docDAO.ListDoctorByDegreeInDsc();
		model.addAttribute("appointmentList",appoints);
		model.addAttribute("patient",p);
		model.addAttribute("docList",doctors);
		
		return "userPatientHome";
	}
	
	@RequestMapping(value = "/user/patientAppointmentList", method = RequestMethod.GET)
	public String patientAppointmentList(Model model,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		Doctor d = (Doctor) session.getAttribute("doctor");
		List allAppoints = appointDAO.ListAllAppointmentByDoctorName(d.getDocUsername());
		PagedListHolder pagedList = new PagedListHolder(allAppoints);
		int page = ServletRequestUtils.getIntParameter(req, "p", 0);
		pagedList.setPage(page);
		pagedList.setPageSize(2);
		model.addAttribute("pagedListHolder", pagedList);
		model.addAttribute("allAppoints", allAppoints);
		
		return "docViewAllPatientAppointment";
		
	}
	
	@RequestMapping(value = "/user/appointment", method = RequestMethod.POST)
	public String MakeAnAppointment(Model model,@ModelAttribute Appointment appointment,HttpServletRequest req) throws Exception{
		System.out.println(">>>>>>>appoint "+appointment.getPaname());
		//String name=appointment.getPicName();
		//CommonsMultipartFile file = appointment.getFile();
		Date date = new Date();
		DateFormat dt1 = new SimpleDateFormat("MM/dd/yyyy");
		String d = dt1.format(date);
		appointment.setMakeTime(d);
		/*if (!file.isEmpty()) {
			try {
				 File destFile = new File("C:\\uploads\\", file.getOriginalFilename());
			        file.transferTo(destFile);
			        appointment.setPicName(file.getOriginalFilename());
				 /*byte[] bytes = file.getBytes();
				// Creating the directory to store file
	               // String rootPath = System.getProperty("catalina.home");
	               // File dir = new File("Users\M.smiley\Documents\workspace-sts-3.6.4.RELEASE\Final\src\main\webapp\tempImages");
	                String filename=name+Math.random()+file.getOriginalFilename();
				    File dir = new File("C:/fileuploads");
	                System.out.println("Dir>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+dir);
	                if (!dir.exists())
	                    dir.mkdirs();
	                // Create the file on server
	               // File serverFile = new File(dir.getAbsolutePath()
	                 //       + File.separator + file.getOriginalFilename());
	                File serverFile = new File(dir.getPath()
	   	                       + File.separator + filename);
	                String path = serverFile.getPath();
	                appointment.setPath(path);
	                System.out.println("Path>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+path);
	                //BufferedOutputStream stream = new BufferedOutputStream(
	                 //       new FileOutputStream(serverFile));
	                BufferedOutputStream stream = new BufferedOutputStream(
	                        new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();
	 
	               // logger.info("Server File Location="
	                //        + serverFile.getAbsolutePath());
	 
	                logger.info("Server File Location="
	                        + dir.getPath());*/
	                appointDAO.SendAppointmentToDoc(appointment);
	               // System.out.println( "You successfully uploaded file=" );
	                return patientHomePage(model,req);
	            //} catch (Exception e) {
	            //    return "You failed to upload "  + " => " + e.getMessage();
	          //  }
			//}else {
	      //      return "You failed to upload " 
	        //            + " because the file was empty.";
	      //  }
	
		
		

        
	}
	
	@RequestMapping(value = "/user/docViewDetailOfAppointment", method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
	public String docViewDetails(Model model,HttpServletRequest req){
		String appointID = req.getParameter("appointID");
		System.out.println("     .... "+appointID);
		int appointId = Integer.parseInt(appointID);
		Appointment appoint = appointDAO.findAppointmentByID(appointId);
		//String path = appoint.getPath();
		//InputStream in = servletContext.getResourceAsStream(path);
		//String name = appoint.getPicName();
		//System.out.println("???????????????????????????????????????"+name);
		model.addAttribute("appointment", appoint);

		
		
		return "detailsOfAppointment";
		
	}
	
	public String doctorHomePage(Model model,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		Doctor doc = (Doctor) session.getAttribute("doctor");
		List<Appointment> sentAppoints = new ArrayList<Appointment>();
		List appoints = appointDAO.findAppointmentByDoctorNameMAX10(doc.getDocUsername());
		if(appoints.isEmpty()){
			return "doctorHome";
		}
		for(int i=0;i<appoints.size();i++){
			Appointment app = (Appointment) appoints.get(i);
			if("Sent".equals(app.getStatus())){
				sentAppoints.add(app);
			}
		}
	
		model.addAttribute("appoints", sentAppoints);
		
		
		return "userDoctorHome";
	}
	
	@RequestMapping(value = "/user/doctorMakeDecision", method = RequestMethod.POST)
	public String theDescisionOfAppointment(Model model,HttpServletRequest req) throws Exception{
		String decision = req.getParameter("submit");
		String appId = req.getParameter("appointID");
		int appointId =Integer.parseInt(appId);
		Date date = new Date();
		DateFormat dt1 = new SimpleDateFormat("MM/dd/yyyy");
		String d = dt1.format(date);
		Appointment appoint = appointDAO.findAppointmentByID(appointId);
		if("Approve".equals(decision)){
			
			appoint.setChangeTime(d);
			appoint.setStatus("Approved");
		}
		if("Decline".equals(decision)){
			appoint.setChangeTime(d);
			appoint.setStatus("Declined");
		}
		appointDAO.updateAppointmentByDecision(appoint);
		//if(rs>0){
			return doctorHomePage(model,req);
		//}
		//return "detailsOfAppointment";
	}
}
