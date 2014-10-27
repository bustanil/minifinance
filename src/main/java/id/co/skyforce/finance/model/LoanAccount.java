package id.co.skyforce.finance.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "loan_account")
public class LoanAccount {

	@Id
	@Column(name = "account_no", length = 6, nullable = false)
	private String accountNo;

	@Column(name = "plafond", precision = 15, scale = 2, nullable = false)
	private BigDecimal plafond;

	@Column(name = "tenure", length = 11, nullable = false)
	private Integer tenure;

	@Column(name = "interest_rate", precision = 5, scale = 2, nullable = false)
	private BigDecimal interestRate;

	@Column(name = "interest_type", length = 1, nullable = false)
	private Character interestType;

	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cif_no")
	//@PrimaryKeyJoinColumn
	private CIF cif;
	
	
	
	
	
	

	@OneToMany(mappedBy = "loanAccount", cascade = CascadeType.ALL)
//	@JoinColumn(name = "account_no")
	private List<LoanAccountSchedule> loanAccountSchedules = new ArrayList<>();
	
	//
/*	@OneToMany(cascade = CascadeType.ALL)
	private Payment payment;
*/
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public BigDecimal getPlafond() {
		return plafond;
	}

	public void setPlafond(BigDecimal plafond) {
		this.plafond = plafond;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Character getInterestType() {
		return interestType;
	}

	public void setInterestType(Character interestType) {
		this.interestType = interestType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public CIF getCif() {
		return cif;
	}

	public void setCif(CIF cif) {
		this.cif = cif;
	}

	public List<LoanAccountSchedule> getLoanAccountSchedules() {
		return loanAccountSchedules;
	}

	public void setLoanAccountSchedules(
			List<LoanAccountSchedule> loanAccountSchedules) {
		this.loanAccountSchedules = loanAccountSchedules;
	}
}
