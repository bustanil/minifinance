package id.co.skyforce.finance.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cif")
public class CIF {

	@Id
	@Column(name = "cif_no", length = 8, nullable = false)
	private String cifNo;

	@Column(name = "password", length = 10, nullable = false)
	private String password;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;

	@Column(name = "birth_date", length = 50, nullable = false)
	private Date birthDate;

	@Column(name = "mother_maiden_name", length = 50, nullable = false)
	private String motherMaidenName;

	@Column(name = "id_number", length = 50, nullable = false)
	private String idNumber;

	@Column(name = "id_type", length = 50, nullable = false)
	private Character idType;

	@OneToOne(mappedBy = "cif", cascade = CascadeType.ALL)
	private LoanAccount loanAccount;

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
