package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.LoanAccount;
import id.co.skyforce.finance.model.Payment;
import id.co.skyforce.finance.service.LoanAccountService;
import id.co.skyforce.finance.service.PaymentService;
import id.co.skyforce.finance.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
public class PaymentController {

	private Integer id;
	private Date transactionDate;
	private BigDecimal amount;
	private String acountNo;
	private Character paymentStatus;

	private Integer paymentId;
	private List<LoanAccount> loanAccounts;
	private LoanAccountService loanAccountService;
	PaymentService paymentService = new PaymentService();
	Payment payment = new Payment();
	LoanAccount loanAccount = new LoanAccount();
	
	public List<LoanAccount> getLoanAccounts() {
		return loanAccounts;
	}

	public void setLoanAccounts(List<LoanAccount> loanAccounts) {
		this.loanAccounts = loanAccounts;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}


	public PaymentController() {
		String id = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id");

		if (id != null) {
			paymentId = Integer.valueOf(id);
			Session session = HibernateUtil.openSession();
			Transaction trx = session.beginTransaction();

			Payment payment = (Payment) session.get(Payment.class, paymentId);

			this.id = payment.getId();
			this.transactionDate = payment.getTransactionDate();
			this.amount = payment.getAmount();
			this.acountNo = payment.getAcountNo();
			this.paymentStatus = payment.getPaymentStatus();

			trx.commit();
			session.close();
		}
		
		loanAccountService = new LoanAccountService();
		this.loanAccounts = loanAccountService.getAllLoanAccount();
	}

	public void save() throws Exception {
		
		payment.setTransactionDate(new Date());
		payment.setAmount(amount);
		payment.setAcountNo(acountNo);
		payment.setPaymentStatus(paymentStatus);
		
		loanAccountService = new LoanAccountService();
		loanAccount = loanAccountService.getAccountNo(acountNo);
		
		paymentService.addPayment(payment);
		paymentService.payInstallment(payment);
	}

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAcountNo() {
		return acountNo;
	}

	public void setAcountNo(String acountNo) {
		this.acountNo = acountNo;
	}

	public Character getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Character paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
