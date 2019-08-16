package io.leasingninja.riskmanagement.domain;

import io.hschwentner.dddbits.annotation.DomainEntity;
import io.hschwentner.dddbits.annotation.Factory;
import io.hschwentner.dddbits.basetype.Entity;

@DomainEntity
public class Contract extends Entity<ContractNumber> {

	private CreditRating creditRating;
	private VoteResult voteResult;

	public Contract(ContractNumber number, SignDate signDate) { // TODO: do we need the signDate?
		super(number);
		assert signDate != null;
	}

	@Factory
	public static Contract restore(ContractNumber nr, SignDate signDate, CreditRating rating, VoteResult voteResult) {
		assert nr != null;
		assert signDate != null;
//		assert voteResult == null || rating != null    // VoteResult != null => rating != null

		var restoredContract = new Contract(nr, signDate);
		restoredContract.creditRating = rating;
		restoredContract.voteResult = voteResult;
		return restoredContract;
	}
	
	public void checkCreditRating(CreditRating creditRating) {
		assert creditRating != null;
		assert !isVoted();
		
		this.creditRating = creditRating;
		
		assert isRated();
	}

	public boolean isRated() {
		return creditRating != null;
	}

	public CreditRating rating() {
		assert isRated();
		
		return creditRating;
	}
	
	public void vote(VoteResult result) {
		assert result != null;
		assert isRated(); // TODO: Decide DbC-Mechanism
		
		this.voteResult = result;
		
		assert isVoted();
	}

	public boolean isVoted() {
		return voteResult != null;
	}

	public VoteResult votingResult() {
		assert isVoted();
		
		return this.voteResult;
	}

}
