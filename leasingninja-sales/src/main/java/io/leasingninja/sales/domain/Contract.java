package io.leasingninja.sales.domain;

import java.util.Objects;

import org.jmolecules.ddd.annotation.Entity;
//import org.jmolecules.ddd.annotation.Factory;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Contract extends io.hschwentner.dddbits.basetype.Entity<ContractNumber> {

	private final Customer lessee;
	private final Car car;
	private final Amount price;
	
	private Amount installment;

	private SignDate signDate;

	//@Factory TODO: extend jMolecules so that @Factory can annotate methods
	public static Contract restore(ContractNumber number, Customer lessee, Car car, Amount price, SignDate signDate) {
		assert number != null;
		assert lessee != null;
		assert car != null;
		assert price != null;
		Objects.requireNonNull(number);
//		assert signDate != null;
		
		var contract = new Contract(number, lessee, car, price);
		contract.signDate = signDate; // TODO: set directly here or replay with sign() ?

		return contract;
	}
	
	public Contract(ContractNumber number, Customer lessee, Car car, Amount price) {
		super(number);
		this.lessee = lessee;
		this.car = car;
		this.price = price;
	}

	@Identity
	public ContractNumber number() {
		return identity();
	}
	
	public Customer lessee() {
		return lessee;
	}
	
	public Car car() {
		return car;
	}
	
	public Amount price() {
		return price;
	}

	public boolean isCalculated() {
		return installment != null;
	}

	public void calculateInstallmentFor(LeaseTerm leaseTerm, Interest interest) {
		assert !isSigned();

		double inAdvance = 0;
		double residualValue = 0;

		double pmt = FinancialCalculator.pmt(
			leaseTerm.noOfMonths(),
			interest.perMonth(),
			-1 * price.amount(),
			residualValue,
			inAdvance);

		installment =  Amount.of(pmt, price.currency());

		assert isCalculated();
	}

	public Amount installment() {
		assert isCalculated();
		return installment;
	}

	public void sign(SignDate date) {
		assert date != null;
		assert !isSigned();
		
		this.signDate = date;
		
		assert isSigned();
	}

	public boolean isSigned() {
		return this.signDate != null;
	}
	
	public SignDate signDate() {
		assert isSigned();
		return this.signDate;
	}
	
	@Override
	public String toString() {
		return "Contract [number=" + number() + ", lessee=" + lessee + ", car=" + car
				+ ", price=" + price + ", signDate=" + signDate + "]";
	}

}
