package id.co.skyforce.finance.model.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loan_account_schedule")
public class LoanAccountSchedule {
	@Id
	@Column(name = "period", nullable = false)
	private Integer period;

	@Column(name = "due_date", nullable = true)
	private Date dueDate;

	@Column(name = "principal", nullable = true, precision = 15, scale = 2)
	private BigDecimal principal;

	@Column(name = "interest", nullable = true, precision = 15, scale = 2)
	private BigDecimal interest;

	@Column(name = "installment", nullable = true, precision = 15, scale = 2)
	private BigDecimal installment;

	@Column(name = "outstanding", nullable = true, precision = 15, scale = 2)
	private BigDecimal outstanding;

	@Column(name = "paid_status", nullable = true)
	private Character paidStatus;

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
