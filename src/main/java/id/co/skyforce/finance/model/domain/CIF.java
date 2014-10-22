package id.co.skyforce.finance.model.domain;

import java.util.Date;

public class CIF {

	private String cifNo;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String motherMaiden;
	private String idNumber;
	private Character idType;

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

	public String getMotherMaiden() {
		return motherMaiden;
	}

	public void setMotherMaiden(String motherMaiden) {
		this.motherMaiden = motherMaiden;
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
