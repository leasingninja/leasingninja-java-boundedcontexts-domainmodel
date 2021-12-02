package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

/**
 * Interest in percent.
 */
@ValueObject
public record Interest(double perYear) {
    public static Interest of(double perYear) {
        return new Interest(perYear);
    }

    public double perMonth() {
        return perYear() / 12;
    }
}
