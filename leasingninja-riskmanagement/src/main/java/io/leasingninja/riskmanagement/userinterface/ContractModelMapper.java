package io.leasingninja.riskmanagement.userinterface;

import io.leasingninja.riskmanagement.domain.Contract;

import java.util.Objects;

public class ContractModelMapper {

	public static ContractModel modelFrom(Contract contract) { // TODO: static??
		Objects.requireNonNull(contract);

		return new ContractModel(
				contract.identity().value(),
				//contract.isRated() ? contract.rating().toString() : "<not rated yet>",
				contract.isRated() ? Integer.valueOf(contract.rating().value()) : null,
				contract.isVoted() ? contract.votingResult().toString() : "<not voted yet>");
	}

}
