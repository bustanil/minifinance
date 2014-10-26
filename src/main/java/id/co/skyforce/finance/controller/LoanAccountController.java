package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.CIF;
import id.co.skyforce.finance.model.LoanAccount;
import id.co.skyforce.finance.model.LoanAccountSchedule;
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
	private List<LoanAccountSchedule> loanAccountSchedules;
	
	public void simulate() throws Exception {
		LoanAccount loanAccounts = new LoanAccount();
		loanAccounts.setAccountNo(accountNo);
		loanAccounts.setPlafond(plafond);
		loanAccounts.setTenure(tenure);
		loanAccounts.setInterestType(interestType);
		loanAccounts.setStartDate(startDate);
		loanAccounts.setInterestRate(interestRate);
		LoanAccountScheduleService.generateSchedule(loanAccounts);
		LoanAccountService loanAccountService = new LoanAccountService();
		loanAccountService.addAcount(loanAccounts);
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
}
