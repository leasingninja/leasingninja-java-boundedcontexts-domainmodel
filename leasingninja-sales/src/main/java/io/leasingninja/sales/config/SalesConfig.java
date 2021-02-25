package io.leasingninja.sales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.leasingninja.riskmanagement.application.InboxApplicationService;
import io.leasingninja.sales.application.FilloutContract;
import io.leasingninja.sales.application.SignContract;
import io.leasingninja.sales.application.ViewContract;
import io.leasingninja.sales.domain.Contracts;
import io.leasingninja.sales.infrastructure.ContractDatabaseEntityRepository;
import io.leasingninja.sales.infrastructure.ContractsJpaImpl;

@Configuration
public class SalesConfig {

	// Arbeitshypothese: DI hierüber, höherwertige Spring-Technologien (Repo-Magic, Web-MVC) über Stereotypen
	
	// Infrastructure layer
	@Bean
	public Contracts salesContractRepository(ContractDatabaseEntityRepository repo) {
		return new ContractsJpaImpl(repo);
	}
	
	// Application layer
	@Bean
	public FilloutContract salesFilloutContract(Contracts contracts) {
		return new FilloutContract(contracts);
	}
	
	@Bean
	public SignContract salesSignContract(Contracts contracts, InboxApplicationService riskmanagementInbox) {
		return new SignContract(contracts, riskmanagementInbox);
	}

	@Bean
	public ViewContract salesViewContract(Contracts contracts) {
		return new ViewContract(contracts);
	}
	
}
