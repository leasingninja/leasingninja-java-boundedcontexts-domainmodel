package io.leasingninja.sales.application;

//import java.beans.
import java.util.concurrent.SubmissionPublisher;

import io.leasingninja.sales.domain.*;

//public class UnterschreibeVertragApplicationService extends Observable implements Flow.Publisher<VertragUnterschrieben>{
public class UnterschreibeVertragApplicationService_MitEvent extends SubmissionPublisher<ContractSigned>{

	public void fuelleVertragsformularAus(String vertragsnummer, String kundenname, String fahrzeug, int preis, String waehrung) {
		new Contract(
				ContractNumber.of(vertragsnummer),
				Customer.of(kundenname),
				Car.of(fahrzeug),
				Amount.of(preis, Currency.valueOf(waehrung)));
	}

	public void unterschreibeVertrag(String vnr, String unterschriftsdatum) {
		// TODO Auto-generated method stub

		submit(new ContractSigned());
	}

}
