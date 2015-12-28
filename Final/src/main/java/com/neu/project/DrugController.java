package com.neu.project;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.project.dao.DrugDAO;
import com.neu.project.model.Drug;

@Controller
public class DrugController {
	
	@Autowired
	private DrugDAO drugDAO;
	
	@RequestMapping(value = "/user/showDrugDetails", method = RequestMethod.GET)
	public String showDrugDetails(Model model,HttpServletRequest req){
		String drugID = req.getParameter("drugId");
		//System.out.println(">??<?>?? "+drugID);
		
		Drug drug = drugDAO.selectedDrug(Integer.parseInt(drugID));
		
		model.addAttribute("drug", drug);
		
		return "docViewDrugDetails";
	}
	
	@RequestMapping(value = "/user/patientViewDrugDetails", method = RequestMethod.GET)
	public String patientViewDrugDetails(Model model,HttpServletRequest req){
		String drugID = req.getParameter("drugId");
		//System.out.println(">??<?>?? "+drugID);
		
		Drug drug = drugDAO.selectedDrug(Integer.parseInt(drugID));
		
		model.addAttribute("drug", drug);
		
		return "patientViewDrugDetails";
	}

}
