package io.leasingninja.riskmanagement.application;

import static java.util.Objects.*;

import org.springframework.stereotype.Component;

import io.hschwentner.dddbits.annotation.ApplicationService;
import io.leasingninja.riskmanagement.domain.ContractNumber;
import io.leasingninja.riskmanagement.domain.ContractRepository;
import io.leasingninja.riskmanagement.domain.VoteResult;

@Component
@ApplicationService
public class VoteContract {

	private final ContractRepository contracts;

	public VoteContract(ContractRepository contracts) {
		requireNonNull(contracts);

		this.contracts = contracts;
	}

	public void vote(ContractNumber contractnumber, VoteResult result) {
		requireNonNull(contractnumber);
		requireNonNull(result);
		
		var contract = this.contracts.findById(contractnumber);

		contract.vote(result);

		this.contracts.save(contract);
	}

}
