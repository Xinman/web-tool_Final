package com.neu.project.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.neu.project.model.Patient;

@Repository
public class PatientDAO extends DAO {

	public void addUserAccount(Patient pa) {
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		pa.setRole_id(1);
		session.save(pa);
		tx.commit();
		session.close();
		System.out.println("success!");
	}

	public Patient findUserByNameAndPassword(String eu, String password)
			throws Exception {

		try {
			Criteria crit = getSession().createCriteria(Patient.class);
			Criterion name = Restrictions.eq("paUsername", eu);
			Criterion email = Restrictions.eq("contactemail", eu);
			Criterion pass = Restrictions.eq("papassword", password);
			LogicalExpression orExp = Restrictions.or(email, name);
			crit.add(orExp);

			Patient pa = (Patient) crit.uniqueResult();

			return pa;
		} catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get user " + eu, e);
		}

	}

	public void updatePatientInfor(Patient pa) {
		//System.out.println("  pa   "+pa.getPaId());
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Patient where paUsername =:paname");
		q.setString("paname", pa.getPaUsername());
		//Patient p = (Patient) session.get(Patient.class, pa.getPaId());
		Patient p = (Patient) q.uniqueResult();
		//System.out.println("pa id   "+pa.getPaId());
		if(p!=null){
			//System.out.println("..............................................");
			p.setAge(pa.getAge());
			p.setContactemail(pa.getContactemail());
			p.setGender(pa.getGender());
			p.setPaUsername(pa.getPaUsername());
			p.setPhone(pa.getPhone());
			p.setSsn(pa.getSsn());
			session.update(p);
			System.out.println("patient update success!");
		}
		
		tx.commit();
		session.close();
		
	}

	public void updatePatientPass(Patient pa) {
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//Patient p = (Patient) session.get(Patient.class, pa.getPaId());
		Query q = session.createQuery("from Patient where paUsername =:paname");
		q.setString("paname", pa.getPaUsername());
		//Patient p = (Patient) session.get(Patient.class, pa.getPaId());
		Patient p = (Patient) q.uniqueResult();
		if(p!=null){
			p.setPapassword(pa.getPapassword());
			session.update(p);
			System.out.println("patient update success!");
		}
		
		tx.commit();
		session.close();
		
	}
	
	
	public boolean isExistSSN(String ssn) throws Exception {
		
		try{
			Query q = getSession().createQuery("from Patient where ssn =:ssn");
			q.setString("ssn", ssn);
			Patient p = (Patient) q.uniqueResult();
			if(p==null){
				return false;
			}
			return true;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get user " + ssn, e);
		}

	}
}
