package com.neu.project.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.neu.project.model.Medication;

@Repository
public class MedicationDAO extends DAO{
	
	public void saveMedication(Medication med){
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(med);
		tx.commit();
		session.close();
		System.out.println("medication success!");
	}
	
	public List listMedicationByPatientName(String paname) throws Exception{
		
		try{
			Query q = getSession().createQuery("from Medication where paname =:paname");
			q.setString("paname", paname);
			List medList = q.list();
			return medList;
		}catch (HibernateException e) {
		     //       rollback();
            throw new Exception("Could not get paname "+e.getMessage());
            }
	}

}
