package io.leasingninja.sales.application;

import io.hschwentner.dddbits.annotation.ApplicationService;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;

@ApplicationService
public class ViewContract {

	private final Contracts contracts;

	public ViewContract(Contracts contracts) {
		this.contracts = contracts;
	}
	
	public Contract with(ContractNumber number) {
		var contract = this.contracts.with(number);
		System.out.println("ViewContract: " + contract);
		return contract;
	}

//	public VertragModel with(String vertragsnummer) {
//		var vertrag = this.vertragRepo.findById(Vertragsnummer.of(vertragsnummer));
//		System.out.println("UnterschreibeVertragApplicationService: vertrag: " + vertrag);
//		return vertrag != null 
//				? VertragModelMapper.modelFrom(vertrag)
//				: new VertragModel();
////		return VertragModelMapper.INSTANCE.vertragToVertragModel(vertrag);
//	}

}
