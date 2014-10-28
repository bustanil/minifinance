package id.co.skyforce.finance.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import id.co.skyforce.finance.model.CIF;
import id.co.skyforce.finance.model.User;
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

	public void deleteCif(CIF cif) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
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
	
	public CIF getCif(String cifNo){
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		CIF cif=null;
		
		try{
			transaction = session.beginTransaction();
			cif = (CIF) session.get(CIF.class, cifNo);
		} catch (Exception e){
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
		return cif;
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

	public List<CIF> searchByNoCif(String search) {
		Transaction transaction = null;
		Session session = HibernateUtil.openSession();
		try {
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("FROM CIF where cifNo LIKE :fn");
			query.setString("fn", "%" + search + "%");
			
			List<CIF> listCifs = query.list();
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
