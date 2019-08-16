package io.leasingninja.sales.application;

import io.hschwentner.dddbits.annotation.ApplicationService;
import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;
import io.leasingninja.sales.domain.Customer;

@ApplicationService
public class FilloutContract {

	private final Contracts contracts;

	public FilloutContract(Contracts contracts) {
		this.contracts = contracts;
	}
	
	public void with(ContractNumber number, Customer customer, Car car, Amount amount) {
		contracts.save(new Contract(
				number, 
				customer, 
				car,
				amount));
	}

}
