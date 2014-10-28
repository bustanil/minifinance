package id.co.skyforce.finance.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import id.co.skyforce.finance.model.LoanAccount;
import id.co.skyforce.finance.model.LoanAccountSchedule;
import id.co.skyforce.finance.model.Payment;
import id.co.skyforce.finance.util.HibernateUtil;

import org.hibernate.Hibernate;
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

	// TODO Test
	public void payInstallment(Payment payment) throws Exception {
		if (payment == null) {
			throw new Exception("Payment tidak boleh null.");
		}

		if (payment.getPaymentStatus() == null) {
			throw new Exception("Payment status tidak boleh null.");
		}

		if (!payment.getPaymentStatus().equals('N')) {
			throw new Exception(
					"Payment tidak dapat dibayarkan karena status payment bukan 'N' (New).");
		}

		if (payment.getAmount().compareTo(BigDecimal.ZERO) != 1) {
			throw new Exception("Amount harus lebih besar dari nol.");
		}

		Transaction transaction = null;
		Session session = HibernateUtil.openSession();

		transaction = session.beginTransaction();

		LoanAccountService loanAccountService = new LoanAccountService();
		LoanAccount loanAccount = loanAccountService.getLoanAccount(payment
				.getAcountNo());

		if (loanAccount == null) {
			transaction.rollback();
			session.close();
			throw new Exception("Loan account " + payment.getAcountNo()
					+ "tidak ditemukan.");
		}

		session.refresh(loanAccount);
		Hibernate.initialize(loanAccount.getLoanAccountSchedules());
		List<LoanAccountSchedule> loanAccountSchedules = loanAccount
				.getLoanAccountSchedules();

		LoanAccountSchedule loanAccountSchedule = null;

		for (LoanAccountSchedule schedule : loanAccountSchedules) {
			if (schedule.getPaidStatus().equals('U')) {
				loanAccountSchedule = schedule;
				break;
			}
		}

		if (loanAccountSchedule == null) {
			transaction.rollback();
			session.close();
			throw new Exception("Tidak ada angsuran yang belum dibayar.");
		}

		if (loanAccountSchedule.getOutstanding().compareTo(payment.getAmount()) != 0) {
			transaction.rollback();
			session.close();
			throw new Exception(
					"Cicilan tidak sesuai dengan jumlah pembayaran.");
		}

		loanAccountSchedule.setPaidStatus('P');
		payment.setPaymentStatus('P');

		session.update(loanAccountSchedule);
		session.update(payment);

		transaction.commit();
		session.close();
	}
}
