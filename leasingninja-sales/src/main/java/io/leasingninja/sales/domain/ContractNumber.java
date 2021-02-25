package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;
import io.hschwentner.dddbits.basetype.TinyStringType;

@ValueObject
public final class ContractNumber extends TinyStringType {
	
	private ContractNumber(String number) {
		super(number);		
	}

	public static ContractNumber of(String number) {
		return new ContractNumber(number);
	}

}
