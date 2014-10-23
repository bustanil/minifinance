package id.co.skyforce.finance.service;

import id.co.skyforce.finance.model.User;
import id.co.skyforce.finance.util.HibernateUtil;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginService {
	public User checkLogin(String username, String password) {
		Session session = HibernateUtil.openSession();
		Transaction trx = session.beginTransaction();

		
		Query query = session.createQuery("from User m where m.username = :username and m.password = :password");
		query.setString("username", "%" + username + "%");
		query.setString("password", "%" + password + "%");
		User user = (User) query.uniqueResult();
		
		user.setLastLogin(new Date());

		session.save(user);
		trx.commit();
		session.close();

		return user;
	}


}
