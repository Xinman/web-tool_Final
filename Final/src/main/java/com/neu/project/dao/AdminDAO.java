package com.neu.project.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.neu.project.model.Admin;

@Repository
public class AdminDAO extends DAO{

	public Admin findByUsernaemAndPassword(String username, String password) throws Exception {
		try{
			Query q = getSession().createQuery("from Admin where adminUsername =:username and adminPassword =:password");
			q.setString("username", username);
			q.setString("password", password);
			Admin ad = (Admin) q.uniqueResult();
			return ad;
		}catch (HibernateException e) {
		     //       rollback();
            throw new Exception("Could not get admin " + e.getMessage());
            }
	}
}
