package id.co.skyforce.finance.service;

import id.co.skyforce.finance.model.CIF;
import id.co.skyforce.finance.model.LoanAccount;
import id.co.skyforce.finance.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoanAccountService {
	Transaction transaction = null;
	Session session;

	public void addAcount(LoanAccount loanAccount) {
		session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(loanAccount);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}

	public void updateAccount(LoanAccount loanAccount) {
		session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(loanAccount);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}

	public void deleteAccount(String accountNumber) {
		session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			LoanAccount loanAccount = (LoanAccount) session.get(LoanAccount.class,
					accountNumber);
			session.delete(loanAccount);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}

	public List<LoanAccount> getAllLoanAccount() {
		List<LoanAccount> loanAccounts = new ArrayList<>();
		session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			loanAccounts = session.createQuery("FROM LoanAccount").list();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
		return loanAccounts;
	}

}
