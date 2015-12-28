package com.neu.project;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.project.dao.AppointmentDAO;
import com.neu.project.dao.DiagnosisDAO;
import com.neu.project.dao.DoctorDAO;
import com.neu.project.dao.MedicationDAO;
import com.neu.project.dao.PatientDAO;
import com.neu.project.dao.UserAccountDAO;
import com.neu.project.model.Appointment;
import com.neu.project.model.Diagnosis;
import com.neu.project.model.Doctor;
import com.neu.project.model.Patient;

@Controller
public class PatientController {

	@Autowired
	private PatientDAO paDAO;
	
	@Autowired
	private DiagnosisDAO diagDAO;
	
	@Autowired
	private AppointmentDAO appDAO;
	
	@Autowired
	private DoctorDAO docDAO;
	
	@Autowired
	private MedicationDAO medDAO;
	
	@Autowired
	private UserAccountDAO uaDAO;
	
	@RequestMapping(value = "/user/patientViewVisitHistory", method = RequestMethod.GET)
	public String patientViewVisitHistory(Model model,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		Patient p = (Patient) session.getAttribute("patient");
		List diagnosisList = diagDAO.findDiagnosisByPatientName(p.getPaUsername());
		System.out.println("????????????????????????????"+diagnosisList.size());
		//ArrayList<Appointment> diagDoctors = new ArrayList<Appointment>();
		/*for(int i=0;i<diagnosisList.size();i++){
			Diagnosis dia = (Diagnosis) diagnosisList.get(i);
			System.out.println("??????????????????????"+dia.getDiseasename());
			Appointment appoint = appDAO.findAppointmentByID(dia.getAppointID());
			System.out.println("??????????????????????"+appoint.getDocname());
			diagDoctors.add(appoint);
		}*/
		 model.addAttribute("diagList", diagnosisList);
		 //model.addAttribute("diagDoctors", diagDoctors);
		 
		 return "patientViewVisitHistory";
		
	}
	
	
	@RequestMapping(value = "/user/patientViewDetailsOfAppointment", method = RequestMethod.GET)
	public String docViewDetails(Model model,HttpServletRequest req){
		String appointID = req.getParameter("appointID");
		System.out.println("     .... "+appointID);
		int appointId = Integer.parseInt(appointID);
		Appointment appoint = appDAO.findAppointmentByID(appointId);
		//String path = appoint.getPath();
		//InputStream in = servletContext.getResourceAsStream(path);
		//String name = appoint.getPicName();
		//System.out.println("???????????????????????????????????????"+name);
		model.addAttribute("appointment", appoint);

		
		
		return "patientViewDetailsOfAppointment";
		
	}
	
	@RequestMapping(value = "/user/patientViewDiagnosisDetails", method = RequestMethod.GET)
	public String patientViewDiagnosisDetails(Model model,HttpServletRequest req) throws Exception{
		
		String diagnosisID = req.getParameter("diagID");
		int diagID = Integer.parseInt(diagnosisID);
		;
		Diagnosis diag = diagDAO.findDiagnosisByDiagID(diagID);
		
		model.addAttribute("diag", diag);
		
		return "patientViewDiagnosisDetails";
	}
	
	@RequestMapping(value = "/user/patientViewMedicationRecords", method = RequestMethod.GET)
	public String patientViewMedicationRecords(Model model,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		Patient p = (Patient) session.getAttribute("patient");
		List medList = medDAO.listMedicationByPatientName(p.getPaUsername());
		model.addAttribute("medList", medList);
		return "patientViewMedication";
	}
	
	@RequestMapping(value = "/user/showPatientInfor", method = RequestMethod.GET)
	public String showPatientInfor(Model model,HttpServletRequest req){
		HttpSession session = req.getSession();
		Patient p = (Patient) session.getAttribute("patient");
		Patient newPa = new Patient();
		model.addAttribute("patient", p);
		model.addAttribute("newPa", newPa);
		return "patientPersonalInfor";
	}
	
	public String patientHomePage(Model model,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		Patient p = (Patient) session.getAttribute("patient");
		List appoints = appDAO.ListAppointmentByPatientNameMAX10(p.getPaUsername());
		List doctors = docDAO.ListDoctorByDegreeInDsc();
		model.addAttribute("appointmentList",appoints);
		model.addAttribute("patient",p);
		model.addAttribute("docList",doctors);
		
		return "userPatientHome";
	}
	
	
	@RequestMapping(value = "/user/patientChangeInfor", method = RequestMethod.POST)
	public String patientChangeInfor(Model model,HttpServletRequest req, @Valid @ModelAttribute("newPa")Patient pa, BindingResult result ) throws Exception{
		
		if(result.hasErrors()){
			System.out.println("..........................");
			return "patientPersonalInfor";
		}
		
		String gender = req.getParameter("gender");
		pa.setGender(gender);
		System.out.println("    ... "+pa.getPaId());
		paDAO.updatePatientInfor(pa);
	    uaDAO.updateUserAccount(pa.getPaUsername(), pa.getContactemail());
			return patientHomePage(model,req);	
	}
	
