

package id.co.skyforce.finance.service;

import id.co.skyforce.finance.model.User;
import id.co.skyforce.finance.util.HibernateUtil;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginService {
	
	public static boolean loginn(String username, String password, User inputUser){
		Session session = HibernateUtil.openSession();
		Transaction trx = session.beginTransaction();

		try{
			Query query = session.createQuery("from User m where m.username = :username and m.password = :password");
			query.setString("username", username);
			query.setString("password", password);
			User user = (User) query.uniqueResult();
			if (user != null){
				user.setLastLogin(inputUser.getLastLogin());
				session.saveOrUpdate(user);
				trx.commit();
				session.close();
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			System.out.println("Error Login!!!" + e.getMessage());
			return false;
		}
	}


//	public void loginDifferentUSer(User user){
//		Transaction transaction = null;
//		Session session = HibernateUtil.openSession();
//		try {
//			transaction = session.beginTransaction();
//			session.saveOrUpdate(user);
//			transaction.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			transaction.rollback();
//		}
//		session.close();
//	}


}

