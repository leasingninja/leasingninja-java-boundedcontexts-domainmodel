package io.leasingninja.sales.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class ContractTest {

	@Test
	void givenAFilledOutContract_whenCalculate_thenInstallmentIsX() {
		// given
		var contract = new Contract(ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Volkswagen ID.3"),
				Amount.of(40_000, "EUR"));

		// when
		contract.calculateInstallmentFor(LeaseTerm.ofMonths(48), Interest.of(3.7));

		// then
		assertThat(contract.isCalculated()).isTrue();
		assertThat(contract.installment()).isEqualTo(Amount.of(897.80, "EUR"));
	}

	@Test
	void givenAFilledOutContractWith0Interest_whenCalculate_thenInstallmentIsX() {
		// given
		var contract = new Contract(ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Volkswagen ID.3"),
				Amount.of(40_000, "EUR"));

		// when
		contract.calculateInstallmentFor(LeaseTerm.ofMonths(48), Interest.of(0));

		// then
		assertThat(contract.isCalculated()).isTrue();
		assertThat(contract.installment()).isEqualTo(Amount.of(833.3333333333334, "EUR"));
	}

	@Test
	void givenANewContract_whenSign_thenContractIsSigned() {
		// given
		var contract = new Contract(ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Mercedes Benz C-Class"),
				Amount.of(20_000, "EUR"));

		// when
		contract.sign(SignDate.of(2018, 12, 24));

		// then
		assertThat(contract.isSigned()).isTrue();
		assertThat(contract.signDate()).isEqualTo(SignDate.of(2018, 12, 24));
		// check that event ContractSigned is fired
	}

	@Test
	void given_whenRestore_thenContractContainsRestoredData() {
		// given

		// when
		Contract contract = Contract.restore(
				ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Mercedes Benz C-Class"),
				Amount.of(20_000, "EUR"),
				Optional.of(SignDate.of(2018, 04, 12)));

		// then
		assertThat(contract.number()).isEqualTo(ContractNumber.of("4711"));
		assertThat(contract.lessee()).isEqualTo(Customer.of("John Buyer"));
		assertThat(contract.car()).isEqualTo(Car.of("Mercedes Benz C-Class"));
		assertThat(contract.price()).isEqualTo(Amount.of(20_000, "EUR"));
		assertThat(contract.isSigned()).isEqualTo(true);
		// check that event ContractSigned is fired
	}

}
