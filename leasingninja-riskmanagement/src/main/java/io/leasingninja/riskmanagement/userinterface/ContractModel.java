package io.leasingninja.riskmanagement.userinterface;

class ContractModel {

	public String number;
	public Integer creditRating;
	public String voteResult;

	public ContractModel() {
		this("", null, "");
	}

	public ContractModel(String number, Integer creditRating, String voteResult) {
		this.number = number;
		this.creditRating = creditRating;
		this.voteResult = voteResult;
	}

	@Override
	public String toString() {
		return "ContractModel[" +
				"number = '" + number + '\'' +
				", creditRating = '" + creditRating + '\'' +
				", voteResult = '" + voteResult + '\'' +
				']';
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(Integer creditRating) {
		this.creditRating = creditRating;
	}

	public String getVoteResult() {
		return voteResult;
	}

	public void setVoteResult(String voteResult) {
		this.voteResult = voteResult;
	}
}
