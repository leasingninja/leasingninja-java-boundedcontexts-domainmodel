package io.leasingninja.sales.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ContractFactoryTest {

    @Test
	void given_whenRestore_thenContractContainsRestoredData() {
		// given

		// when
		Contract contract = ContractFactory.restoreContract(
				ContractNumber.of("4711"),
				Customer.of("John Buyer"),
				Car.of("Mercedes Benz C-Class"),
				Amount.of(20_000, Currency.EUR),
                Optional.of(LeaseTerm.ofMonths(48)),
                Optional.of(Interest.of(3.6)),
				Optional.of(SignDate.of(2018, 04, 12)));

		// then
		assertThat(contract.number()).isEqualTo(ContractNumber.of("4711"));
		assertThat(contract.lessee()).isEqualTo(Customer.of("John Buyer"));
		assertThat(contract.car()).isEqualTo(Car.of("Mercedes Benz C-Class"));
		assertThat(contract.price()).isEqualTo(Amount.of(20_000, Currency.EUR));
		assertThat(contract.isCalculated()).isTrue();
		assertThat(contract.isSigned()).isTrue();
		// check that event ContractSigned is fired
	}

}
