package io.leasingninja.riskmanagement.application;

import static java.util.Objects.*;

import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;

import org.springframework.stereotype.Component;

import io.leasingninja.riskmanagement.domain.Contract;
import io.leasingninja.riskmanagement.domain.ContractNumber;
import io.leasingninja.riskmanagement.domain.ContractRepository;

@Component
@ApplicationLayer
@Service
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
