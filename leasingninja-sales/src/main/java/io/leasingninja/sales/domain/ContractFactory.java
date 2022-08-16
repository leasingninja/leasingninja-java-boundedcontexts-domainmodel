package io.leasingninja.sales.domain;

import static java.util.Objects.requireNonNull;
import java.util.Optional;
import org.jmolecules.ddd.annotation.Factory;

@Factory
public class ContractFactory {

	public static Contract restore(ContractNumber number, Customer lessee, Car car, Amount price, Optional<SignDate> optionalSignDate) {
		requireNonNull(number);
		requireNonNull(lessee);
		requireNonNull(car);
		requireNonNull(price);

		var contract = new Contract(number, lessee, car, price);
        optionalSignDate.ifPresent(signDate -> contract.sign(signDate));

		return contract;
	}

}
