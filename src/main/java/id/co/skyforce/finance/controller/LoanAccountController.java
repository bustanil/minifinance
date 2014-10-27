package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.CIF;
import id.co.skyforce.finance.model.LoanAccount;
import id.co.skyforce.finance.model.LoanAccountSchedule;
import id.co.skyforce.finance.service.CIFService;
import id.co.skyforce.finance.service.LoanAccountScheduleService;
import id.co.skyforce.finance.service.LoanAccountService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoanAccountController {

	private String accountNo;
	private BigDecimal plafond;
	private Integer tenure;
	private BigDecimal interestRate;
	private Character interestType;
	private Date startDate;
	private String cifNo;
	private List<LoanAccountSchedule> loanAccounts;
	private CIF cif;
	private CIFService cifService;
	
	private Integer period;
	private Date dueDate;
	private BigDecimal principal;
	private BigDecimal interest;
	private BigDecimal installment;
	private BigDecimal outstanding;
	private Character paidStatus;
	
	LoanAccount loanAccount = new LoanAccount();
	
	public void simulate() throws Exception {
		loanAccount.setAccountNo(accountNo);
		loanAccount.setPlafond(plafond);
		loanAccount.setTenure(tenure);
		loanAccount.setInterestType(interestType);
		loanAccount.setStartDate(new Date());
		loanAccount.setInterestRate(interestRate);
		LoanAccountScheduleService.generateSchedule(loanAccount);

		this.loanAccounts = loanAccount.getLoanAccountSchedules();
	}
	
	public void save() throws Exception{
		loanAccount.setAccountNo(accountNo);
		loanAccount.setPlafond(plafond);
		loanAccount.setTenure(tenure);
		loanAccount.setInterestRate(interestRate);
		loanAccount.setInterestType(interestType);
		loanAccount.setStartDate(new Date());
		
		cifService = new CIFService();
		
		cif = cifService.getCif(cifNo);
		loanAccount.setCif(cif);
		
		LoanAccountService loanAccountService = new LoanAccountService();
		
		LoanAccountScheduleService.generateSchedule(loanAccount);
		
		loanAccountService.addAcount(loanAccount);
	}

	public List<LoanAccountSchedule> getLoanAccounts() {
		return loanAccounts;
	}

	public void setLoanAccounts(List<LoanAccountSchedule> loanAccounts) {
		this.loanAccounts = loanAccounts;
	}

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

	public String getCifNo() {
		return cifNo;
	}

	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}

	//
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
