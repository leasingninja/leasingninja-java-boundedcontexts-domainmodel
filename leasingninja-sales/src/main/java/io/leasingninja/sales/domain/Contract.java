package io.leasingninja.sales.domain;

import static java.util.Objects.requireNonNull;
import java.util.Optional;

import org.jmolecules.ddd.annotation.Entity;
//import org.jmolecules.ddd.annotation.Factory;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Contract extends io.hschwentner.dddbits.basetype.Entity<ContractNumber> {

	private final Customer lessee;
	private final Car car;
	private final Amount price;
	
	private Optional<Amount> installment;

	private Optional<SignDate> signDate;

	//@Factory TODO: extend jMolecules so that @Factory can annotate methods
	public static Contract restore(ContractNumber number, Customer lessee, Car car, Amount price, Optional<SignDate> signDate) {
		requireNonNull(number);
		requireNonNull(lessee);
		requireNonNull(car);
		requireNonNull(price);
		
		var contract = new Contract(number, lessee, car, price);
		contract.signDate = signDate; // TODO: set directly here or replay with sign() ?

		return contract;
	}
	
	public Contract(ContractNumber number, Customer lessee, Car car, Amount price) {
		super(number);
		this.lessee = lessee;
		this.car = car;
		this.price = price;
		this.installment = Optional.empty();
		this.signDate = Optional.empty();
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
		return installment.isPresent();
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

		installment = Optional.of(Amount.of(pmt, price.currency()));

		assert isCalculated();
	}

	public Amount installment() {
		assert isCalculated();
		return installment.get();
	}

	public void sign(SignDate date) {
		assert date != null;
		assert !isSigned();
		
		this.signDate = Optional.of(date);
		
		assert isSigned();
	}

	public boolean isSigned() {
		return this.signDate != null;
	}
	
	public SignDate signDate() {
		assert isSigned();
		return this.signDate.get();
	}
	
	@Override
	public String toString() {
		return "Contract [number=" + number() + ", lessee=" + lessee + ", car=" + car
				+ ", price=" + price + ", signDate=" + signDate + "]";
	}

}
