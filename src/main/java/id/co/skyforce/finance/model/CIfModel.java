package id.co.skyforce.finance.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import id.co.skyforce.finance.model.domain.CIF;
import id.co.skyforce.finance.util.HibernateUtil;

public class CIfModel {

	public void addCif(CIF cif) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(cif);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}

	public void deleteCif(int cifId) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			CIF cif = (CIF) session.load(CIF.class, new Integer(cifId));
			session.delete(cif);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}

	public void updateCif(CIF cif) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(cif);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}

	public List<CIF> getAllCif() {
		List<CIF> cifs = new ArrayList<CIF>();
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			cifs = session.createQuery("FROM CIF").list();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
		return cifs;
	}

	public List<CIF> searchByNameBirthDayMother(String search) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("FROM CIF where firstName LIKE :fn || lastName LIKE :ln || birhtDate LIKE :bd || motherMaidenName LIKE :mnm");
			query.setString("fn", "%" + search + "%");
			query.setString("ln", "%" + search + "%");
			query.setString("bd", "%" + search + "%");
			query.setString("mnm", "%" + search + "%");
			List<CIF>listCifs = query.list();
			if (listCifs.size() > 0) {
				return listCifs;
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
		return null;
	}
}
