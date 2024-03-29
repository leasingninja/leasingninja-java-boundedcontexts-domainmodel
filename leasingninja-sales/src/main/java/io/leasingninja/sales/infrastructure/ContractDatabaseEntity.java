package io.leasingninja.sales.infrastructure;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractFactory;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Currency;
import io.leasingninja.sales.domain.Customer;
import io.leasingninja.sales.domain.Interest;
import io.leasingninja.sales.domain.LeaseTerm;
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

    private int leaseTermInMonths;

    private double interestPerYear;

	private LocalDate signDate;

	private ContractDatabaseEntity() {
	}

	public static ContractDatabaseEntity from(Contract contract) {
		var dbEntity = new ContractDatabaseEntity();
		dbEntity.number = contract.number().number();
		dbEntity.lessee = contract.lessee().customer();
		dbEntity.car = contract.car().car();
		dbEntity.priceAmount = contract.price().amountInCents();
		dbEntity.priceCurrency = contract.price().currency().name();
        if(contract.isCalculated()) {
            dbEntity.leaseTermInMonths = contract.leaseTerm().noOfMonths();
            dbEntity.interestPerYear = contract.interest().perYear();
        }
        if(contract.isSigned()) {
			dbEntity.signDate = contract.signDate().date();
		}
		return dbEntity;
	}

	public Contract toContract() {

		return ContractFactory.restoreContract(
                // TODO: check that priceCurrency is a real Currency
				ContractNumber.of(number),
				Customer.of(lessee),
				Car.of(car),
				Amount.of(priceAmount, Currency.valueOf(priceCurrency)),
				leaseTermInMonths != 0
                    ? Optional.of(LeaseTerm.ofMonths(leaseTermInMonths))
                    : Optional.empty(),
                interestPerYear != 0
                    ? Optional.of(Interest.of(interestPerYear))
                    : Optional.empty(),
                signDate != null
					? Optional.of(SignDate.of(signDate))
					: Optional.empty());
	}

}
