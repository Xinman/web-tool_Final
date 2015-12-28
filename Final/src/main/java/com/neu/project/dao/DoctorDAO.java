package com.neu.project.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.project.model.Appointment;
import com.neu.project.model.Doctor;

@Repository
public class DoctorDAO extends DAO {

	public Doctor findDoctorByNameAndPassword(String eu, String password)
			throws Exception {

		try {
			Criteria crit = getSession().createCriteria(Doctor.class);
			Criterion name = Restrictions.eq("docUsername", eu);
			Criterion email = Restrictions.eq("email", eu);
			Criterion pass = Restrictions.eq("docPassword", password);
			LogicalExpression orExp = Restrictions.or(email, name);
			crit.add(orExp);
			crit.setMaxResults(1);
			Doctor pa = (Doctor) crit.uniqueResult();

			return pa;
		} catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get user " + eu, e);
		}

	}

	public List ListDoctorByDegreeInDsc() throws Exception {
		try {
			Query q = getSession().createQuery(
					"from Doctor");
			q.setMaxResults(10);
			List rs = q.list();
			return rs;
		} catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get user ", e);
		}
	}

	public List ListAllDoctor() throws Exception {
		try {
			Query q = getSession().createQuery("from Doctor ");
			List rs = q.list();
			return rs;
		} catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get user ", e);
		}
	}
	
	public List adminViewAllDoctor() throws Exception {
		try {
			Query q = getSession().createQuery("from Doctor");
			List rs = q.list();
			return rs;
		} catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get user ", e);
		}
	}

	public void SaveDoctor(Doctor doc) {
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(doc);
		tx.commit();
		session.close();
		System.out.println("doctor success!");
	}
	
	
	
	public int deleteDoctor(int docID) throws Exception{
		try{
		
			SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createSQLQuery("delete from doctor where docId =:docID");
			q.setInteger("docID", docID);
			int rs =q.executeUpdate();
			if(rs>0){
				//System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}} success!");

			}
			tx.commit();
			session.close();
			return rs;
			
		} catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc "+ e.getMessage());
		}
	}

	public Doctor findDoctorByDocId(int docID) throws Exception{
		try{
			Doctor doctor = (Doctor) getSession().get(Doctor.class, docID);
			return doctor;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc ", e);
		}
		
	}
	
	public void updateDoctorInformation(Doctor doc){
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//Doctor doctor = (Doctor) session.get(Doctor.class, doc.getDocId());
		Query q = session.createQuery("from Doctor where docUsername =:username");
		q.setString("username", doc.getDocUsername());
		Doctor doctor = (Doctor) q.uniqueResult();
		//System.out.println(" doct  "+doc.getDocId());
		if(doctor!=null){
			doctor.setDescription(doc.getDescription());
			doctor.setDocCode(doc.getDocCode());
			//doctor.setDocPassword(doc.getDocPassword());
			doctor.setDocUsername(doc.getDocUsername());
			doctor.setEmail(doc.getEmail());
			doctor.setType(doc.getType());
			session.update(doctor);
			System.out.println("doc Update success!");
		}
		
		tx.commit();
		session.close();
		
	}
	
	public void updateDoctorPass(Doctor doc){
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//Doctor doctor = (Doctor) session.get(Doctor.class, doc.getDocId());
		//System.out.println(" doct  "+doc.getDocId());
		Query q = session.createQuery("from Doctor where docUsername =:username");
		q.setString("username", doc.getDocUsername());
		Doctor doctor = (Doctor) q.uniqueResult();
		if(doctor!=null){
			
			doctor.setDocPassword(doc.getDocPassword());
		
			session.update(doctor);
			System.out.println("doc Update success!");
		}
		
		tx.commit();
		session.close();
		
	}
	
	public Doctor findDoctorByDocName(String name) throws Exception{
		try{
			Query q = getSession().createQuery("from Doctor where docUsername =:name");
			q.setString("name", name);
			Doctor doc = (Doctor) q.uniqueResult();
			return doc;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc ", e);
		}
	}
	
	public Doctor findDoctorByDocEmail(String email) throws Exception{
		try{
			Query q = getSession().createQuery("from Doctor where email =:email");
			q.setString("email", email);
			Doctor doc = (Doctor) q.uniqueResult();
			
			return doc;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc ", e);
		}
	}
	
	public Doctor findDoctorByDocCode(String code) throws Exception{
		try{
			Query q = getSession().createQuery("from Doctor where docCode =:code");
			q.setString("code", code);
			Doctor doc = (Doctor) q.uniqueResult();
			
			return doc;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc ", e);
		}
	}
	
	public List findDoctorByNotDocName(String name) throws Exception{
		try{
			Query q = getSession().createQuery("from Doctor where docUsername <>:name");
			q.setString("name", name);
			List doc =  q.list();
			
			return doc;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc ", e);
		}
	}
	
	public List findDoctorByNotDocEmail(String email) throws Exception{
		try{
			Query q = getSession().createQuery("from Doctor where email <>:email");
			q.setString("email", email);
			List doc =  q.list();
			
			return doc;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc ", e);
		}
	}
	
	public List findDoctorByNotDocCode(String code) throws Exception{
		try{
			Query q = getSession().createQuery("from Doctor where docCode <>:code");
			q.setString("code", code);
			List doc =  q.list();
			
			return doc;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc ", e);
		}
	}
	
	public List findDoctorByLikeDocName(String name) throws Exception{
		try{
			Query q = getSession().createQuery("from Doctor where docUsername like '%"+name+"%'");
	
			List doc =  q.list();
			
			return doc;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc ", e);
		}
	}
	
	public List findDoctorByLikeDocEmail(String email) throws Exception{
		try{
			Query q = getSession().createQuery("from Doctor where email like '%"+email+"%'");

			List doc =  q.list();
			
			return doc;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc ", e);
		}
	}
	
	public List findDoctorByLikeDocCode(String code) throws Exception{
		try{
			Query q = getSession().createQuery("from Doctor where docCode like '%"+code+"%'");

			List doc =  q.list();
			
			return doc;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc ", e);
		}
	}
}
