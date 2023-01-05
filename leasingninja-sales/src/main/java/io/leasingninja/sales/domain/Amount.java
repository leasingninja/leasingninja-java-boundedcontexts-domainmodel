package io.leasingninja.sales.domain;


import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public class Amount {

	private final long amountInCents;
	private final Currency currency; // TODO: Change from String to Currency

	private Amount(long amountInCents, Currency currency) {
		this.amountInCents = amountInCents;
		this.currency = currency;
	}

	public static Amount of(double amount, Currency currency) {
		assert currency != null;

		return ofCents(Math.round(amount * 100), currency);
	}

	public static Amount ofCents(long amountInCents, Currency currency) {
		assert currency != null;

		return new Amount(amountInCents, currency);
	}

	public double amount() {
		return amountInCents / 100d;
	}

	public long amountInCents() {
		return amountInCents;
	}

	public Currency currency() {
		return currency;
	}

	@Override
	public String toString() {
		return currency() + " " + amount();
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
		if (amountInCents != other.amountInCents)
			return false;
		if (currency == null) {
			return other.currency == null;
		} else return currency.equals(other.currency);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(amountInCents);
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		return result;
	}

}
