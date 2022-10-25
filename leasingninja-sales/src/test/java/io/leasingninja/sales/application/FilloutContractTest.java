package io.leasingninja.sales.application;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;
import io.leasingninja.sales.domain.Currency;
import io.leasingninja.sales.domain.Customer;

@ExtendWith(MockitoExtension.class)
class FilloutContractTest {

	@Mock
	private Contracts contractsMock;

	@InjectMocks
	private FilloutContract filloutContractUnderTest;

	@Test
	void givenEmptyContract_WhenFillout_ThenSave() {
		// Given

		// When
		filloutContractUnderTest.with(
				ContractNumber.of("4711"),
				Customer.of("Bob Smith"),
				Car.of("Mercedes Benz E-Class"),
				Amount.of(10_000, Currency.EUR));


		// Then
		then(contractsMock).should().save(refEq(new Contract(
				ContractNumber.of("4711"),
				Customer.of("Bob Smith"),
				Car.of("Mercedes Benz E-Class"),
				Amount.of(10_000,  Currency.EUR))));
	}

}
