package id.co.skyforce.finance.service;

import id.co.skyforce.finance.model.domain.LoanAccount;
import id.co.skyforce.finance.model.domain.LoanAccountSchedule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoanAccountScheduleService {
	public static List<LoanAccountSchedule> generateSchedule(
			LoanAccount loanAccount) throws Exception {
		if (loanAccount.getInterestType() == 'F') {
			return LoanAccountScheduleService
					.generateScheduleWithFlatRate(loanAccount);
		} else if (loanAccount.getInterestType() == 'E') {
			return LoanAccountScheduleService
					.generateScheduleWithEffectiveRate(loanAccount);
		} else if (loanAccount.getInterestType() == 'A') {
			return LoanAccountScheduleService
					.generateScheduleWithAnnuityeRate(loanAccount);
		}

		throw new Exception(
				"Jenis bunga tidak diketahui. Jenis bunga: 'F'/'E'/'A'");
	}

	// TODO Test
	private static List<LoanAccountSchedule> generateScheduleWithFlatRate(
			LoanAccount loanAccount) {
		List<LoanAccountSchedule> loanAccountSchedules = new ArrayList<LoanAccountSchedule>();
		Calendar calendar = Calendar.getInstance();

		BigDecimal principal = loanAccount.getPlafond().divide(
				new BigDecimal(loanAccount.getTenure()));
		BigDecimal periodInterestRate = loanAccount.getInterestRate()
				.divide(new BigDecimal(100))
				.divide(new BigDecimal(loanAccount.getTenure()));
		BigDecimal periodInterest = principal.multiply(periodInterestRate);
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
			loanAccountSchedule.setOutstanding(BigDecimal.ZERO);
			loanAccountSchedule.setPaidStatus('U');

			loanAccountSchedules.add(loanAccountSchedule);
		}

		return loanAccountSchedules;
	}

	// TODO Test
	private static List<LoanAccountSchedule> generateScheduleWithEffectiveRate(
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
			loanAccountSchedule.setOutstanding(BigDecimal.ZERO);
			loanAccountSchedule.setPaidStatus('U');

			loanAccountSchedules.add(loanAccountSchedule);
			installmentBalance = installmentBalance.subtract(principal);
		}

		return loanAccountSchedules;
	}

	private static List<LoanAccountSchedule> generateScheduleWithAnnuityeRate(
			LoanAccount loanAccount) {
		// TODO Auto-generated method stub
		return null;
	}
}
