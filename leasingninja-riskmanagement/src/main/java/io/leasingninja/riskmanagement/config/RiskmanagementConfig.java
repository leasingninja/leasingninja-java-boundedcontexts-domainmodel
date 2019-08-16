package io.leasingninja.riskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.leasingninja.riskmanagement.domain.ContractRepository;
import io.leasingninja.riskmanagement.infrastructure.ContractRepositoryMemImpl;


@Configuration
public class RiskmanagementConfig {

	@Bean
	public ContractRepository riskmanagementContractRepository() {
		return new ContractRepositoryMemImpl();
	}
	
}
