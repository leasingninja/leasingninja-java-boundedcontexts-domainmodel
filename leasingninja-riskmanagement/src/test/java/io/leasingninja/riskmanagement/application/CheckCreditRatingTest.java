package io.leasingninja.riskmanagement.application;

import static org.assertj.core.api.Assertions.*;
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
class CheckCreditRatingTest {

	@Mock
	private ContractRepository contractRepositoryMock;

	@InjectMocks
	private CheckCreditRating serviceUnderTest;

	@Test
	void testDomainLayerIntegrationTest() {
		// given
		var contract = new Contract(ContractNumber.of("4711"), SignDate.of(2018, 4, 1));

		// when
		contract.checkCreditRating(CreditRating.of(3));
//		contract.calculateResaleValue(); //TODO: wollen wir das?
		contract.vote(VoteResult.ACCEPTED);

		// then
		assertThat(contract.isRated()).isTrue();
		assertThat(contract.rating()).isEqualTo(CreditRating.of(3));
		assertThat(contract.isVoted()).isTrue();
	}

//	@Test
//	void test() {
//		// given
//		ein unterschriebener Vertrag
//		
//		// when
//		checkCreditRating()
//		calculateResaleValue()
//		voteContract();
//		votiere
//		
//		// then
//		Vertrag hat Bonität und Zustand isVoted
//	}

	@Test
	void givenAContract_whenCheckCreditRating_thenContractRated() {
		// given
		given(contractRepositoryMock.findById(ContractNumber.of("4711")))
				.willReturn(new Contract(ContractNumber.of("4711"), SignDate.of(2018, 4, 1)));

		// when
		serviceUnderTest.checkCreditRating(ContractNumber.of("4711"), CreditRating.of(3));

		// then
		then(contractRepositoryMock).should().save(refEq(Contract.restore(
				ContractNumber.of("4711"),
				SignDate.of(2018, 4, 1),
				CreditRating.of(3),
				null)));
//		Contract contract = serviceUnderTest.readContract(Contractnumber.of("4711")); // TODO: or showContract
//		assertThat(contract.isRated()).isTrue();
	}

}
