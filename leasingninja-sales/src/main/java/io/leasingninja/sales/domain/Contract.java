package io.leasingninja.sales.domain;

import java.util.Objects;

import io.hschwentner.dddbits.annotation.DomainEntity;
import io.hschwentner.dddbits.annotation.Factory;
import io.hschwentner.dddbits.basetype.Entity;

@DomainEntity
public class Contract extends Entity<ContractNumber> {

	private final Customer lessee;
	private final Car car;
	private final Amount price;
	private SignDate signDate;

	@Factory
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
