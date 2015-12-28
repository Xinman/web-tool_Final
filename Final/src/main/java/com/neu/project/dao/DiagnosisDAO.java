package com.neu.project.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.neu.project.model.Diagnosis;

@Repository
public class DiagnosisDAO extends DAO{

	public void saveDiagnosis(Diagnosis diagnosis){
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(diagnosis);
		tx.commit();
		session.close();
		System.out.println("diagnose success!");
	}
	
	public List findDiagnosisByPatientName(String paname) throws Exception{
		try{
			Query q = getSession().createQuery("from Diagnosis where paname =:paname");
			q.setString("paname", paname);
			List diagnosisList = q.list();
			return diagnosisList;
		}catch (HibernateException e) {
		     //       rollback();
            throw new Exception("Could not get user " + paname, e);
            }
	}
	
	public Diagnosis findDiagnosisByDiagID(int diagID) throws Exception{
		
		try{
			Diagnosis diag = (Diagnosis) getSession().get(Diagnosis.class, diagID);
			return diag;
		}catch (HibernateException e) {
		     //       rollback();
            throw new Exception("Could not get diagnosis " + diagID, e);
            }
	}
}
