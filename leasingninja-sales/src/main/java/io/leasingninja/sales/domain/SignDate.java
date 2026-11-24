package io.leasingninja.sales.domain;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record SignDate(LocalDate date) {

    public SignDate {
        requireNonNull(date);
    }

	// TODO: both of() variants?
	public static SignDate of(LocalDate date) {
		requireNonNull(date);
		return new SignDate(date);
	}

	public static SignDate of(int year, int month, int dayOfMonth) {
		return of(LocalDate.of(year, month, dayOfMonth));
	}

    // TODO: year() vs. getYear im Domain Model?
	public final int year() {
		return date.getYear();
	}

	public final int month() {
		return date.getMonthValue();
	}

	public final int day() {
		return date.getDayOfMonth();
	}

}
