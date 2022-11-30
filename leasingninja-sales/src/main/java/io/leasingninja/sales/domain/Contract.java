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

	private record Calculation(LeaseTerm leaseTerm, Interest interest, Amount installment) {}
    private Optional<Calculation> calculation;

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
		this.calculation = Optional.empty();
		this.signDate = Optional.empty();
	}

	@Identity
	public ContractNumber number() {
        return this.number;
	}

	public Customer lessee() {
        return this.lessee;
	}

	public Car car() {
        return this.car;
	}

	public Amount price() {
		return this.price;
	}

	public boolean isCalculated() {
		return this.calculation.isPresent();
	}

	public void calculateInstallmentFor(LeaseTerm leaseTerm, Interest interest) {
		requireNonNull(leaseTerm);
		requireNonNull(interest);
		assert !isSigned();

		double inAdvance = 0;
		double residualValue = 0;

		double pmt = FinancialCalculator.pmt(
			leaseTerm.noOfMonths(),
			interest.perMonth(),
			-1 * price().amount(),
			residualValue,
			inAdvance);

		this.calculation = Optional.of(new Calculation(leaseTerm, interest, Amount.of(pmt, price.currency())));

		assert isCalculated();
	}

    public LeaseTerm leaseTerm() {
    	assert isCalculated();
	    return this.calculation.get().leaseTerm();
    }

    public Interest interest() {
    	assert isCalculated();
	    return this.calculation.get().interest();
    }

	public Amount installment() {
		assert isCalculated();
		return this.calculation.get().installment();
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
		return "Contract [number=" + number() + ", lessee=" + this.lessee + ", car=" + this.car
				+ ", price=" + this.price + ", signDate=" + this.signDate + "]";
	}

	@Override
	public final int hashCode() {
		return this.number.hashCode();
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
		if (this.number == null) {
			if (other.number() != null)
				return false;
		} else if (!this.number.equals(other.number()))
			return false;
		return true;
	}

}
