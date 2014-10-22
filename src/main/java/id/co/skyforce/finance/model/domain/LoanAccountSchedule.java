package id.co.skyforce.finance.model.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import org.hibernate.annotations.Entity;

@Entity
@Table(name = "loan_account_schedule")
public class LoanAccountSchedule {
	private CIF cif;
	private Integer period;
	private Date dueDate;
	private BigDecimal principal;
	private BigDecimal interest;
	private BigDecimal installment;
	private BigDecimal outstanding;
	private Character paidStatus;

	public CIF getCif() {
		return cif;
	}

	public void setCif(CIF cif) {
		this.cif = cif;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getInstallment() {
		return installment;
	}

	public void setInstallment(BigDecimal installment) {
		this.installment = installment;
	}

	public BigDecimal getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(BigDecimal outstanding) {
		this.outstanding = outstanding;
	}

	public Character getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(Character paidStatus) {
		this.paidStatus = paidStatus;
	}
}
