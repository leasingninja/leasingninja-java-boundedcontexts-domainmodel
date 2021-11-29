package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record Interest(double perYear) {
    public static Interest of(double perYear) {
        return new Interest(perYear);
    }
}
