package io.leasingninja.riskmanagement.domain;

import java.util.Collection;

import io.hschwentner.dddbits.annotation.DomainRepository;

@DomainRepository
public interface ContractRepository {

	Contract findById(ContractNumber number);
	
	Collection<Contract> findAll();
	
	void save(Contract contract);

}
