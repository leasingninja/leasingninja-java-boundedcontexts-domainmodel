package io.leasingninja.sales.domain;


import org.jmolecules.ddd.annotation.ValueObject;

import java.math.BigDecimal;

@ValueObject
public class Amount {

	private final String amountInCents;
	private final String currency;

	private Amount(String amountInCents, String currency) {
		this.amountInCents = amountInCents;
		this.currency = currency;
	}

	public static Amount of(double amount, String currency) {
		assert currency != null;
		
		// return new Amount(Math.round(amount * 100), currency);
		return new Amount(String.valueOf(amount), currency);
	}

	public static Amount ofCents(double amount, String currency) {
		assert currency != null;
		
		return new Amount(String.valueOf(amount * 100), currency);
	}

	public String amount() {
		//return String.valueOf(  Double.valueOf(amountInCents) / 100d);
		return amountInCents;
	}

	public String amountInCents() {
		return amountInCents;
	}

	public String currency() {
		return currency;
	}

	@Override
	public String toString() {
		return currency + " " + amount();
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
		BigDecimal otherLong =  new BigDecimal(other.amountInCents);
		BigDecimal amountInCentsLong = new BigDecimal(amountInCents);
		if (otherLong.longValue() != amountInCentsLong.longValue() )
			return false;
		if (currency == null) {
			return other.currency == null;
		} else {
			return currency.equals(other.currency);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(Long.valueOf(amountInCents));
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		return result;
	}

}
