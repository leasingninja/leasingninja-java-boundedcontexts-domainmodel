package io.leasingninja.sales.infrastructure;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Customer;
import io.leasingninja.sales.infrastructure.ContractRepositoryMemImpl;

class ContractRepositoryMemImplTest {

	private ContractRepositoryMemImpl repoUnderTest;
	
	@Test
	void test() {
		// given
		repoUnderTest = new ContractRepositoryMemImpl();
		
		// when
		repoUnderTest.save(new Contract(
				ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Mercedes Benz C class"),
				Amount.of(20_000, "EUR")));
		var contract = repoUnderTest.with(ContractNumber.of("4711"));
		
		// then
		assertThat(contract).isEqualToComparingFieldByField(
				new Contract(
						ContractNumber.of("4711"),
						Customer.of("John Buyer"),
						Car.of("Mercedes Benz C class"),
						Amount.of(20_000, "EUR")));
	}

}
