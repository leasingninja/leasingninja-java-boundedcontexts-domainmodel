package io.leasingninja.riskmanagement.userinterface;

import io.leasingninja.riskmanagement.domain.Contract;

public class ContractModelMapper {

	public static ContractModel modelFrom(Contract contract) {
		return new ContractModel(
				contract.identity().toString(),
				contract.rating().toString(),
				contract.votingResult().toString());
	}

}
