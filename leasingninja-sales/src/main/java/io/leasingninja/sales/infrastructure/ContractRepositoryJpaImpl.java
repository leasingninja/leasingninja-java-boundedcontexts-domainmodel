package io.leasingninja.sales.infrastructure;

import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;

public class ContractRepositoryJpaImpl implements Contracts {

//	private static final Logger LOGGER = LoggerFactory.getLogger("io.leasingninja.vertrieb.infrastructure.VertragRepositoryJpaImpl");
    
	private final ContractDatabaseEntityRepository repo;
	
	public ContractRepositoryJpaImpl(ContractDatabaseEntityRepository repo) {
		this.repo = repo;
	}

	@Override
	public void save(Contract contract) {
//		LOGGER.info("Saving Vertrag " + leasingVertrag);
		System.out.println("ContractRepositoryJpaImpl: Saving contract " + contract);
		repo.save(ContractDatabaseEntity.from(contract));
	}

	@Override
	public Contract with(ContractNumber number) {
//		LOGGER.info("Reading Vertrag with nummer " + id);
		System.out.println("ContractRepositoryJpaImpl: Reading contract with number " + number);
		return repo.findById(number.value()).get().toContract();
	}

}
