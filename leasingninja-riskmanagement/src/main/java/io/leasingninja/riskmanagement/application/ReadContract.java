package io.leasingninja.riskmanagement.application;

import static java.util.Objects.*;

import org.springframework.stereotype.Component;

import io.hschwentner.dddbits.annotation.ApplicationService;
import io.leasingninja.riskmanagement.domain.Contract;
import io.leasingninja.riskmanagement.domain.ContractNumber;
import io.leasingninja.riskmanagement.domain.ContractRepository;

@Component
@ApplicationService
public class ReadContract {

	private final ContractRepository contracts;

	public ReadContract(ContractRepository contracts) {
		requireNonNull(contracts);

		this.contracts = contracts;
	}

	public Contract readContract(ContractNumber number) {
		requireNonNull(number);
		
		return this.contracts.findById(number);
	}

}
