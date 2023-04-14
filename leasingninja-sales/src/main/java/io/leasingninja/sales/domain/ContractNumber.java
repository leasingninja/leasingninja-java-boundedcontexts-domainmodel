package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record ContractNumber(String number) {

	public static ContractNumber of(String number) {
		return new ContractNumber(number);
	}

}
