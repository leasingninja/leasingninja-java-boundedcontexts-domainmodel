package io.leasingninja.sales.domain;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Contract {

    @Identity
    private final ContractNumber number;

	private final Customer lessee;
	private final Car car;
	private final Amount price;

    // TODO: put these three values into new type Calculation ??
    private Optional<LeaseTerm> leaseTerm;
    private Optional<Interest> interest;
	private Optional<Amount> installment;

	private Optional<SignDate> signDate;

	public Contract(ContractNumber number, Customer lessee, Car car, Amount price) {
        requireNonNull(number);
		requireNonNull(lessee);
		requireNonNull(car);
		requireNonNull(price);

        this.number = number;
		this.lessee = lessee;
		this.car = car;
		this.price = price;
		this.installment = Optional.empty();
		this.signDate = Optional.empty();
	}

	@Identity
	public ContractNumber number() {
        return number;
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
		requireNonNull(leaseTerm);
		requireNonNull(interest);
		assert !isSigned();

        this.leaseTerm = Optional.of(leaseTerm);
        this.interest = Optional.of(interest);

		double inAdvance = 0;
		double residualValue = 0;

		double pmt = FinancialCalculator.pmt(
			leaseTerm.noOfMonths(),
			interest.perMonth(),
			-1 * price.amount(),
			residualValue,
			inAdvance);

		this.installment = Optional.of(Amount.of(pmt, price.currency()));

		assert isCalculated();
	}

    public LeaseTerm leaseTerm() {
    	assert isCalculated();
	    return leaseTerm.get();
    }

    public Interest interest() {
    	assert isCalculated();
	    return interest.get();
    }

	public Amount installment() {
		assert isCalculated();
		return installment.get();
	}

	public void sign(SignDate date) {
		requireNonNull(date);
		assert !isSigned();
        assert isCalculated();

		this.signDate = Optional.of(date);

		assert isSigned();
	}

	public boolean isSigned() {
		return this.signDate.isPresent();
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

	@Override
	public final int hashCode() {
		return number.hashCode();
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		if (number == null) {
			if (other.number() != null)
				return false;
		} else if (!number.equals(other.number()))
			return false;
		return true;
	}

}
