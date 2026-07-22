package io.leasingninja.sales.domain;


import static java.util.Objects.requireNonNull;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record Amount(long amountInCents, Currency currency) {

    public Amount {
        requireNonNull(currency);
    }

    public static Amount of(double amount, Currency currency) {
		requireNonNull(currency);

		return ofCents(Math.round(amount * 100), currency);
	}

	public static Amount ofCents(long amountInCents, Currency currency) {
		requireNonNull(currency);

		return new Amount(amountInCents, currency);
	}

    public double amount() {
		return amountInCents / 100d;
	}

    public Amount add(Amount otherAmount) {
        assert this.currency == otherAmount.currency;

        return ofCents(this.amountInCents + otherAmount.amountInCents, this.currency);
    }

    public Amount subtract(Amount otherAmount) {
        assert this.currency == otherAmount.currency;

        return ofCents(this.amountInCents - otherAmount.amountInCents, this.currency);
    }

    @Override
	public String toString() {
		return currency() + " " + amount();
	}

}
