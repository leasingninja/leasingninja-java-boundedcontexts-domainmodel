package io.leasingninja.riskmanagement.application;

import static java.util.Objects.*;

import org.springframework.stereotype.Component;

import io.hschwentner.dddbits.annotation.ApplicationService;
import io.leasingninja.riskmanagement.domain.ContractNumber;
import io.leasingninja.riskmanagement.domain.ContractRepository;
import io.leasingninja.riskmanagement.domain.CreditRating;

@Component
@ApplicationService
public class CheckCreditRating {

	private final ContractRepository contracts;

	public CheckCreditRating(ContractRepository contracts) {
		requireNonNull(contracts);

		this.contracts = contracts;
	}

	public void checkCreditRating(ContractNumber contractnumber, CreditRating rating) {
		requireNonNull(contractnumber);
		requireNonNull(rating);
		System.out.println("1- ContractNumber contractnumber : " + contractnumber + " CreditRating rating :" + rating);
		var contract = this.contracts.findById(contractnumber);
		System.out.println("2- ContractNumber contractnumber : " + contractnumber + " CreditRating rating :" + rating);
		contract.checkCreditRating(rating);
		System.out.println("3- ContractNumber contractnumber : " + contractnumber + " CreditRating rating :" + rating);
		this.contracts.save(contract);
		System.out.println("4- ContractNumber contractnumber : " + contractnumber + " CreditRating rating :" + rating);
	}

}
