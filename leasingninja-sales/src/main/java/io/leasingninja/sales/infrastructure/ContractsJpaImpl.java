package io.leasingninja.sales.infrastructure;

import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;

public class ContractsJpaImpl implements Contracts {

	private final ContractDatabaseEntityRepository repo;
	
	public ContractsJpaImpl(ContractDatabaseEntityRepository repo) {
		this.repo = repo;
	}

	@Override
	public void save(Contract contract) {
		repo.save(ContractDatabaseEntity.from(contract));
	}

	@Override
	public Contract with(ContractNumber number) {
		return repo.findById(number.value()).get().toContract();
	}

}
