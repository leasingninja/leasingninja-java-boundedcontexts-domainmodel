package io.leasingninja.sales.application;

import io.hschwentner.dddbits.annotation.ApplicationService;
import io.leasingninja.riskmanagement.application.InboxApplicationService;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;
import io.leasingninja.sales.domain.SignDate;

@ApplicationService
public class SignContract {

	private final Contracts contracts;
	private final InboxApplicationService riskmanagementInbox;

	public SignContract(Contracts contracts, InboxApplicationService riskmanagementInbox) {
		this.contracts = contracts;
		this.riskmanagementInbox = riskmanagementInbox;
	}
	
	public void with(ContractNumber number, SignDate signDate) {
		var contract = this.contracts.with(number);
		
		contract.sign(signDate);
		
		this.contracts.save(contract);
		
		riskmanagementInbox.confirmSignedContract(number.value(), signDate.year(), signDate.month(), signDate.day());
	}

}
