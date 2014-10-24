package id.co.skyforce.finance.controller;

import java.util.List;

import id.co.skyforce.finance.model.CIF;
import id.co.skyforce.finance.util.HibernateUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Transaction;
import org.hibernate.Session;

@ManagedBean
public class CIFListController {

	private List<CIF> cifList;

	public CIFListController() {
		String cifNo = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("cif_no");

		if (cifNo != null) {
			Session session = HibernateUtil.openSession();
			Transaction trx = session.beginTransaction();

			cifList = session.createQuery("from CIF").list();

			trx.commit();
			session.close();
		}
	}

	public List<CIF> getCifList() {
		return cifList;
	}

	public void setCifList(List<CIF> cifList) {
		this.cifList = cifList;
	}

}
