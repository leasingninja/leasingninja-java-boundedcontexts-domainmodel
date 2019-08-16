package io.leasingninja.riskmanagement.domain;

import io.hschwentner.dddbits.basetype.TinyDateType;

public final class SignDate extends TinyDateType {

	private SignDate(int year, int month, int dayOfMonth) {
		super(year, month, dayOfMonth);
	}
	
	public static SignDate of(int year, int month, int dayOfMonth) {
		return new SignDate(year, month, dayOfMonth);
	}

}
