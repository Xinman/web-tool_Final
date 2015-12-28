package com.neu.project.dao;

import javax.transaction.Transactional;

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

import com.neu.project.model.Doctor;
import com.neu.project.model.Patient;
import com.neu.project.model.UserAccount;

@Repository
public class UserAccountDAO extends DAO {

	public void saveUserAccount(String username, String password, String email) {

		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UserAccount ua = new UserAccount();
		ua.setRole_id(1);
		ua.setPassword(password);
		ua.setUsername(username);
		ua.setEmail(email);
		session.save(ua);
		tx.commit();
		session.close();
		System.out.println(" userAccount success!");
	}
	
	public void updateUserAccount(String username,String email){
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//UserAccount ua = (UserAccount) session.get(UserAccount.class, doc.getDocId());
	
		Query q = getSession().createQuery("from UserAccount where username =:username");
		q.setString("username", username);
		UserAccount ua = (UserAccount) q.uniqueResult();
		if(ua!=null){
			
			ua.setUsername(username);
			ua.setEmail(email);
			
			session.update(ua);
			System.out.println("ua Update success!");
		}
		tx.commit();
		session.close();
	}
	
	public void updateUserAccountPass(Patient p){
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//UserAccount ua = (UserAccount) session.get(UserAccount.class, doc.getDocId());
	
		Query q = getSession().createQuery("from UserAccount where username =:username");
		q.setString("username", p.getPaUsername());
		UserAccount ua = (UserAccount) q.uniqueResult();
		if(ua!=null){
			
			ua.setPassword(p.getPapassword());
			session.update(ua);
			System.out.println("ua Update success!");
		}
		tx.commit();
		session.close();
	}
	
	public void updateUserAccountDocPass(Doctor d){
		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//UserAccount ua = (UserAccount) session.get(UserAccount.class, doc.getDocId());
	
		Query q = getSession().createQuery("from UserAccount where username =:username");
		q.setString("username", d.getDocUsername());
		UserAccount ua = (UserAccount) q.uniqueResult();
		if(ua!=null){
			
			ua.setPassword(d.getDocPassword());
			session.update(ua);
			System.out.println("ua Update success!");
		}
		tx.commit();
		session.close();
	}

	public void saveDoctorUserAccount(UserAccount doc) {

		SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(doc);
		tx.commit();
		session.close();
		System.out.println(" doctoruseraccount success!");
	}

	public UserAccount findByUsernameAndPassword(String eu, String password)
			throws Exception {

		try {
			Criteria crit = getSession().createCriteria(UserAccount.class);
			Criterion name = Restrictions.eq("username", eu);
			Criterion email = Restrictions.eq("email", eu);
			Criterion pass = Restrictions.eq("password", password);
			LogicalExpression orExp = Restrictions.or(email, name);
			crit.add(orExp);
			UserAccount ua = (UserAccount) crit.uniqueResult();
			return ua;
		} catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get user " + eu, e);
		}
	}


	@Transactional
	public int deleteUserAccountByDoctorname(String docname) throws Exception{
		try{
			SessionFactory sessionFactory = HibernateUtil.getSessionfactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createSQLQuery("delete from useraccount where username =:username");
			q.setString("username", docname);
			//UserAccount ua = (UserAccount) q.uniqueResult();
			//System.out.println(" ////////////////////////////////// "+ua.getUaID());
			int rs = q.executeUpdate();
			if(rs>0){
				System.out.println("delete success!");
			}
			tx.commit();
			session.close();
			return rs;
		}catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get doc " + docname+"" +e.getMessage());
		}
	}
	
	public boolean isExistUserName(String eu){
		
		Criteria crit = getSession().createCriteria(UserAccount.class);
		Criterion name = Restrictions.eq("username", eu);
		Criterion email = Restrictions.eq("email", eu);
		LogicalExpression orExp = Restrictions.or(email, name);
		crit.add(orExp);
		UserAccount ua = (UserAccount) crit.uniqueResult();
		if(ua==null){
			return false;
		}
		//System.out.println(" hahahhh  "+ua.getUsername());
		return true;
	}

}
