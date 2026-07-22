package io.leasingninja.riskmanagement.domain;

import static java.util.Objects.requireNonNull;

import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Contract extends io.hschwentner.dddbits.basetype.Entity<ContractNumber>  {

    @Identity
    private final ContractNumber number;

	private CreditRating creditRating;
	private VoteResult voteResult;

	public Contract(ContractNumber number, SignDate signDate) { // TODO: do we need the signDate?
		super(number);
        requireNonNull(number);
		requireNonNull(signDate);

        this.number = number;
	}

	//TODO: @Factory
	public static Contract restore(ContractNumber nr, SignDate signDate, CreditRating rating, VoteResult voteResult) {
		requireNonNull(nr);
		requireNonNull(signDate);
//		assert voteResult == null || rating != null    // VoteResult != null => rating != null

		var restoredContract = new Contract(nr, signDate);
		restoredContract.creditRating = rating;
		restoredContract.voteResult = voteResult;
		return restoredContract;
	}

    @Identity
    public ContractNumber number() {
        return this.number;
    }

	public void checkCreditRating(CreditRating creditRating) {
		requireNonNull(creditRating);
		assert !isVoted();

		this.creditRating = creditRating;

		assert isRated();
	}

	public boolean isRated() {
		return creditRating != null;
	}

	public CreditRating rating() {
		assert isRated() : "Precondition violated: isRated()";

		return creditRating;
	}

	public void vote(VoteResult result) {
		requireNonNull(result);
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

    // TODO: equals() and hashCode()

}
