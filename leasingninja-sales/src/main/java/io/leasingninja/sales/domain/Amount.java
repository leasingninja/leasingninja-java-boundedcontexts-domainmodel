package io.leasingninja.sales.domain;

import io.hschwentner.dddbits.annotation.ValueObject;

@ValueObject
public class Amount {

	private final int amount;
	private final String currency;

	private Amount(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public static Amount of(int amount, String currency) {
		assert currency != null;
		
		return new Amount(amount, currency);
	}

	public int amount() {
		return amount;
	}

	public String currency() {
		return currency;
	}

	@Override
	public String toString() {
		return "Amount [" + currency + " "  + amount + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Amount other = (Amount) obj;
		if (amount != other.amount)
			return false;
		if (currency == null) {
			return other.currency == null;
		} else return currency.equals(other.currency);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		return result;
	}

}
