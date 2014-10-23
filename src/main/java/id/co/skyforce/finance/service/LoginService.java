package id.co.skyforce.finance.service;

import id.co.skyforce.finance.model.User;
import id.co.skyforce.finance.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginService {
	private Integer id;
	private String username;
	private String password;
	
	public void login (User user) {
		Session session = HibernateUtil.openSession();
		Transaction trx = session.beginTransaction();
		
		
		Query query = session.createQuery("from User m where m.id =:id and m.username = :username and m.password = :password");
		query.setString("id", "%" + id + "%");
		query.setString("username", "%" + username + "%");
		query.setString("password", "%" + password + "%");
		user = (User) query.uniqueResult();

		session.saveOrUpdate(user);
		trx.commit();
		session.close();
	}


}
