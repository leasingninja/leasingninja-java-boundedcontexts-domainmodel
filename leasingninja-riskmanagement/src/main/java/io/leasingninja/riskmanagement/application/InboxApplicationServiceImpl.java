package io.leasingninja.riskmanagement.application;

import io.leasingninja.riskmanagement.domain.Contract;
import io.leasingninja.riskmanagement.domain.ContractNumber;
import io.leasingninja.riskmanagement.domain.ContractRepository;
import io.leasingninja.riskmanagement.domain.SignDate;
import org.springframework.stereotype.Component;

import static java.time.temporal.ChronoField.*;

@Component
class InboxApplicationServiceImpl implements InboxApplicationService {

	private ContractRepository contracts;

	public InboxApplicationServiceImpl(ContractRepository contracts) {
		this.contracts = contracts;
	}

	@Override
	public void confirmSignedContract(String lvnr, int year, int month, int dayOfMonth) {
		YEAR.checkValidValue(year);
		MONTH_OF_YEAR.checkValidValue(month);
		DAY_OF_MONTH.checkValidValue(dayOfMonth);

		contracts.save(new Contract(ContractNumber.of(lvnr), SignDate.of(year, month, dayOfMonth)));
	}
}
