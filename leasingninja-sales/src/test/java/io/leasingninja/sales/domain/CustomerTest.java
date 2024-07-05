package io.leasingninja.sales.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    @Test
    void givenAStringWithOnlyLetters_whenIsValid_thenTrue() {
        // given
        var nameString = "John";

        // when
        boolean isValid = Customer.isValid(nameString);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    void givenAStringWithNonStandardLatinCharacter_whenIsValid_thenTrue() {
        // given
        var nameString = "Björn";

        // when
        boolean isValid = Customer.isValid(nameString);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    void givenAStringWithOnlyLettersAndSpace_whenIsValid_thenTrue() {
        // given
        var nameString = "John Buyer";

        // when
        boolean isValid = Customer.isValid(nameString);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    void givenAStringWithNonStandardLatinCharacterAndSpace_whenIsValid_thenTrue() {
        // given
        var nameString = "John le Carré";

        // when
        boolean isValid = Customer.isValid(nameString);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    void givenAStringWithNumbers_whenIsValid_thenFalse() {
        // given
        var nameString = "John42";

        // when
        boolean isValid = Customer.isValid(nameString);

        // then
        assertThat(isValid).isFalse();
    }

}
