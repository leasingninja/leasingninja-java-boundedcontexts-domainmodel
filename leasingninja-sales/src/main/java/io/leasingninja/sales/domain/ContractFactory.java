package io.leasingninja.sales.domain;

import static java.util.Objects.requireNonNull;
import java.util.Optional;
import org.jmolecules.ddd.annotation.Factory;

@Factory
public class ContractFactory {

	public static Contract restoreContract(ContractNumber number, Customer lessee, Car car, Amount price, Optional<SignDate> signDate) {
		requireNonNull(number);
		requireNonNull(lessee);
		requireNonNull(car);
		requireNonNull(price);

		var contract = new Contract(number, lessee, car, price);
        signDate.ifPresent(contract::sign);

		return contract;
	}

}
