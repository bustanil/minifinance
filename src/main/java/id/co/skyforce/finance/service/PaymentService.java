package id.co.skyforce.finance.service;

import java.util.ArrayList;
import java.util.List;

import id.co.skyforce.finance.model.Payment;
import id.co.skyforce.finance.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PaymentService {
	
	public List<Payment> getAllPayment() {
		List<Payment> payments = new ArrayList<>();
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			payments = session.createQuery("FROM Payment").list();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
		return payments;
	}

	public void updatePayment(Payment payment) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(payment);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}
	
	public void deletePayment(int idPayment) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			Payment payment = (Payment) session.get(Payment.class, idPayment);
			session.delete(payment);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.commit();
		}
		session.close();
	}
	
	public void addPayment(Payment payment) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(payment);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}
}
