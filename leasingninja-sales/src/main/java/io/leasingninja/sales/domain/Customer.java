package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record Customer(String customer) {

	public static Customer of(String customer) {
		return new Customer(customer);
	}

    public static boolean isValid(String nameString) {
        return nameString.matches("/^(\\p{L}+\\s)*\\p{L}+$/");
    }

}
