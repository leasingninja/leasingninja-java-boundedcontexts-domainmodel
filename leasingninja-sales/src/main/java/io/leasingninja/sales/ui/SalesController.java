package io.leasingninja.sales.ui;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import io.leasingninja.sales.application.FilloutContract;
import io.leasingninja.sales.application.SignContract;
import io.leasingninja.sales.application.ViewContract;
import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Customer;
import io.leasingninja.sales.domain.SignDate;

import javax.validation.Valid;

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

		if (contractNumberString != null) {
			var contract = this.viewContract.with(ContractNumber.of(contractNumberString));
			var contractModel = ContractModelMapper.modelFrom(contract);

			assingValuesToContactModel(contractModel, model);
			System.out.println("SalesController: contractNumber:" + contractModel.getNumber());
			//model.addAttribute("editing_disabled", contract.isSigned()); // TODO use this one
			model.addAttribute("editing_disabled", true);
			System.out.println("SalesController: editing_disabled:" + contract.isSigned());
		} else {
			model.addAttribute("contractModel", new ContractModel());
			model.addAttribute("editing_disabled", false);

		}
		return "contractView.html";
			
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
	public String filloutContract(@Valid ContractModel contractModel,
								  final BindingResult bindingResult,
								  final Model model) {

		assingValuesToContactModel(contractModel, model);
		if (bindingResult.hasErrors()) {
			return "contractView.html";
		}
		this.filloutContract.with(
				ContractNumber.of(contractModel.getNumber()),
				Customer.of(contractModel.getLessee()),
				Car.of(contractModel.getCar()),
				Amount.of(Long.valueOf(contractModel.getPrice_amount()), contractModel.getCar()));
		return "redirect:/sales/view_contract?contractNumber=" + contractModel.getNumber();
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

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	private void assingValuesToContactModel(ContractModel contractModel, Model model) {
		model.addAttribute("editing_disabled", false);
		model.addAttribute("contractModel", contractModel);
		model.addAttribute("number" ,  contractModel.getNumber());
		model.addAttribute("lessee" ,  contractModel.getLessee());
		model.addAttribute("car" ,  contractModel.getCar());
		model.addAttribute("price_amount" ,  contractModel.getPrice_amount());
		model.addAttribute("price_currency" ,  contractModel.getPrice_currency());
	}

}
