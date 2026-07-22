package io.leasingninja.riskmanagement.userinterface;

import org.jspecify.annotations.Nullable;

class ContractModel {

	public String number;
	public @Nullable Integer creditRating;
	public String voteResult;

	public ContractModel() {
		this("", null, "");
	}

	public ContractModel(String number, @Nullable Integer creditRating, String voteResult) {
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

	public @Nullable Integer getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(@Nullable Integer creditRating) {
		this.creditRating = creditRating;
	}

	public String getVoteResult() {
		return voteResult;
	}

	public void setVoteResult(String voteResult) {
		this.voteResult = voteResult;
	}
}
