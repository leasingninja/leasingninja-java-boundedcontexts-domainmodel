package io.leasingninja.riskmanagement.userinterface;

import io.leasingninja.riskmanagement.application.CheckCreditRating;
import io.leasingninja.riskmanagement.application.ListContracts;
import io.leasingninja.riskmanagement.application.ReadContract;
import io.leasingninja.riskmanagement.application.VoteContract;
import io.leasingninja.riskmanagement.domain.ContractNumber;
import io.leasingninja.riskmanagement.domain.CreditRating;
import io.leasingninja.riskmanagement.domain.VoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RiskManagementController {
	
	private final ListContracts listContracts;
	private final ReadContract readContract;
	private final CheckCreditRating checkCreditRating;
	private final VoteContract voteContract;

	public RiskManagementController(ListContracts listContracts, ReadContract readContract, CheckCreditRating checkCreditRating, VoteContract voteContract) {
		this.listContracts = listContracts;
		this.readContract = readContract;
		this.checkCreditRating = checkCreditRating;
		this.voteContract = voteContract;
	}

	@GetMapping("/riskmanagement/contracts")
	public String listContracts(Model model)
	{
		List<ContractModel> contractModels = this.listContracts.all().stream()
				.map(ContractModelMapper::modelFrom)
				.collect(Collectors.toUnmodifiableList());
		System.out.println("Contract models: " + contractModels);
		model.addAttribute(
				"contracts",
				contractModels);
		return "contracts";
	}


	
	@GetMapping("/riskmanagement/contract")
	public String showContract(
			@RequestParam(name="number", required = true) String vertragsnummerString,
			Model model) {
//		if (!Contractnumber.isValid(contractNumber)) {
//		// TODO: 
//		return "Invalid Contract Number: " + contractNumber;
//	}

		var contract = this.readContract.readContract(ContractNumber.of(vertragsnummerString));
		if (contract == null) {
			return "No contract with number " + vertragsnummerString + " in inbox.";
		}
			
		var vertragModel = ContractModelMapper.modelFrom(contract);
		model.addAttribute("contract", vertragModel);
		model.addAttribute("editing_disabled", contract.isVoted());

		return "contracts";
	}


	@GetMapping("/riskmanagement/rating")
	public String showRatings(
			@RequestParam(name="contract_number") String contract_number,
			Model model) {

		System.out.println(" --> /riskmanagement/rating called -> contract_number" + contract_number  );

		var contract = this.readContract.readContract(ContractNumber.of(contract_number));

		var vertragModel = ContractModelMapper.modelFrom(contract);
		model.addAttribute("contract", vertragModel);


		return "rating";
	}

    @PostMapping("/riskmanagement/vote_contract")
    public String checkCreditRating(
            @RequestParam(name="contract_number") String contractNumber,
            @RequestParam(name="vote_result") String voteResult,
            Model model) {
	    try {
			System.out.println(" ||| ----> contractNumber " + contractNumber + " voteResult " + voteResult );
            //CreditRating.valueOf(voteResult);
        } catch(IllegalArgumentException exception) {
            // TODO:
            //return "Invalid credit rating: " + voteResult;
        }

	    try {
			System.out.println("**** CreditRating.valueOf(voteResult)  " + CreditRating.valueOf(voteResult)
					+ "voteResult " + voteResult);
			// TODO --> fix
			this.checkCreditRating.checkCreditRating(
					ContractNumber.of(contractNumber),
					CreditRating.Accepted);
		} catch (Exception ex) {
			System.out.println("ex " + ex );
		}
        return "redirect:/riskmanagement/contracts?number=" + contractNumber;
    }

    @PostMapping("/riskmanagement/vote")
    public String voteContract(
            @RequestParam(name="contract_number") String contractNumber,
            @RequestParam(name="vote_result") String voteResult,
            Model model) {
//		if (!Contractnumber.isValid(contractNumber)) {
//			// TODO:
//			return "Invalid Contract Number: " + contractNumber;
//		}
        try {
            VoteResult.valueOf(voteResult);
        } catch(IllegalArgumentException e) {
            // TODO:
            return "Invalid vote result: " + voteResult;
        }

        this.voteContract.vote(
                ContractNumber.of(contractNumber),
                VoteResult.valueOf(voteResult));
        return "redirect:/riskmanagement/vote?number=" + contractNumber;
    }

}
