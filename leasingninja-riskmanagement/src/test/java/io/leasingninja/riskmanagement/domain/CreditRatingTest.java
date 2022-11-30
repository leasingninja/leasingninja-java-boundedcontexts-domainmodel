package io.leasingninja.riskmanagement.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CreditRatingTest {
	@Test
	void testIsValid() {
		assertThat(CreditRating.isValid(0)).isFalse();
		assertThat(CreditRating.isValid(1)).isTrue();
		assertThat(CreditRating.isValid(3)).isTrue();
		assertThat(CreditRating.isValid(10)).isTrue();
		assertThat(CreditRating.isValid(11)).isFalse();
	}
}
