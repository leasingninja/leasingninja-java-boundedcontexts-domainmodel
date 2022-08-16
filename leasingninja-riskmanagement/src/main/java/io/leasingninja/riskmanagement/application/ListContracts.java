package io.leasingninja.riskmanagement.application;

import java.util.Collection;

import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.stereotype.Component;

import io.leasingninja.riskmanagement.domain.Contract;
import io.leasingninja.riskmanagement.domain.ContractRepository;

@Component
@ApplicationLayer
@Service
public class ListContracts {

	private final ContractRepository contracts;

	public ListContracts(ContractRepository contracts) {
		this.contracts = contracts;
	}

	public Collection<Contract> all() {
		return contracts.findAll();
	}

}
