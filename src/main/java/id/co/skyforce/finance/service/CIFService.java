package id.co.skyforce.finance.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import id.co.skyforce.finance.model.CIF;
import id.co.skyforce.finance.util.HibernateUtil;

public class CIFService {
	
	public void addUpdateCif(CIF cif) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(cif);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}

	public void addCif(CIF cif) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(cif);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}

	public void deleteCif(String cifId) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			CIF cif = (CIF) session.get(CIF.class, cifId);
			session.delete(cif);
			transaction.commit();
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
					.createQuery("FROM CIF where firstName LIKE :fn OR lastName LIKE :ln OR birthDate LIKE :bd OR motherMaidenName LIKE :md");
			query.setString("fn", "%" + search + "%");
			query.setString("ln", "%" + search + "%");
			query.setString("bd", "%" + search + "%");
			query.setString("md", "%" + search + "%");
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
