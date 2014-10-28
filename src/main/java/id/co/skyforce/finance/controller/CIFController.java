package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.CIF;
import id.co.skyforce.finance.service.CIFService;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class CIFController {

	private CIFService service;
	private List<CIF> ciflist;
	private CIF cif;
	private String no = null;
	private String search;

	public CIFController() {
		service = new CIFService();
		ciflist = service.getAllCif();

		no = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("noCif");

		if (no != null) {
			this.cif = service.getCif(no);
		} else {
			cif = new CIF();
		}
	}

	public String save() {
		service = new CIFService();
		service.addUpdateCif(this.cif);
		return "blankcif?faces-redirect=true";
	}

	public void delete() {
		service = new CIFService();
		no = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("noCif");
		this.cif = service.getCif(no);
		service.deleteCif(cif);
		ciflist = service.getAllCif();
	}
	
	public void search() {
		ciflist = service.searchByNoCif(search);
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<CIF> getCiflist() {
		return ciflist;
	}

	public void setCiflist(List<CIF> ciflist) {
		this.ciflist = ciflist;
	}

	public CIF getCif() {
		return cif;
	}

	public void setCif(CIF cif) {
		this.cif = cif;
	}
}
