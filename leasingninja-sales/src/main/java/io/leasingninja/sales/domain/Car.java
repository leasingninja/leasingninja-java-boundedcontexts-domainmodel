package io.leasingninja.sales.domain;

import com.sun.istack.NotNull;
import org.jmolecules.ddd.annotation.ValueObject;
import io.hschwentner.dddbits.basetype.TinyStringType;

import javax.validation.constraints.NotBlank;

@ValueObject
public class Car extends TinyStringType {


	private Car(String car) {
		super(car);
	}

	public static Car of(String car) {
		return new Car(car);
	}

}
