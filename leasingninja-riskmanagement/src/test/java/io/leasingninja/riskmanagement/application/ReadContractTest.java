package io.leasingninja.riskmanagement.application;

import static org.assertj.core.api.Assertions.*;
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
import io.leasingninja.riskmanagement.domain.SignDate;

@ExtendWith(MockitoExtension.class)
class ReadContractTest {

	@Mock
	private ContractRepository contractRepositoryMock;

	@InjectMocks
	private ReadContract serviceUnderTest;

	@Test
	void givenAContract_whenRead_thenContractIsRead() {
		// given
		given(contractRepositoryMock.findById(ContractNumber.of("4711")))
				.willReturn(new Contract(
						ContractNumber.of("4711"),
						SignDate.of(2018, 4, 1)));

		// when
		var contract = serviceUnderTest.readContract(ContractNumber.of("4711"));
		
		// then
		assertThat(contract).isEqualToComparingFieldByField(new Contract(
				ContractNumber.of("4711"),
				SignDate.of(2018, 4, 1)));
	}

}
