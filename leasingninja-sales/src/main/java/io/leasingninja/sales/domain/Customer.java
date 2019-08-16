package io.leasingninja.sales.domain;

import io.hschwentner.dddbits.annotation.ValueObject;
import io.hschwentner.dddbits.basetype.TinyStringType;

@ValueObject
public class Customer extends TinyStringType {

	private Customer(String customer) {
		super(customer);
	}

	public static Customer of(String customer) {
		return new Customer(customer);
	}

}
