package io.leasingninja.riskmanagement.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public enum VoteResult {
	ACCEPTED,
	ACCEPTED_WITH_OBLIGATIONS,
	REJECTED
}
