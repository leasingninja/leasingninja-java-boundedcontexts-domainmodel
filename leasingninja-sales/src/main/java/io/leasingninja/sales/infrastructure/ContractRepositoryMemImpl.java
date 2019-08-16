package io.leasingninja.sales.infrastructure;

import java.util.HashMap;
import java.util.Map;

import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;

public class ContractRepositoryMemImpl implements Contracts {
	
	private final Map<ContractNumber, Contract> repo;
	
	public ContractRepositoryMemImpl() {
		repo = new HashMap<>();
	}

	@Override
	public void save(Contract contract) {
		repo.put(contract.number(), contract);
	}

	@Override
	public Contract with(ContractNumber number) {
		System.out.println("with:" + number);
		return repo.get(number);
	}

}
