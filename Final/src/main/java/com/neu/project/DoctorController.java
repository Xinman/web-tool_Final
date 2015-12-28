package com.neu.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.neu.project.dao.DiagnosisDAO;
import com.neu.project.dao.DoctorDAO;
import com.neu.project.dao.DrugDAO;
import com.neu.project.dao.MedicationDAO;
import com.neu.project.dao.UserAccountDAO;
import com.neu.project.model.Appointment;
import com.neu.project.model.Diagnosis;
import com.neu.project.model.Doctor;
import com.neu.project.model.Drug;
import com.neu.project.model.Medication;
import com.neu.project.model.Patient;
import com.neu.project.model.SelectedDrug;


@Controller
public class DoctorController {

	@Autowired
	private DoctorDAO docDAO;
	
	@Autowired
	private DrugDAO drugDAO;
	
	@Autowired
	private DiagnosisDAO diagnosisDAO;
	
	@Autowired
	private AppointmentDAO appointDAO;
	
	@Autowired
	private MedicationDAO medDAO;
	
	@Autowired
	private UserAccountDAO uaDAO;
	
	@RequestMapping(value = "/user/showDoctors", method = RequestMethod.GET)
	public String ShowDoctorList(Model model,HttpServletRequest req) throws Exception{
		
			List doctorList = docDAO.ListAllDoctor();
			model.addAttribute("doctors", doctorList);
			
		return "doctorList";
	}
	
	@RequestMapping(value = "/user/docDiagnosis", method = RequestMethod.GET)
	public String doctorDiagnosis(Model model,HttpServletRequest req){
		String paname = req.getParameter("paname");
		String appointID = req.getParameter("appId");
		Diagnosis diagnosis = new Diagnosis();
		model.addAttribute("paname", paname);
		model.addAttribute("appointID", appointID);
		model.addAttribute("diagnosis", diagnosis);
		
		return "makeDiagnosis";
	}
	
	public String doctorViewPatientPage(Model model,HttpServletRequest req) throws Exception{
		
		HttpSession session = req.getSession();
		Doctor d = (Doctor) session.getAttribute("doctor");
		List aprovedAppoints = appointDAO.findApprovedAppointmentByDocName(d.getDocUsername());
	    model.addAttribute("approvedAppoints", aprovedAppoints);
	    
	    return "docViewPatient";
		
	}
	
	@RequestMapping(value = "/user/makeDiagnosis", method = RequestMethod.POST)
	public String doctorMakeDiagnosis(Model model, @ModelAttribute Diagnosis diagnosis,HttpServletRequest req) throws Exception{
		
		Date date = new Date();
		DateFormat dt1 = new SimpleDateFormat("MM/dd/yyyy");
		String d = dt1.format(date);
		diagnosis.setDiagdate(d);
		diagnosisDAO.saveDiagnosis(diagnosis);
		Appointment appoint = appointDAO.findAppointmentByID(diagnosis.getAppointID());
		appoint.setStatus("Diagnosed");
		appoint.setChangeTime(d);
		//System.out.println("appoint???????????????????? "+appoint.getDocname());
		appointDAO.updateAppointmentByDecision(appoint);
		
		return doctorViewPatientPage(model,req);
	}
	
	@RequestMapping(value = "/user/docMakeMedication", method = RequestMethod.GET)
	public String docMakeMedication(Model model,HttpServletRequest req) throws Exception{

		HttpSession session = req.getSession();
		String appID = req.getParameter("appId");
		String paname = req.getParameter("paname");
		
		session.setAttribute("appointID", Integer.parseInt(appID));
		session.setAttribute("paname", paname);
		List drugs = drugDAO.ListAllDrugs();
		List amount = drugDAO.chooseAmount();
		
		
		model.addAttribute("drugs", drugs);
		model.addAttribute("amount", amount);
		return "makeMedication";
	}
	
	@RequestMapping(value = "/user/docMakeMedication", method = RequestMethod.POST)
	public  String doctorMakeMedication(Model model, HttpServletRequest req) throws Exception{
		
		//
	    String[] drugID = req.getParameterValues("drugID");
	    if(drugID.length==0){
	    	return doctorMakeMedication(model,req);
	    }

	    List<SelectedDrug> selectedDrugs = new ArrayList<SelectedDrug>();
	
	   // List<Float> totals = new ArrayList<Float>();
	    for(int i = 0; i<drugID.length;i++){
	    	//System.out.println("<<<<<<<<<<<<<<<<<<amount  "+drugAmount[i]);
	        SelectedDrug sd = new SelectedDrug();
	    	Drug drug = drugDAO.selectedDrug(Integer.parseInt(drugID[i]));
	    	//total = drug.getPrice()*Integer.parseInt(drugAmount[i]);
	    	//sd.setQuantity(Integer.parseInt(drugAmount[i]));
	    	sd.setDrug(drug);
	    	//sd.setTotal(total);
	    	selectedDrugs.add(sd);
	    	
	    	//totals.add(total);
	}
	    List drugs = drugDAO.ListAllDrugs();
	    List amount = drugDAO.chooseAmount();
	    model.addAttribute("amount", amount);
	    model.addAttribute("drugs", drugs);
	    model.addAttribute("selectedDrug", selectedDrugs);
	    //Medication med = new Medication();
	   // model.addAttribute("medication", med);
	    return "docAddMedication";
	}
	
