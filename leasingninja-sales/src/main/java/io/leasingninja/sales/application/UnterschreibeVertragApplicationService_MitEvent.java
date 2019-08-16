package io.leasingninja.sales.application;

import java.util.Observable;
import java.util.concurrent.Flow;
//import java.beans.
import java.util.concurrent.SubmissionPublisher;

import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Customer;

//public class UnterschreibeVertragApplicationService extends Observable implements Flow.Publisher<VertragUnterschrieben>{
public class UnterschreibeVertragApplicationService_MitEvent extends SubmissionPublisher<ContractSigned>{

	public void fuelleVertragsformularAus(String vertragsnummer, String kundenname, String fahrzeug, int preis, String waehrung) {
		new Contract(
				ContractNumber.of(vertragsnummer), 
				Customer.of(kundenname), 
				Car.of(fahrzeug),
				Amount.of(preis, waehrung));
		
	}
	
	public void unterschreibeVertrag(String vnr, String unterschriftsdatum) {
		// TODO Auto-generated method stub
		
		submit(new ContractSigned());
	}

}
