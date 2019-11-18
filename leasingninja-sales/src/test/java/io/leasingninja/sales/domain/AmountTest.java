package io.leasingninja.sales.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AmountTest {
    @Test
    void givenTwoEqualAmounts_whenEquals_thenAreEqual() {
        // given
        var amount1 = Amount.of(100, "EUR");
        var amount2 = Amount.of(100, "EUR");

        // when
        boolean areEqual = amount1.equals(amount2);

        // then
        assertThat(areEqual).isTrue();
    }

    @Test
    void givenTwoUnequalAmounts_whenEquals_thenAreNotEqual() {
        // given
        var amount1 = Amount.of(100, "EUR");
        var amount2 = Amount.of(200, "EUR");

        // when
        boolean areEqual = amount1.equals(amount2);

        // then
        assertThat(areEqual).isFalse();
    }

    @Test
    void givenTwoAmountsWithUnequalCurrencies_whenEquals_thenAreNotEqual() {
        // given
        var amount1 = Amount.of(100, "EUR");
        var amount2 = Amount.of(100, "GBP");

        // when
        boolean areEqual = amount1.equals(amount2);

        // then
        assertThat(areEqual).isFalse();
    }

}