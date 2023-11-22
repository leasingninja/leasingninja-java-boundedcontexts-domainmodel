package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

/**
 * Interest in percent.
 */
@ValueObject
public record Interest(double perYear) {
    public Interest {
        assert perYear >= 0;
    }

    public static Interest of(double perYear) {
        return new Interest(perYear);
    }

    public double perMonth() {
        return perYear() / 12;
    }

    @Override
    public String toString() {
        return "Interest [" + perYear() + "% p.a.]";
    }
}
