package io.leasingninja.sales.application;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.leasingninja.riskmanagement.application.InboxApplicationService;
import io.leasingninja.sales.application.SignContract;
import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;
import io.leasingninja.sales.domain.Customer;
import io.leasingninja.sales.domain.SignDate;

@ExtendWith(MockitoExtension.class)
class SignContractTest {

	@Mock
	private Contracts contractsMock;
	
	@Mock
	private InboxApplicationService inboxApplicationServiceMock;
	
	@InjectMocks
	private SignContract applicationServiceUnderTest;

	@Test
	void givenAContract_WhenSign_ThenSignedContractIsSaved() {
		// Given
		var contract = new Contract(
				ContractNumber.of("4711"),
				Customer.of("Bob Smith"),
				Car.of("Mercedes Benz E-Class"),
				Amount.of(10_000, "EUR"));
		given(contractsMock.with(ContractNumber.of("4711"))).willReturn(contract);
		
		// When
		applicationServiceUnderTest.with(
				ContractNumber.of("4711"),
//				"2018-04-12");
				SignDate.of(2018, 4, 12));
		
		// Then
		then(contractsMock).should().save(refEq(Contract.restore(
				ContractNumber.of("4711"),
				Customer.of("Bob Smith"),
				Car.of("Mercedes Benz E-Class"),
				Amount.of(10_000,  "EUR"),
				Optional.of(SignDate.of(2018, 04, 12)))));
//		then(inboxApplicationServiceMock).should().meldeUnterschriebenenVertrag("4711", "2018-04-12");
		then(inboxApplicationServiceMock).should().confirmSignedContract("4711", 2018, 04, 12);
		//liesVertrag() liefert unterschriebenen Vertrag?
		//Event unterschrieben verschickt
	}

}
