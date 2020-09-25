package io.leasingninja.riskmanagement.userinterface;

import static org.assertj.core.api.Assertions.*;

import io.leasingninja.riskmanagement.domain.*;
import org.junit.jupiter.api.Test;

public class ContractModelMapperTest {

    @Test
    void givenAnUnratedContract_whenMapped_thenUnvotedModel() {
        // Given
        var contract = new Contract(ContractNumber.of("12345"), SignDate.of(2019, 3, 5));

        // When
        var model = ContractModelMapper.modelFrom(contract);

        // Then
        assertThat(model.number).isEqualTo("12345");
        assertThat(model.creditRating).isEqualTo("<not rated yet>");
        assertThat(model.voteResult).isEqualTo("<not voted yet>");
    }

    @Test
    void givenARatedButUnvotedContract_whenMapped_thenUnvotedModel() {
        // Given
        var contract = new Contract(ContractNumber.of("12345"), SignDate.of(2019, 3, 5));
        contract.checkCreditRating(CreditRating.GOOD);

        // When
        var model = ContractModelMapper.modelFrom(contract);

        // Then
        assertThat(model.number).isEqualTo("12345");
        assertThat(model.creditRating).isEqualTo("GOOD");
        assertThat(model.voteResult).isEqualTo("<not voted yet>");
    }

    @Test
    void givenAVotedContract_whenMapped_thenUnvotedModel() {
        // Given
        var contract = new Contract(ContractNumber.of("12345"), SignDate.of(2019, 3, 5));
        contract.checkCreditRating(CreditRating.GOOD);
        contract.vote(VoteResult.ACCEPTED);

        // When
        var model = ContractModelMapper.modelFrom(contract);

        // Then
        assertThat(model.number).isEqualTo("12345");
        assertThat(model.creditRating).isEqualTo("GOOD");
        assertThat(model.voteResult).isEqualTo("ACCEPTED");
    }

}
