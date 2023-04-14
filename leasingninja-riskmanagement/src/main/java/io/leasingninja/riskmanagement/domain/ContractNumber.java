package io.leasingninja.riskmanagement.domain;

public record ContractNumber(String contractnumber) {

	public static ContractNumber of(String contractnumber) {
		return new ContractNumber(contractnumber);
	}

}
