package io.leasingninja.sales.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.leasingninja.sales.domain.SignDate;

class SignDateTest {

	@Test
	void test() {
		// when
		SignDate signDate1 = SignDate.of(2018, 8, 4);
		SignDate signDate2 = SignDate.of(2018, 8, 4);
		
		// then
		assertThat(signDate1).isEqualTo(signDate2);
	}

}
