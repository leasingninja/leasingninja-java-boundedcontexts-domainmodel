package io.leasingninja.sales.domain;

import io.hschwentner.dddbits.annotation.DomainRepository;

@DomainRepository
public interface Contracts {

	Contract with(ContractNumber number);

	void save(Contract contract);

}
