package id.co.skyforce.finance.service;

import java.util.List;

import javax.faces.context.FacesContext;

import id.co.skyforce.finance.model.User;
import id.co.skyforce.finance.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginService {

	public void login(User inputUser) {
		Session session = HibernateUtil.openSession();
		Transaction trx = session.beginTransaction();

		Query query = session
				.createQuery("from User m where m.username = :username and m.password = :password");
		query.setString("username", inputUser.getUsername());
		query.setString("password", inputUser.getPassword());
		User user = (User) query.uniqueResult();

		if (user != null) {
			user.setLastLogin(inputUser.getLastLogin());
			session.update(user);
		}

		trx.commit();
		session.close();
	}
	
	public void loginDifferentUser(User user){
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}

}
