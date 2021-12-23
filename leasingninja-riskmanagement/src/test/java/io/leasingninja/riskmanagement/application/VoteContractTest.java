package io.leasingninja.riskmanagement.application;

import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.leasingninja.riskmanagement.domain.Contract;
import io.leasingninja.riskmanagement.domain.ContractNumber;
import io.leasingninja.riskmanagement.domain.ContractRepository;
import io.leasingninja.riskmanagement.domain.CreditRating;
import io.leasingninja.riskmanagement.domain.SignDate;
import io.leasingninja.riskmanagement.domain.VoteResult;

@ExtendWith(MockitoExtension.class)
class VoteContractTest {

	@Mock
	private ContractRepository contractRepositoryMock;

	@InjectMocks
	private VoteContract serviceUnderTest;

	@Test
	void givenARatedContract_whenVote_thenContractVoted() {
		// given
		given(contractRepositoryMock.findById(ContractNumber.of("4711")))
				.willReturn(Contract.restore(
						ContractNumber.of("4711"),
						SignDate.of(2018, 4, 1),
						CreditRating.of(3),
						null));

		// when
		serviceUnderTest.vote(ContractNumber.of("4711"), VoteResult.ACCEPTED);

		// then
		then(contractRepositoryMock).should().save(refEq(Contract.restore(
				ContractNumber.of("4711"),
				SignDate.of(2018, 4, 1),
				CreditRating.of(3),
				VoteResult.ACCEPTED)));
	}

}
