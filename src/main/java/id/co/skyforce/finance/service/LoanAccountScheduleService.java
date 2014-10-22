package id.co.skyforce.finance.service;

import id.co.skyforce.finance.model.domain.LoanAccount;
import id.co.skyforce.finance.model.domain.LoanAccountSchedule;

import java.math.BigDecimal;
import java.util.ArrayList;
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

	private static List<LoanAccountSchedule> generateScheduleWithFlatRate(
			LoanAccount loanAccount) {
		BigDecimal principal = loanAccount.getPlafond().divide(
				new BigDecimal(loanAccount.getTenure()));
		BigDecimal interest = loanAccount.getInterestRate().divide(
				new BigDecimal(100));
		BigDecimal installment = principal.add(interest);
		List<LoanAccountSchedule> loanAccountSchedules = new ArrayList<LoanAccountSchedule>();

		for (int i = 0; i < loanAccount.getTenure(); i++) {
			LoanAccountSchedule loanAccountSchedule = new LoanAccountSchedule();

			loanAccountSchedule.setPeriod(Integer.valueOf(i + 1));
			loanAccountSchedule.setPrincipal(principal);
			loanAccountSchedule.setInterest(interest);
			loanAccountSchedule.setInstallment(installment);
			loanAccountSchedule.setOutstanding(BigDecimal.ZERO);
			loanAccountSchedule.setPaidStatus('U');

			loanAccountSchedules.add(loanAccountSchedule);
		}

		return loanAccountSchedules;
	}

	private static List<LoanAccountSchedule> generateScheduleWithEffectiveRate(
			LoanAccount loanAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<LoanAccountSchedule> generateScheduleWithAnnuityeRate(
			LoanAccount loanAccount) {
		// TODO Auto-generated method stub
		return null;
	}
}
