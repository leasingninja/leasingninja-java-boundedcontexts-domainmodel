package io.leasingninja.sales.ui;

import io.leasingninja.sales.domain.Contract;

class ContractModelMapper {

	public static ContractModel modelFrom(Contract contract) {
		return new ContractModel(
				contract.number().value(),
				contract.lessee().value(),
				contract.car().value(),
				contract.price().amount(),
				contract.price().currency());
	}

}
