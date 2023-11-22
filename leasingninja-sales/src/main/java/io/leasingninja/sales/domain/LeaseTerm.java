package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record LeaseTerm(int noOfMonths) {
    public static LeaseTerm ofMonths(int noOfMonths) {
        assert noOfMonths > 0;
        return new LeaseTerm(noOfMonths);
    }

    public static LeaseTerm ofYears(int noOfYears) {
        return ofMonths(noOfYears * 12);
    }
}
