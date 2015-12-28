package com.neu.project.dao;

import java.util.List;

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







import com.neu.project.model.Appointment;
import com.neu.project.model.Doctor;


@Repository
public class AppointmentDAO extends DAO{
	
	public List ListAppointmentByPatientNameMAX10(String paName) throws Exception{
		
		try{
			Query q = getSession().createQuery("from Appointment where paname =:paName order by makeTime desc");
			q.setString("paName", paName);
			q.setMaxResults(10); 
			List appointmentList = q.list();
			return appointmentList;
		}catch (HibernateException e) {
		     //       rollback();
            throw new Exception("Could not get user " + paName, e);
            }
	}
	
	
	public List ListAppointmentByPatientName(String paName) throws Exception{
		try{
			Query q = getSession().createQuery("from Appointment where paname =:paName order by makeTime desc");
			q.setString("paName", paName);
			List appointmentList = q.list();
			
			return appointmentList;
		}catch (HibernateException e) {
		     //       rollback();
            throw new Exception("Could not get user " + paName, e);
            }
	}
	
	
	public void SendAppointmentToDoc(Appointment appoint){
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		appoint.setStatus("Sent");
		session.save(appoint);
		tx.commit();
		session.close();
		System.out.println("success!");
	}
	
	public List findAppointmentByDoctorNameMAX10(String docName) throws Exception{
		
		try{
			Query q = getSession().createQuery("from Appointment where docname =:docname order by makeTime desc ");
			q.setString("docname", docName);
			q.setMaxResults(10); 
			List appoints = q.list();
			return appoints;
		}catch (HibernateException e) {
		     //       rollback();
            throw new Exception("Could not get user " + docName, e);
            }
	}
	
public List ListAllAppointmentByDoctorName(String docName) throws Exception{
		
		try{
			//Query q = getSession().createQuery("from Appointment where docname =:docname order by makeTime desc ");
			//q.setString("docname", docName);
			//List appoints = q.list();
			System.out.println("///////////// "+docName);
			Criteria crit = getSession().createCriteria(Appointment.class);
			Criterion name = Restrictions.eq("docname",docName);
			Criterion status1 = Restrictions.eq("status","Sent");
			LogicalExpression andExp = Restrictions.and(name, status1);
			crit.add(andExp);
			List appoints = crit.list();
			return appoints;
		}catch (HibernateException e) {
		     //       rollback();
            throw new Exception("Could not get user " +  e.getMessage());
            }
	}
	
	public Appointment findAppointmentByID(int appointID){
		
		Appointment appoint = (Appointment) getSession().get(Appointment.class, appointID);
		
		return appoint;
	}
	
	
	public void updateAppointmentByDecision(Appointment appoint){
		
		//Query q = getSession().createQuery("update Appointment as a set a.changeTime =:changeTime where a.appId =:appID");
		//q.setString("changeTime", appoint.getChangeTime());
		//q.setString("status", appoint.getStatus());
		//System.out.println("status     "+appoint.getStatus());
		
		//q.setInteger("appID", appoint.getAppId());
		//System.out.println("appID     "+appoint.getAppId());
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Appointment app = (Appointment) session.get(Appointment.class, appoint.getAppId());
		app.setChangeTime(appoint.getChangeTime());
		app.setStatus(appoint.getStatus());
		session.update(app);
		//int rs =q.executeUpdate();
		//System.out.println("RS      "+rs);
		//if(rs>0){
		//}
		tx.commit();
		session.close();
		System.out.println("Update success!");
		//return rs;
	}
	
	public List findApprovedAppointmentByDocName(String docName) throws Exception{
		try{
			
			Criteria crit = getSession().createCriteria(Appointment.class);
			Criterion name = Restrictions.eq("docName",docName);
			Criterion status1 = Restrictions.eq("status","Approved");
			Criterion status2 = Restrictions.eq("status","Diagnosed");
			LogicalExpression orExp = Restrictions.or(status1,status2); 
			crit.add(orExp);
			List approvedAppoints = crit.list();
			return approvedAppoints;
		}catch (HibernateException e) {
		     //       rollback();
            throw new Exception("Could not get user " + docName, e);
            }
		
	}

	
}
