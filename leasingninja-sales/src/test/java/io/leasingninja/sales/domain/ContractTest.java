package io.leasingninja.sales.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Customer;
import io.leasingninja.sales.domain.SignDate;

class ContractTest {

	@Test
	void givenANewContract_whenSign_thenContractIsSigned() {
		// given
		var contract = new Contract(ContractNumber.of("4711"), Customer.of("John Buyer"),
				Car.of("Mercedes Benz C-Class"), Amount.of(20_000, "EUR"));

		// when
		contract.sign(SignDate.of(2018, 12, 24));

		// then
		assertThat(contract.isSigned()).isEqualTo(true);
		// check that event ContractSigned is fired
	}

	@Test
	void given_whenRestore_thenVertragContainsRestoredData() {
		// given

		// when
		Contract contract = Contract.restore(
				ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Mercedes Benz C-Class"),
				Amount.of(20_000, "EUR"),
				SignDate.of(2018, 04, 12));

		// then
		assertThat(contract.number()).isEqualTo(ContractNumber.of("4711"));
		assertThat(contract.lessee()).isEqualTo(Customer.of("John Buyer"));
		assertThat(contract.car()).isEqualTo(Car.of("Mercedes Benz C-Class"));
		assertThat(contract.price()).isEqualTo(Amount.of(20_000, "EUR"));
		assertThat(contract.isSigned()).isEqualTo(true);
		// check that event ContractSigned is fired
	}

}
