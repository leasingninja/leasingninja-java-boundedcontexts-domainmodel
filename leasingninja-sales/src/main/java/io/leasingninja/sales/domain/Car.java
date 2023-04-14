package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record Car(String car) {

	public static Car of(String car) {
		return new Car(car);
	}

}
