package io.leasingninja.sales.infrastructure;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractFactory;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Customer;
import io.leasingninja.sales.domain.SignDate;

@Entity
@Table(name = "contracts")
public class ContractDatabaseEntity {

	@Id
	private String number;

	private String lessee;

	private String car;

	@Column(name = "price_amount")
	private long priceAmount;

	@Column(name = "price_currency")
	private String priceCurrency;

	private LocalDate signDate;

	private ContractDatabaseEntity() {
	}

	public static ContractDatabaseEntity from(Contract contract) {
		var dbEntity = new ContractDatabaseEntity();
		dbEntity.number = contract.number().value();
		dbEntity.lessee = contract.lessee().value();
		dbEntity.car = contract.car().value();
		dbEntity.priceAmount = contract.price().amountInCents();
		dbEntity.priceCurrency = contract.price().currency();
		if(contract.isSigned()) {
			dbEntity.signDate = contract.signDate().value();
		}
		return dbEntity;
	}

	public Contract toContract() {

		return ContractFactory.restore(
				ContractNumber.of(number),
				Customer.of(lessee),
				Car.of(car),
				Amount.of(priceAmount, priceCurrency),
				signDate != null
					? Optional.of(SignDate.of(signDate))
					: Optional.empty());
	}

}
