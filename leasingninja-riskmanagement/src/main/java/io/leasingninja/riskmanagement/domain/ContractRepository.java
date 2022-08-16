package io.leasingninja.riskmanagement.domain;

import java.util.Collection;

import org.jmolecules.ddd.annotation.Repository;

@Repository
public interface ContractRepository {

	Contract findById(ContractNumber number);

	Collection<Contract> findAll();

	void save(Contract contract);

}
