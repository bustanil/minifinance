package id.co.skyforce.finance.controller;

import id.co.skyforce.finance.model.CIF;
import id.co.skyforce.finance.model.Payment;
import id.co.skyforce.finance.service.PaymentService;
import id.co.skyforce.finance.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private PaymentService service;

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public PaymentService getService() {
		return service;
	}

	public void setService(PaymentService service) {
		this.service = service;
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
	}

	public void save() {
		service = new PaymentService();
		Payment payment = new Payment();
		payment.setTransactionDate(new Date());
		payment.setAmount(amount);
		payment.setAcountNo(acountNo);
		payment.setPaymentStatus(paymentStatus);
		service.addPayment(payment);
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
