package io.leasingninja.riskmanagement.domain;

// TODO: umbauen auf TinyIntType mit Grenzen 1 - 10
public enum 	CreditRating {
	GOOD("Good"), Accepted("Accepted"), Accepted_with_obligations("Accepted with obligations");

	String value;
	CreditRating(String good) {
		this.value = good;
	}
}
