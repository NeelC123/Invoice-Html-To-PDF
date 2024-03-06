package com.rt.pot.invoice.model;

public class InvoiceItem {
	private String itemName;
	private String currency;
	private double amount;

	public InvoiceItem(String itemName, String currency, double amount) {
		this.itemName = itemName;
		this.currency = currency;
		this.amount = amount;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
