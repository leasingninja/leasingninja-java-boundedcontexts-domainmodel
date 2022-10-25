package io.leasingninja.sales.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LeaseTermTest {
    @Test
    void given_whenALeaseTermIsCreatedOfYears_thenNoOfMonthsIsCorrect() {
        // given

        // when
        var leaseTerm = LeaseTerm.ofYears(4);

        // then
        assertThat(leaseTerm.noOfMonths()).isEqualTo(48);
    }
}
