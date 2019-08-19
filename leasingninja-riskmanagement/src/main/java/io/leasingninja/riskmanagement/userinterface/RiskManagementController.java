package io.leasingninja.riskmanagement.userinterface;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.leasingninja.riskmanagement.application.CheckCreditRating;
import io.leasingninja.riskmanagement.application.ListContracts;
import io.leasingninja.riskmanagement.application.ReadContract;
import io.leasingninja.riskmanagement.application.VoteContract;
import io.leasingninja.riskmanagement.domain.ContractNumber;
import io.leasingninja.riskmanagement.domain.VoteResult;

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

		return "contract";
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
		return "redirect:/riskmanagement/contract?number=" + contractNumber;
	}

}
