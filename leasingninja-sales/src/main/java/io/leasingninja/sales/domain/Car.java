package io.leasingninja.sales.domain;

import io.hschwentner.dddbits.annotation.ValueObject;
import io.hschwentner.dddbits.basetype.TinyStringType;

@ValueObject
public class Car extends TinyStringType {

	private Car(String car) {
		super(car);
	}

	public static Car of(String car) {
		return new Car(car);
	}

}
