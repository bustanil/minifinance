import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import id.co.skyforce.finance.model.LoanAccount;
import id.co.skyforce.finance.model.LoanAccountSchedule;
import id.co.skyforce.finance.service.LoanAccountScheduleService;

public class TestLoanAccountScheduleService {
	public static void main(String[] args) throws Exception {
		// BigDecimal one = BigDecimal.ONE;
		// BigDecimal three = new BigDecimal(3);
		// BigDecimal oneDivideThree = one.divide(three, 0, RoundingMode.UP);
		// System.out.println(oneDivideThree);

		LoanAccount flatLoanAccount = new LoanAccount();
		LoanAccount effectiveLoanAccount = new LoanAccount();
		LoanAccount annuityLoanAccount = new LoanAccount();

		flatLoanAccount.setAccountNo("001");
		flatLoanAccount.setInterestRate(new BigDecimal(6));
		flatLoanAccount.setInterestType('F');
		flatLoanAccount.setPlafond(new BigDecimal(12000000));
		flatLoanAccount.setStartDate(new Date());
		flatLoanAccount.setTenure(12);
		LoanAccountScheduleService.generateSchedule(flatLoanAccount);

		effectiveLoanAccount.setAccountNo("002");
		effectiveLoanAccount.setInterestRate(new BigDecimal(12));
		effectiveLoanAccount.setInterestType('E');
		effectiveLoanAccount.setPlafond(new BigDecimal(12000000));
		effectiveLoanAccount.setStartDate(new Date());
		effectiveLoanAccount.setTenure(12);
		LoanAccountScheduleService.generateSchedule(effectiveLoanAccount);

		annuityLoanAccount.setAccountNo("003");
		annuityLoanAccount.setInterestRate(new BigDecimal(15));
		annuityLoanAccount.setInterestType('A');
		annuityLoanAccount.setPlafond(new BigDecimal(12000000));
		annuityLoanAccount.setStartDate(new Date());
		annuityLoanAccount.setTenure(36);
		LoanAccountScheduleService.generateSchedule(annuityLoanAccount);

		System.out.println("Flat Rate");
		TestLoanAccountScheduleService
				.printLoanAccountSchedule(flatLoanAccount);
		System.out.println();

		System.out.println("Effective Rate");
		TestLoanAccountScheduleService
				.printLoanAccountSchedule(effectiveLoanAccount);
		System.out.println();

		System.out.println("Annuity Rate");
		TestLoanAccountScheduleService
				.printLoanAccountSchedule(annuityLoanAccount);
		System.out.println();
	}

	private static void printLoanAccountSchedule(LoanAccount loanAccount) {
		System.out
				.println("==========================================================================");
		System.out.printf("| %-5s | %-14s | %-14s | %-10s | %-15s |\n",
				"Bulan", "Saldo Angsuran", "Pokok Angsuran", "Bunga",
				"Jumlah Angsuran");
		System.out
				.println("==========================================================================");

		for (LoanAccountSchedule loanAccountSchedule : loanAccount
				.getLoanAccountSchedules()) {
			System.out.printf("| %5d | %14.2f | %14.2f | %10.2f | %15.2f |\n",
					loanAccountSchedule.getPeriod(),
					loanAccountSchedule.getInstallmentBalance(),
					loanAccountSchedule.getPrincipal(),
					loanAccountSchedule.getInterest(),
					loanAccountSchedule.getInstallment());
		}

		System.out
				.println("==========================================================================");
	}
}
