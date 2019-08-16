package io.leasingninja.riskmanagement.domain;

import io.hschwentner.dddbits.basetype.TinyStringType;

public class ContractNumber extends TinyStringType {

	protected ContractNumber(String contractnumber) {
		super(contractnumber);
	}
	
	public static ContractNumber of(String contractnumber) {
		return new ContractNumber(contractnumber);
	}

}
