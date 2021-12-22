package io.leasingninja.sales.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.hschwentner.dddbits.annotation.ApplicationService;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;

@ApplicationService
public class ViewContract {

    private static Logger logger = LoggerFactory.getLogger(ViewContract.class);

	private final Contracts contracts;

	public ViewContract(Contracts contracts) {
		this.contracts = contracts;
	}
	
	public Contract with(ContractNumber number) {
		var contract = this.contracts.with(number);
		logger.debug("Repository returned contract: " + contract);
		return contract;
	}

//	public VertragModel with(String vertragsnummer) {
//		var vertrag = this.vertragRepo.findById(Vertragsnummer.of(vertragsnummer));
//		logger.debug("UnterschreibeVertragApplicationService: vertrag: " + vertrag);
//		return vertrag != null 
//				? VertragModelMapper.modelFrom(vertrag)
//				: new VertragModel();
////		return VertragModelMapper.INSTANCE.vertragToVertragModel(vertrag);
//	}

}
