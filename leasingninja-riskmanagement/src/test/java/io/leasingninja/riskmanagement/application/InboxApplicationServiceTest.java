package io.leasingninja.riskmanagement.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.then;

import io.leasingninja.riskmanagement.domain.Contract;
import io.leasingninja.riskmanagement.domain.ContractNumber;
import io.leasingninja.riskmanagement.domain.ContractRepository;
import io.leasingninja.riskmanagement.domain.SignDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InboxApplicationServiceTest {

	@Mock
	private ContractRepository contracts;

	@InjectMocks
	private InboxApplicationServiceImpl inboxUnderTest;

	@Test
	void givenAnEmptyInbox_whenConfirmSignedContract_thenContractIsSaved() {
		// Given

		// When
		inboxUnderTest.confirmSignedContract("4711", 2019, 12, 24);

		// Then
		then(contracts).should().save(refEq(new Contract(
				ContractNumber.of("4711"),
				SignDate.of(2019, 12, 24))));
	}

}
