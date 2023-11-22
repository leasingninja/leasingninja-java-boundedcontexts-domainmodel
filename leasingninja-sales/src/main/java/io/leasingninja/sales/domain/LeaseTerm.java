package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record LeaseTerm(int noOfMonths) {
    public LeaseTerm {
        assert noOfMonths > 0;
    }

    public static LeaseTerm ofMonths(int noOfMonths) {
        return new LeaseTerm(noOfMonths);
    }

    public static LeaseTerm ofYears(int noOfYears) {
        return ofMonths(noOfYears * 12);
    }
}
