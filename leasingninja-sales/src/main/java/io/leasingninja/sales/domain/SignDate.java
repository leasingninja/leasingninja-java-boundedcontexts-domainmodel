package io.leasingninja.sales.domain;

import java.time.LocalDate;

import org.jmolecules.ddd.annotation.ValueObject;
import io.hschwentner.dddbits.basetype.TinyDateType;

@ValueObject
public final class SignDate extends TinyDateType {

	// TODO: both of() variants?
	public static SignDate of(LocalDate date) {
		assert date != null;
		return new SignDate(date);
	}
	
	public static SignDate of(int year, int month, int dayOfMonth) {
		return new SignDate(year, month, dayOfMonth);
	}
	
	private SignDate(LocalDate date) {
		super(date);
	}

	private SignDate(int year, int month, int dayOfMonth) {
		super(year, month, dayOfMonth);
	}

}