	@RequestMapping(value = "/user/patientChange", method = RequestMethod.GET)
	public String patientChangePassword(Model model){
		
		return "patientChangePassword";
	}
	
	@RequestMapping(value = "/user/patientChange", method = RequestMethod.POST)
	public String patientChangePass(Model model,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		Patient p = (Patient) session.getAttribute("patient");
		String oldPass = req.getParameter("oldPass");
		String newPass1 = req.getParameter("newPass1");
		String newPass2 = req.getParameter("newPass2");
		if(oldPass.equals(p.getPapassword())){
			p.setPapassword(newPass1);
			paDAO.updatePatientPass(p);
			uaDAO.updateUserAccountPass(p);
			return patientHomePage(model,req);
		}
		return "patientChangePassword";
	}
	
	@RequestMapping(value = "/user/searchDoctor", method = RequestMethod.POST)
	public String searchDoctorByFilter(Model model,HttpServletRequest req) throws Exception{
		String condition = req.getParameter("condition");
		String way = req.getParameter("way");
		String value = req.getParameter("value");
		
		//System.out.println("////////////////////////////////"+way);
		//System.out.println("///////////////////////////////////////////////"+condition);
		//System.out.println("///////////////////////////////////////////////"+value);
		if("isEqualTo".equals(way)){
			//System.out.println("......................................");
			if("name".equals(condition)){
				Doctor doc = docDAO.findDoctorByDocName(value);
				if(doc!=null){
					model.addAttribute("doctor", doc);
					return "patientSearchDoctor";
				}
				return "doctorList";
			}
			else if("email".equals(condition)){
				Doctor doc = docDAO.findDoctorByDocEmail(value);
				if(doc!=null){
					model.addAttribute("doctor", doc);
					return "patientSearchDoctor";
				}
				return "doctorList";
			}
			else if("code".equals(condition)){
				Doctor doc = docDAO.findDoctorByDocCode(value);
				if(doc!=null){
					model.addAttribute("doctor", doc);
					return "patientSearchDoctor";
				}
				return "doctorList";
			}
		}
		
		if("isNotEqualTo".equals(way)){
			if("name".equals(condition)){
				//System.out.println("///////////////////////////////////////////////");
				List doc = docDAO.findDoctorByNotDocName(value);
				if(doc!=null){
					model.addAttribute("doctor", doc);
					return "patientSearchDoctorList";
				}
				return "doctorList";
			}
			else if("email".equals(condition)){
				List doc = docDAO.findDoctorByNotDocEmail(value);
				if(doc!=null){
					model.addAttribute("doctor", doc);
					return "patientSearchDoctorList";
				}
				return "doctorList";
			}
			else if("code".equals(condition)){
				List doc = docDAO.findDoctorByNotDocCode(value);		
				if(doc!=null){
					model.addAttribute("doctor", doc);
					return "patientSearchDoctorList";
				}
				return "doctorList";
			}
		}
		
		if("contain".equals(way)){
			if("name".equals(condition)){
				List doc = docDAO.findDoctorByLikeDocName(value);
				if(doc!=null){
					model.addAttribute("doctor", doc);
					return "patientSearchDoctorList";
				}
				return "doctorList";
			}
			else if("email".equals(condition)){
				List doc = docDAO.findDoctorByLikeDocEmail(value);
				if(doc!=null){
					model.addAttribute("doctor", doc);
					return "patientSearchDoctorList";
				}
				return "doctorList";
			}
			else if("code".equals(condition)){
				List doc = docDAO.findDoctorByLikeDocCode(value);		
				if(doc!=null){
					model.addAttribute("doctor", doc);
					return "patientSearchDoctorList";
				}
				return "doctorList";
			}
		}
		return "doctorList";
		
	}
	
	@RequestMapping(value = "/user/patientViewDoctorDetails", method = RequestMethod.GET)
	public String patientViewDoctorDetails(Model model,HttpServletRequest req) throws Exception{
		
		String docName = req.getParameter("docname");
		System.out.println("doc    "+docName);
		Doctor doc = docDAO.findDoctorByDocName(docName);
		
		model.addAttribute("doctor",doc);
		
		return "patientViewDoctorDetails";
		
	}
	
}