	@RequestMapping(value = "/user/addToMedication", method = RequestMethod.POST)
	public String docAddDrugToMedication(Model model,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		int appID = (Integer) session.getAttribute("appointID");
		String paname =  (String) session.getAttribute("paname");
		Date date = new Date();
		DateFormat dt1 = new SimpleDateFormat("MM/dd/yyyy");
		String d = dt1.format(date);
		String[] drugAmount = req.getParameterValues("amount");
		String[] drugID = req.getParameterValues("drugID");
		if(drugAmount.length==0){
			return doctorMakeMedication(model,req);
		}
		ArrayList<Medication> medList = new ArrayList<Medication>();
		float total =0;
		for(int i=0;i<drugAmount.length;i++){
			
			Medication med = new Medication();
			med.setAppointID(appID);
			med.setPaname(paname);
			med.setMedDate(d);
			//med.setDrugID(Integer.parseInt(drugID[i]));
			
			Drug drug = drugDAO.selectedDrug(Integer.parseInt(drugID[i]));
			total = drug.getPrice()*Integer.parseInt(drugAmount[i]);
			med.setQuantity(Integer.parseInt(drugAmount[i]));
			med.setDrugID(Integer.parseInt(drugID[i]));
			med.setDrugname(drug.getDrugname());
			med.setTotal(total);
			med.setPrice(drug.getPrice());
			//med.setQuantity(Integer.parseInt(drugAmount[i]));
			medDAO.saveMedication(med);
			medList.add(med);
		}
	
		//medDAO.saveMedication(med);
		//model.addAttribute("drugList", drugList);
		model.addAttribute("medication", medList);
		Appointment app = appointDAO.findAppointmentByID(appID);
		app.setStatus("Completed");
		appointDAO.updateAppointmentByDecision(app);
		
		return "makeMedicationSuccess";
	}
	
	
	@RequestMapping(value = "/user/showPatient", method = RequestMethod.GET)
	public String docViewPatient(Model model, HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		Doctor d = (Doctor) session.getAttribute("doctor");
		List aprovedAppoints = appointDAO.findApprovedAppointmentByDocName(d.getDocUsername());
	    model.addAttribute("approvedAppoints", aprovedAppoints);
	    
	    return "docViewPatient";
	}
	

	@RequestMapping(value = "/user/showDoctorInfor", method = RequestMethod.GET)
	public String docViewPersonalInfor(Model model, HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		Doctor d = (Doctor) session.getAttribute("doctor");
		Doctor doc = new Doctor();
		model.addAttribute("doctor", d);
		model.addAttribute("newDoc", doc);
	    
	    
	    return "doctorViewPersonalInfor";
	}
	
	@RequestMapping(value = "/user/docChangeInfor", method = RequestMethod.POST)
	public String docChangeInfor(Model model,HttpServletRequest req, @Valid @ModelAttribute("newDoc")Doctor doc, BindingResult result ) throws Exception{
		
		if(result.hasErrors()){
			//System.out.println("..........................");
			return "doctorPatientInfor";
		}
		//String gender = req.getParameter("gender");
		//pa.setGender(gender);
		//System.out.println("    ... "+pa.getPaUsername());
		docDAO.updateDoctorInformation(doc);
		uaDAO.updateUserAccount(doc.getDocUsername(), doc.getEmail());
			return doctorViewPatientPage(model,req);	
	}
	
	@RequestMapping(value = "/user/doctorChangePass", method = RequestMethod.GET)
	public String doctorChangePassword(Model model){
		
		return "doctorChangePassword";
	}
	
	@RequestMapping(value = "/user/changeDoctorPass", method = RequestMethod.POST)
	public String doctorChangePass(Model model,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		Doctor d = (Doctor) session.getAttribute("doctor");
		String oldPass = req.getParameter("oldPass");
		String newPass1 = req.getParameter("newPass1");
		String newPass2 = req.getParameter("newPass2");
		if(oldPass.equals(d.getDocPassword())){
			d.setDocPassword(newPass1);
			docDAO.updateDoctorPass(d);
			uaDAO.updateUserAccountDocPass(d);
			return doctorViewPatientPage(model,req);
		}
		return "patientChangePassword";
	}
	
}
