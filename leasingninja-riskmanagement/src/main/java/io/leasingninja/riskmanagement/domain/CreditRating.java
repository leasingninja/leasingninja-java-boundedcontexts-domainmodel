package io.leasingninja.riskmanagement.domain;

public record CreditRating(int value) {

	public CreditRating {
		assert isValid(value);
	}

	public static boolean isValid(int value) {
		return value >= 1 && value <= 10;
	}

	public static CreditRating of(int value) {
		return new CreditRating(value);
	}
}
