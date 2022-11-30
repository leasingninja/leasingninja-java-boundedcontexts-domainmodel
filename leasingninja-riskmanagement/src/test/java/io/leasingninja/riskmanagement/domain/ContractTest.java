package io.leasingninja.riskmanagement.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContractTest {

	@Test
	void givenASignedContract_whenCheckCreditRating_ThenRated() {
		// given
		var contract = new Contract(ContractNumber.of("4711"), SignDate.of(2018, 4, 1));

		// when
		contract.checkCreditRating(CreditRating.of(3));

		// then
		assertThat(contract.isRated()).isTrue();
		assertThat(contract.rating()).isEqualTo(CreditRating.of(3));
	}

	@Test
	void givenARatedContract_whenVote_ThenVoted() {
		// given
		var contract = new Contract(ContractNumber.of("4711"), SignDate.of(2018, 4, 1));
		contract.checkCreditRating(CreditRating.of(3));

		// when
		contract.vote(VoteResult.ACCEPTED);

		// then
		assertThat(contract.isVoted()).isTrue();
	}

	@Test
	void restoreContract() {
		// given

		// when
		var contract = Contract.restore(
				ContractNumber.of("4711"),
				SignDate.of(2018, 4, 1),
				CreditRating.of(3),
				VoteResult.ACCEPTED_WITH_OBLIGATIONS);

		// then
		assertThat(contract.identity()).isEqualTo(ContractNumber.of("4711"));
//		assertThat(contract.signDate()).isEqualTo(SignDate.of(2018, 4, 1));
		assertThat(contract.rating()).isEqualTo(CreditRating.of(3));
		assertThat(contract.isVoted()).isTrue();
	}

}
