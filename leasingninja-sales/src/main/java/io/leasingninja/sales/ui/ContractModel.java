package io.leasingninja.sales.ui;

public class ContractModel {
	public ContractModel() {
	}

	public ContractModel(String number, String lessee, String car, double price_amount,
			String price_currency) {
		this.number = number;
		this.lessee = lessee;
		this.car = car;
		this.price_amount = price_amount;
		this.price_currency = price_currency;
	}
	
	public String number = "";
	public String lessee = "";
	public String car = "";
	public double price_amount;
	public String price_currency = "";
}
