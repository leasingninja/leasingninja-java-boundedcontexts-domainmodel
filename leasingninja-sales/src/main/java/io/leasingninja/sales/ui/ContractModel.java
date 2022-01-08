package io.leasingninja.sales.ui;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ContractModel {

	@NotEmpty( message = "Number field can not be empty")
	@NotNull( message = "Number name can not be null")
	private String number ;

	@NotEmpty( message = "Lessee name can not be empty")
	@NotNull( message = "Lessee name can not be null")
	private String lessee ;

	@NotEmpty( message = "Car name can not be empty")
	@NotNull(  message = "Car name can not be null")
	private String car;

	@NotEmpty(message = "Price Amount  can not be empty")
	@NotNull(message = "Price Amount  can not be null")
	private String price_amount;

	@NotEmpty(message = "Price Amount can not be empty")
	@NotNull(message = "Price Amount  can not be null")
	private String price_currency;

	public ContractModel() {
	}

	public ContractModel( String number,  String lessee,  String car,  String price_amount,
						  String price_currency) {
		this.setNumber(number);
		this.setLessee(lessee);
		this.setCar(car);
		this.setPrice_amount(price_amount);
		this.setPrice_currency(price_currency);
	}



	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLessee() {
		return lessee;
	}

	public void setLessee(String lessee) {
		this.lessee = lessee;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getPrice_amount() {
		return price_amount;
	}

	public void setPrice_amount(String price_amount) {
		this.price_amount = price_amount;
	}

	public String getPrice_currency() {
		return price_currency;
	}

	public void setPrice_currency(String price_currency) {
		this.price_currency = price_currency;
	}
}
