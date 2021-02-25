package io.leasingninja.sales.ui;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.leasingninja.sales.application.FilloutContract;
import io.leasingninja.sales.application.SignContract;
import io.leasingninja.sales.application.ViewContract;
import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Customer;
import io.leasingninja.sales.domain.SignDate;

@Controller
public class SalesController {
	
	private final FilloutContract filloutContract;
	private final ViewContract viewContract;
	private final SignContract signContract;

	public SalesController(
			FilloutContract filloutContract,
			ViewContract viewContract,
			SignContract signContract) {
		this.filloutContract = filloutContract;
		this.viewContract = viewContract;
		this.signContract = signContract;
	}

	@GetMapping("/sales/view_contract")
	public String viewContract(
			@RequestParam(name="contractNumber", required = false) String contractNumberString,
			Model model) {
//		var vertrag = 
//				vertragsnummer != null 
//				? this.vertragService.liesVertrag(vertragsnummer) : null;
//		var vertragModel = 
//				vertrag != null 
//						? VertragModelMapper.modelFrom(vertrag)
//						: new VertragModel();
//		model.addAttribute("vertrag", vertragModel);
//		System.out.println("VertriebController: vertragnummer:" + vertragModel.nummer);
//		model.addAttribute("editing_disabled", !vertrag.isUnterschrieben());
//		return "fillout_contract";
		
		System.out.println("SalesController: contractNumber:" + contractNumberString);
		model.addAttribute("contract", new ContractModel());
		model.addAttribute("editing_disabled", false);
		if (contractNumberString != null) {
			var contract = this.viewContract.with(ContractNumber.of(contractNumberString));
			var contractModel = ContractModelMapper.modelFrom(contract);
			model.addAttribute("contract", contractModel);
			System.out.println("SalesController: contractNumber:" + contractModel.number);
			model.addAttribute("editing_disabled", contract.isSigned());
			System.out.println("SalesController: editing_disabled:" + contract.isSigned());
		}
		return "contractView";
			
	}

//	@GetMapping("/sales/view_contract")
//	public String viewContract(
//			@RequestParam(name="vertragsnummer", required = false) String vertragsnummer,
//			Model model) {
//		VertragModel vertragModel = 
//				vertragsnummer != null ? 
//						this.vertragService.liesVertrag(vertragsnummer) :
//						new VertragModel();
//		model.addAttribute("vertrag", vertragModel);
//		System.out.println("VertriebController: vertragnummer:" + vertragModel.nummer);
//		model.addAttribute("editing_disabled", vertragModel.);
//		return "view_contract";
//	}

	@PostMapping("/sales/fillout_contract")
	public String filloutContract(
			@RequestParam(name="contractNumber") String contractNumberString,
			@RequestParam(name="lessee") String lesseeString,
			@RequestParam(name="car") String carString,
			@RequestParam(name="price_amount") int priceAmount,
			@RequestParam(name="price_currency") String priceCurrency,
			Model model) {
		this.filloutContract.with(
				ContractNumber.of(contractNumberString), 
				Customer.of(lesseeString), 
				Car.of(carString),
				Amount.of(priceAmount, priceCurrency));
		return "redirect:/sales/view_contract?contractNumber=" + contractNumberString;
	}

	@PostMapping("/sales/sign_contract")
	public String signContract(
			@RequestParam(name="contractNumber") String contractNumberString,
			Model model) {
//		CheckResult result = ContractNumber.checkValidity(contractNumberString);
//		if(!result.valid) {
//			return 400 result.errors;
//		}
		this.signContract.with(ContractNumber.of(contractNumberString), SignDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue(), LocalDate.now().getDayOfMonth()));
		return "redirect:/sales/view_contract?contractNumber=" + contractNumberString;
	}

}
