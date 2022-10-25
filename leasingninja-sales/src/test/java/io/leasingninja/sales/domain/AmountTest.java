package io.leasingninja.sales.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountTest {
    @Test
    void givenTwoEqualAmounts_whenEquals_thenAreEqual() {
        // given
        var amount1 = Amount.of(100, Currency.EUR);
        var amount2 = Amount.of(100, Currency.EUR);

        // when
        boolean areEqual = amount1.equals(amount2);

        // then
        assertThat(areEqual).isTrue();
    }

    @Test
    void givenTwoUnequalAmounts_whenEquals_thenAreNotEqual() {
        // given
        var amount1 = Amount.of(100, Currency.EUR);
        var amount2 = Amount.of(200, Currency.EUR);

        // when
        boolean areEqual = amount1.equals(amount2);

        // then
        assertThat(areEqual).isFalse();
    }

    @Test
    void givenTwoAmountsWithUnequalCurrencies_whenEquals_thenAreNotEqual() {
        // given
        var amount1 = Amount.of(100, Currency.EUR);
        var amount2 = Amount.of(100, Currency.GBP);

        // when
        boolean areEqual = amount1.equals(amount2);

        // then
        assertThat(areEqual).isFalse();
    }

    @Test
    void givenTwoAmountsWithRoundingAfterThePoint_whenEquals_thenAreEqual() {
        // given
        var amount1 = Amount.of(100.45, Currency.EUR);
        var amount2 = Amount.of(100.447123, Currency.EUR);

        // when
        boolean areEqual = amount1.equals(amount2);

        // then
        assertThat(areEqual).isTrue();
    }

    @Test
    void givenAnAmountsWithCents_whenToString_thenAfterThePointIsCorrectlyPrinted() {
        // given
        var amount = Amount.of(100.45, Currency.EUR);

        // when
        String amountString = amount.toString();

        // then
        assertThat(amountString).isEqualTo("EUR 100.45");
    }

    @Test
    void givenTwoAmountsOfEurosAndCents_whenEquals_thenAreEqual() {
        // given
        var amount1 = Amount.of(100.45, Currency.EUR);
        var amount2 = Amount.ofCents(10045, Currency.EUR);

        // when
        boolean areEqual = amount1.equals(amount2);

        // then
        assertThat(areEqual).isTrue();
    }

}
