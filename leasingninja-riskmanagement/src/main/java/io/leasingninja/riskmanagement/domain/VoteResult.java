package io.leasingninja.riskmanagement.domain;

import io.hschwentner.dddbits.annotation.ValueObject;

@ValueObject
public enum VoteResult {
	ACCEPTED,
	ACCEPTED_WITH_OBLIGATIONS,
	REJECTED
}
