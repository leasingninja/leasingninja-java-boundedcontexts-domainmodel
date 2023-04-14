package io.leasingninja.sales.ui;

import io.leasingninja.sales.domain.Contract;

import java.util.Objects;

class ContractModelMapper {

	public static ContractModel modelFrom(Contract contract) {
		Objects.requireNonNull(contract);

		return new ContractModel(
				contract.number().number(),
				contract.lessee().customer(),
				contract.car().car(),
				contract.price().amount(),
				contract.price().currency().name());
	}

}
