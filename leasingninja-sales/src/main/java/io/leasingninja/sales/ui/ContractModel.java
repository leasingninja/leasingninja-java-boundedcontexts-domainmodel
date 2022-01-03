package io.leasingninja.sales.ui;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContractModel {
	public ContractModel() {
	}

	public ContractModel(String number, String lessee, String car, double price_amount,
			String price_currency) {
		this.setNumber(number);
		this.setLessee(lessee);
		this.setCar(car);
		this.setPrice_amount(price_amount);
		this.setPrice_currency(price_currency);
	}

	@NotNull
	private String number ;

	@NotNull
	private String lessee ;

	@NotNull
	private String car;

	@Min(1)
	private double price_amount=0;

	@NotNull
	private String price_currency;

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

	public double getPrice_amount() {
		return price_amount;
	}

	public void setPrice_amount(double price_amount) {
		this.price_amount = price_amount;
	}

	public String getPrice_currency() {
		return price_currency;
	}

	public void setPrice_currency(String price_currency) {
		this.price_currency = price_currency;
	}
}
