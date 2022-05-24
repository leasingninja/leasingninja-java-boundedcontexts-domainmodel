package io.leasingninja.riskmanagement.userinterface;

import static org.assertj.core.api.Assertions.*;

import io.leasingninja.riskmanagement.domain.*;
import org.junit.jupiter.api.Test;

class ContractModelMapperTest {

    @Test
    void givenAnUnratedContract_whenMapped_thenUnvotedModel() {
        // Given
        var contract = new Contract(ContractNumber.of("12345"), SignDate.of(2019, 3, 5));

        // When
        var model = ContractModelMapper.modelFrom(contract);

        // Then
        assertThat(model.number).isEqualTo("12345");
        assertThat(model.creditRating).isEqualTo(null);
        assertThat(model.voteResult).isEqualTo("<not voted yet>");
    }

    @Test
    void givenARatedButUnvotedContract_whenMapped_thenUnvotedModel() {
        // Given
        var contract = new Contract(ContractNumber.of("12345"), SignDate.of(2019, 3, 5));
        contract.checkCreditRating(CreditRating.of(3));

        // When
        var model = ContractModelMapper.modelFrom(contract);

        // Then
        assertThat(model.number).isEqualTo("12345");
        assertThat(model.creditRating).isEqualTo(3);
        assertThat(model.voteResult).isEqualTo("<not voted yet>");
    }

    @Test
    void givenAVotedContract_whenMapped_thenUnvotedModel() {
        // Given
        var contract = new Contract(ContractNumber.of("12345"), SignDate.of(2019, 3, 5));
        contract.checkCreditRating(CreditRating.of(3));
        contract.vote(VoteResult.ACCEPTED);

        // When
        var model = ContractModelMapper.modelFrom(contract);

        // Then
        assertThat(model.number).isEqualTo("12345");
        assertThat(model.creditRating).isEqualTo(3);
        assertThat(model.voteResult).isEqualTo("ACCEPTED");
    }

}
