package com.neu.project.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.neu.project.model.Drug;

@Repository
public class DrugDAO extends DAO{
	
	
	public List ListAllDrugs() throws Exception{
		try{
			Query q = getSession().createQuery("from Drug");
			List drugList = q.list();
			return drugList;
		}catch (HibernateException e) {
		     //       rollback();
            throw new Exception("Could not get drugs "+e.getMessage());
            }
	}
	
	public List chooseAmount() throws Exception{
		try{
			Query q = getSession().createQuery("from Amount");
			List amount = q.list();
			return amount;
		}catch (HibernateException e) {
		     //       rollback();
            throw new Exception("Could not get amount " , e);
            }
	}

	public Drug selectedDrug(int drugID){
		Drug drug = (Drug) getSession().get(Drug.class, drugID);
		return drug;
	}
}
