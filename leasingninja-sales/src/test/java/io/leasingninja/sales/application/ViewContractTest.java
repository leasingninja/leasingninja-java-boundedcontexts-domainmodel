package io.leasingninja.sales.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.leasingninja.sales.application.ViewContract;
import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;
import io.leasingninja.sales.domain.Customer;

@ExtendWith(MockitoExtension.class)
class ViewContractTest {

	@Mock
	private Contracts contractsMock;
	
	@InjectMocks
	private ViewContract viewContractUnderTest;

	@Test
	void testLiesVertrag() {
		// Given
		given(contractsMock.with(ContractNumber.of("4711"))).willReturn(
				new Contract(
					ContractNumber.of("4711"),
					Customer.of("Bob Smith"),
					Car.of("Mercedes Benz E class"),
					Amount.of(10_000, "EUR")));
		
		// When
		Contract contract = viewContractUnderTest.with(ContractNumber.of("4711"));
		
		// Then
		//TODO: Fix this!
		assertThat(contract).isEqualToComparingFieldByField(
				new Contract(
					ContractNumber.of("4711"),
					Customer.of("Bob Smith"),
					Car.of("Mercedes Benz E class"),
					Amount.of(10_000, "EUR")));
	}

//	@Test
//	void testLiesVertrag() {
//		// Given
//		var vertrag = new LeasingVertrag(
//				Vertragsnummer.of("4711"),
//				Kundenname.of("Hans Schmidt"),
//				Fahrzeug.of("Mercedes Benz E-Klasse"),
//				Betrag.of(10_000, "EUR"));
//		given(vertragRepoMock.findById(Vertragsnummer.of("4711"))).willReturn(vertrag);
//		
//		// When
//		VertragModel model = viewContractUnderTest.liesVertrag("4711");
//		
//		// Then
//		assertThat(model).isEqualToComparingFieldByField(new VertragModel("4711", "Hans Schmidt", "Mercedes Benz E-Klasse", 10_000, "EUR"));
//	}

}
