package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.LoanAccount;
import id.co.skyforce.finance.model.LoanAccountSchedule;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoanAccountScheduleController {

	private LoanAccount loanAccount;
	private Integer period;
	private Date dueDate;
	private BigDecimal principal;
	private BigDecimal interest;
	private BigDecimal installment;
	private BigDecimal outstanding;
	private Character paidStatus;
	private BigDecimal installmentBalance;
	
	public LoanAccountScheduleController() {
		LoanAccountSchedule loanAccountSchedules = new LoanAccountSchedule();
		period = loanAccountSchedules.getPeriod();
		installmentBalance = loanAccountSchedules.getInstallmentBalance();
		principal = loanAccountSchedules.getPrincipal();
		interest = loanAccountSchedules.getInterest();
		installment = loanAccountSchedules.getInstallment();
	}
	
	public LoanAccount getLoanAccount() {
		return loanAccount;
	}
	public void setLoanAccount(LoanAccount loanAccount) {
		this.loanAccount = loanAccount;
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
	public BigDecimal getInstallmentBalance() {
		return installmentBalance;
	}
	public void setInstallmentBalance(BigDecimal installmentBalance) {
		this.installmentBalance = installmentBalance;
	}
}
