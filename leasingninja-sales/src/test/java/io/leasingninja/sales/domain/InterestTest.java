package io.leasingninja.sales.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InterestTest {

    @Test
    void givenAnInterest_whenPerMonth_thenCorrectValue() {
        // given
        var interest = Interest.of(3.6);

        // when
        double perMonth = interest.perMonth();

        // then
        assertThat(perMonth).isEqualTo(0.3);
    }

}
