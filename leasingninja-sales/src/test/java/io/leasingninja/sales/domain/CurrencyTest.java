package io.leasingninja.sales.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyTest {

    @Test
    void givenTwoUnequalCurrencies_whenEquals_thenAreNotEqual() {
        // given
        var currency1 = Currency.EUR;
        var currency2 = Currency.USD;

        // when
        boolean areEqual = currency1.equals(currency2);

        // then
        assertThat(areEqual).isFalse();
    }

}
