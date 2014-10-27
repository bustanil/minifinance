import java.math.BigDecimal;
import java.util.Date;

import id.co.skyforce.finance.model.Payment;
import id.co.skyforce.finance.service.PaymentService;

public class TestPayInstallment {

	public static void main(String[] args) throws Exception {
		PaymentService paymentService = new PaymentService();
		Payment payment = new Payment();
		payment.setAcountNo("001");
		payment.setAmount(new BigDecimal(1060000));
		payment.setPaymentStatus('N');
		payment.setTransactionDate(new Date());

		paymentService.addPayment(payment);
		paymentService.payInstallment(payment);
	}

}
