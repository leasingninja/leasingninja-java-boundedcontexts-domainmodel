package io.leasingninja.riskmanagement.application;

import static java.util.Objects.*;

import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;

import org.springframework.stereotype.Component;

import io.leasingninja.riskmanagement.domain.ContractNumber;
import io.leasingninja.riskmanagement.domain.ContractRepository;
import io.leasingninja.riskmanagement.domain.CreditRating;

@Component
@ApplicationLayer
@Service
public class CheckCreditRating {

	private final ContractRepository contracts;

	public CheckCreditRating(ContractRepository contracts) {
		requireNonNull(contracts);

		this.contracts = contracts;
	}

	public void checkCreditRating(ContractNumber contractnumber, CreditRating rating) {
		requireNonNull(contractnumber);
		requireNonNull(rating);

		var contract = this.contracts.findById(contractnumber);

		contract.checkCreditRating(rating);

		this.contracts.save(contract);
	}

}
