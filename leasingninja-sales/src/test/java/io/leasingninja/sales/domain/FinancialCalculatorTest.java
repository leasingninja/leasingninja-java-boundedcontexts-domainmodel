package io.leasingninja.sales.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FinancialCalculatorTest {
	@Test
	void pmt() {
		// given

		// when
		double pmt = FinancialCalculator.pmt(48, 3.7 / 12, -40_000, 0, 0);

		// then
		assertThat(pmt).isEqualTo(897.8022814470006);
	}

}
