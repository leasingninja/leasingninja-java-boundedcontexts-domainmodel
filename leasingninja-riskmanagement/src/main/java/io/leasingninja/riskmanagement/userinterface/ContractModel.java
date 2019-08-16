package io.leasingninja.riskmanagement.userinterface;

class ContractModel {
	public ContractModel() {
	}
	
	public ContractModel(String number, String creditRating, String voteResult) {
		this.number = number;
		this.creditRating = creditRating;
		this.voteResult = voteResult;
	}

	public String number = "";
	public String creditRating = "";
	public String voteResult = "";
}
