package io.leasingninja.sales.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContractTest {

	@Test
	void givenAFilledOutContract_whenCalculate_thenInstallmentIsX() {
		// given
		var contract = new Contract(ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Volkswagen ID.3"),
				Amount.of(40_000, Currency.EUR));

		// when
		contract.calculateInstallmentFor(LeaseTerm.ofMonths(48), Interest.of(3.7));

		// then
		assertThat(contract.isCalculated()).isTrue();
		assertThat(contract.leaseTerm()).isEqualTo(LeaseTerm.ofMonths(48));
		assertThat(contract.interest()).isEqualTo(Interest.of(3.7));
		assertThat(contract.installment()).isEqualTo(Amount.of(897.80, Currency.EUR));
	}

	@Test
	void givenAFilledOutContractWith0Interest_whenCalculate_thenInstallmentIsX() {
		// given
		var contract = new Contract(ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Volkswagen ID.3"),
				Amount.of(40_000, Currency.EUR));

		// when
		contract.calculateInstallmentFor(LeaseTerm.ofMonths(48), Interest.of(0));

		// then
		assertThat(contract.isCalculated()).isTrue();
		assertThat(contract.installment()).isEqualTo(Amount.of(833.33, Currency.EUR));
	}

	@Test
	void givenACalculatedContract_whenSign_thenContractIsSigned() {
		// given
		var contract = new Contract(ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Mercedes Benz C-Class"),
				Amount.of(20_000, Currency.EUR));
        contract.calculateInstallmentFor(LeaseTerm.ofMonths(48), Interest.of(3.7));

		// when
		contract.sign(SignDate.of(2018, 12, 24));

		// then
		assertThat(contract.isSigned()).isTrue();
		assertThat(contract.signDate()).isEqualTo(SignDate.of(2018, 12, 24));
		// check that event ContractSigned is fired
	}

	@Test
	void givenTwoContractsWithSameIdButDifferentFields_whenEquals_thenShouldReturnTrue() {
		// given
		var contract1 = new Contract(ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Mercedes Benz C-Class"),
				Amount.of(40_000, Currency.EUR));
        var contract2 = new Contract(ContractNumber.of("4711"),
				Customer.of("Bob Myers"),
				Car.of("Volkswagen ID.3"),
				Amount.of(30_000, Currency.EUR));

		// when
		var equal = contract1.equals(contract2);

		// then
		assertThat(equal).isTrue();
	}

	@Test
	void givenTwoContractsWithDifferentIdButSameFields_whenEquals_thenShouldReturnFalse() {
		// given
		var contract1 = new Contract(ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Mercedes Benz C-Class"),
				Amount.of(40_000, Currency.EUR));
        var contract2 = new Contract(ContractNumber.of("4712"),
				Customer.of("John Buyer"),
				Car.of("Mercedes Benz C-Class"),
				Amount.of(40_000, Currency.EUR));

		// when
		var equal = contract1.equals(contract2);

		// then
		assertThat(equal).isFalse();
	}

	@Test
	void givenTwoContractsWithSameId_whenHashcode_thenShouldBeEqual() {
		// given
		var contract1 = new Contract(ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Mercedes Benz C-Class"),
				Amount.of(40_000, Currency.EUR));
        var contract2 = new Contract(ContractNumber.of("4711"),
				Customer.of("Bob Myers"),
				Car.of("Volkswagen ID.3"),
				Amount.of(30_000, Currency.EUR));

		// when
		var hashcode1 = contract1.hashCode();
		var hashcode2 = contract2.hashCode();

		// then
		assertThat(hashcode1).isEqualTo(hashcode2);
	}

}
