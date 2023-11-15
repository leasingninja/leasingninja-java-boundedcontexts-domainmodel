package io.leasingninja.sales.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountTest {

    /*
     * We don’t need to test equals() here, since we’re using records.
     * Please compare the following project to get an idea of what
     * is required for versions without record types:
     * https://github.com/leasingninja/leasingninja-javabefore14-boundedcontexts-domainmodel
     */

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

    @Test
    void givenTwoAmounts_whenAdd_thenSumIsCorrect() {
        // given
        var amount1 = Amount.of(100, Currency.EUR);
        var amount2 = Amount.of(200, Currency.EUR);

        // when
        var sum = amount1.add(amount2);

        // then
        assertThat(sum).isEqualTo(Amount.of(300, Currency.EUR));
    }

    @Test
    void givenTwoAmounts_whenSubtract_thenDifferenceIsCorrect() {
        // given
        var amount1 = Amount.of(300, Currency.EUR);
        var amount2 = Amount.of(200, Currency.EUR);

        // when
        var sum = amount1.subtract(amount2);

        // then
        assertThat(sum).isEqualTo(Amount.of(100, Currency.EUR));
    }
}
