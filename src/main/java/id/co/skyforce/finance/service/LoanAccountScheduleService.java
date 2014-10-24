package id.co.skyforce.finance.service;

import id.co.skyforce.finance.model.LoanAccount;
import id.co.skyforce.finance.model.LoanAccountSchedule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoanAccountScheduleService {
	public static void generateSchedule(LoanAccount loanAccount)
			throws Exception {
		if (loanAccount.getTenure() <= 0) {
			throw new Exception("Tenure <= 0. Seharusnya > 0.");
		}

		if (loanAccount.getInterestType() == 'F') {
			LoanAccountScheduleService
					.generateScheduleWithFlatRate(loanAccount);
		} else if (loanAccount.getInterestType() == 'E') {
			LoanAccountScheduleService
					.generateScheduleWithEffectiveRate(loanAccount);
		} else if (loanAccount.getInterestType() == 'A') {
			LoanAccountScheduleService
					.generateScheduleWithAnnuityeRate(loanAccount);
		} else {
			throw new Exception(
					"Jenis bunga tidak diketahui. Jenis bunga: 'F'/'E'/'A'");
		}
	}

	// TODO Test
	private static void generateScheduleWithFlatRate(LoanAccount loanAccount) {
		List<LoanAccountSchedule> loanAccountSchedules = new ArrayList<LoanAccountSchedule>();
		Calendar calendar = Calendar.getInstance();

		BigDecimal principal = loanAccount.getPlafond().divide(
				new BigDecimal(loanAccount.getTenure()));
		BigDecimal interestRate = loanAccount.getInterestRate().divide(
				new BigDecimal(100));
		BigDecimal interest = loanAccount.getPlafond().multiply(interestRate);
		BigDecimal periodInterest = interest.divide(new BigDecimal(loanAccount
				.getTenure()));
		BigDecimal installment = principal.add(periodInterest);

		calendar.setTime(loanAccount.getStartDate());

		for (int i = 0; i < loanAccount.getTenure(); i++) {
			LoanAccountSchedule loanAccountSchedule = new LoanAccountSchedule();
			Date dueDate;

			calendar.add(Calendar.MONTH, 1);
			dueDate = calendar.getTime();

			loanAccountSchedule.setPeriod(Integer.valueOf(i + 1));
			loanAccountSchedule.setDueDate(dueDate);
			loanAccountSchedule.setPrincipal(principal);
			loanAccountSchedule.setInterest(periodInterest);
			loanAccountSchedule.setInstallment(installment);
			loanAccountSchedule.setOutstanding(installment);
			loanAccountSchedule.setPaidStatus('U');
			loanAccountSchedule.setLoanAccount(loanAccount);

			loanAccountSchedules.add(loanAccountSchedule);
		}

		loanAccount.setLoanAccountSchedules(loanAccountSchedules);
	}

	// TODO Test
	private static void generateScheduleWithEffectiveRate(
			LoanAccount loanAccount) {
		List<LoanAccountSchedule> loanAccountSchedules = new ArrayList<LoanAccountSchedule>();
		Calendar calendar = Calendar.getInstance();

		BigDecimal installmentBalance = loanAccount.getPlafond();
		BigDecimal principal = loanAccount.getPlafond().divide(
				new BigDecimal(loanAccount.getTenure()));
		BigDecimal periodInterestRate = loanAccount.getInterestRate()
				.divide(new BigDecimal(100))
				.divide(new BigDecimal(loanAccount.getTenure()));

		calendar.setTime(loanAccount.getStartDate());

		for (int i = 0; i < loanAccount.getTenure(); i++) {
			LoanAccountSchedule loanAccountSchedule = new LoanAccountSchedule();
			BigDecimal periodInterest = periodInterestRate
					.multiply(installmentBalance);
			BigDecimal installment = principal.add(periodInterest);
			Date dueDate;

			calendar.add(Calendar.MONTH, 1);
			dueDate = calendar.getTime();

			loanAccountSchedule.setPeriod(Integer.valueOf(i + 1));
			loanAccountSchedule.setDueDate(dueDate);
			loanAccountSchedule.setPrincipal(principal);
			loanAccountSchedule.setInterest(periodInterest);
			loanAccountSchedule.setInstallment(installment);
			loanAccountSchedule.setOutstanding(installment);
			loanAccountSchedule.setPaidStatus('U');
			loanAccountSchedule.setLoanAccount(loanAccount);

			loanAccountSchedules.add(loanAccountSchedule);
			installmentBalance = installmentBalance.subtract(principal);
		}

		loanAccount.setLoanAccountSchedules(loanAccountSchedules);
	}

	// TODO test
	private static void generateScheduleWithAnnuityeRate(LoanAccount loanAccount) {
		List<LoanAccountSchedule> loanAccountSchedules = new ArrayList<LoanAccountSchedule>();
		Calendar calendar = Calendar.getInstance();

		BigDecimal installmentBalance = loanAccount.getPlafond();
		BigDecimal installment = LoanAccountScheduleService
				.calculateAnnuityInstallment(loanAccount);

		calendar.setTime(loanAccount.getStartDate());

		for (int i = 0; i < loanAccount.getTenure(); i++) {
			LoanAccountSchedule loanAccountSchedule = new LoanAccountSchedule();
			BigDecimal periodInterest = LoanAccountScheduleService
					.calculateAnnuityPeriodInterest(installmentBalance,
							loanAccount);
			BigDecimal principal = LoanAccountScheduleService
					.calculateAnnuityPrincipal(installment, periodInterest);
			Date dueDate;

			calendar.add(Calendar.MONTH, 1);
			dueDate = calendar.getTime();

			loanAccountSchedule.setPeriod(Integer.valueOf(i + 1));
			loanAccountSchedule.setDueDate(dueDate);
			loanAccountSchedule.setPrincipal(principal);
			loanAccountSchedule.setInterest(periodInterest);
			loanAccountSchedule.setInstallment(installment);
			loanAccountSchedule.setOutstanding(installment);
			loanAccountSchedule.setPaidStatus('U');
			loanAccountSchedule.setLoanAccount(loanAccount);

			loanAccountSchedules.add(loanAccountSchedule);
			installmentBalance = installmentBalance.subtract(principal);
		}

		loanAccount.setLoanAccountSchedules(loanAccountSchedules);
	}

	private static BigDecimal calculateAnnuityInstallment(
			LoanAccount loanAccount) {
		/**
		 * http://jnet99.wordpress.com/2008/12/26/perhitungan-suku-bunga-kredit-
		 * anuitas/
		 */
		BigDecimal installment = loanAccount
				.getPlafond()
				.multiply(
						loanAccount
								.getInterestRate()
								.divide(new BigDecimal(loanAccount.getTenure()))
								.multiply(
										BigDecimal.ONE.divide(BigDecimal.ONE.subtract(BigDecimal.ONE
												.divide(BigDecimal.ONE
														.add(loanAccount
																.getInterestRate()
																.divide(new BigDecimal(
																		100))
																.divide(new BigDecimal(
																		loanAccount
																				.getTenure())))
														.pow(loanAccount
																.getTenure()))))));

		return installment;
	}

	private static BigDecimal calculateAnnuityPeriodInterest(
			BigDecimal lastBalance, LoanAccount loanAccount) {
		/**
		 * http://jnet99.wordpress.com/2008/12/26/perhitungan-suku-bunga-kredit-
		 * anuitas/
		 */
		BigDecimal periodInterest = lastBalance.multiply(loanAccount
				.getInterestRate().divide(
						new BigDecimal(100).divide(new BigDecimal(loanAccount
								.getTenure()))));

		return periodInterest;
	}

	private static BigDecimal calculateAnnuityPrincipal(BigDecimal installment,
			BigDecimal periodInterest) {
		return installment.subtract(periodInterest);
	}
}
