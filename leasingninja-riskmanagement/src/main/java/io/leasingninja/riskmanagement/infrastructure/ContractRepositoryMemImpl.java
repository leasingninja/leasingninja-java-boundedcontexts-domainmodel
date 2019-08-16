package io.leasingninja.riskmanagement.infrastructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.leasingninja.riskmanagement.domain.Contract;
import io.leasingninja.riskmanagement.domain.ContractNumber;
import io.leasingninja.riskmanagement.domain.ContractRepository;

public class ContractRepositoryMemImpl implements ContractRepository {

	private final Map<ContractNumber, Contract> repo;
	
	public ContractRepositoryMemImpl() {
		repo = new HashMap<>();
	}

	@Override
	public Contract findById(ContractNumber number) {
		return repo.get(number);
	}

	@Override
	public Collection<Contract> findAll() {
		return repo.values();
	}

	@Override
	public void save(Contract contract) {
		repo.put(contract.identity(), contract);
	}

}
