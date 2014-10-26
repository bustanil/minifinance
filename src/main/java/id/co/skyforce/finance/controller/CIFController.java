package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.CIF;
import id.co.skyforce.finance.service.CIFService;
import id.co.skyforce.finance.util.HibernateUtil;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
public class CIFController {

	private String cifNo;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String motherMaidenName;
	private String idNumber;
	private Character idType;
	private static CIFService service;

	public CIFController() {
		String cifNo = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("cif_no");

		if (cifNo != null) {
			Session session = HibernateUtil.openSession();
			Transaction trx = session.beginTransaction();

			CIF cif = (CIF) session.get(CIF.class, cifNo);

			this.cifNo = cif.getCifNo();
			this.password = cif.getPassword();
			this.email = cif.getEmail();
			this.firstName = cif.getFirstName();
			this.lastName = cif.getLastName();
			this.birthDate = cif.getBirthDate();
			this.motherMaidenName = cif.getMotherMaidenName();
			this.idNumber = cif.getIdNumber();
			this.idType = cif.getIdType();

			trx.commit();
			session.close();
		}
	}

	public void save() {
		service = new CIFService();
		CIF cif = new CIF();
		cif.setCifNo(cifNo);
		cif.setPassword(password);
		cif.setEmail(email);
		cif.setFirstName(firstName);
		cif.setLastName(lastName);
		cif.setBirthDate(birthDate);
		cif.setMotherMaidenName(motherMaidenName);
		cif.setIdNumber(idNumber);
		cif.setIdType(idType);
		service.addUpdateCif(cif);
	}

	public void delete() {
		service = new CIFService();
		service.deleteCif(cifNo);
	}

	public String getCifNo() {
		return cifNo;
	}

	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMotherMaidenName() {
		return motherMaidenName;
	}

	public void setMotherMaidenName(String motherMaidenName) {
		this.motherMaidenName = motherMaidenName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Character getIdType() {
		return idType;
	}

	public void setIdType(Character idType) {
		this.idType = idType;
	}

}
