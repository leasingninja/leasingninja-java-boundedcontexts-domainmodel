package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.Repository;

@Repository
public interface Contracts {

	Contract with(ContractNumber number);

	void save(Contract contract);

}
