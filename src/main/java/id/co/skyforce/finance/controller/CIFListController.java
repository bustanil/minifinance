package id.co.skyforce.finance.controller;

import java.util.List;

import id.co.skyforce.finance.model.CIF;
import id.co.skyforce.finance.service.CIFService;
import id.co.skyforce.finance.util.HibernateUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Transaction;
import org.hibernate.Session;

@ManagedBean
public class CIFListController {

	private List<CIF> cifList;

	public CIFListController() {
		CIFService service = new CIFService();

		cifList = service.getAllCif();
		

	}

	public List<CIF> getCifList() {
		return cifList;
	}

	public void setCifList(List<CIF> cifList) {
		this.cifList = cifList;
	}

}
