package io.leasingninja.sales.domain;

import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public final record ContractSigned(ContractNumber contract, SignDate signDate) {

}
