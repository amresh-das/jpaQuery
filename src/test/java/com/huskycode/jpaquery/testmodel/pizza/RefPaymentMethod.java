package com.huskycode.jpaquery.testmodel.pizza;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RefPaymentMethod {
	@Id
	private String paymentMethodCode;
	
	private String paymentMethodDescription;	

	public RefPaymentMethod() {
	}

	public RefPaymentMethod(String paymentMethodCode,
			String paymentMethodDescription) {
		this.paymentMethodCode = paymentMethodCode;
		this.paymentMethodDescription = paymentMethodDescription;
	}

	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}

	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}

	public String getPaymentMethodDescription() {
		return paymentMethodDescription;
	}

	public void setPaymentMethodDescription(String paymentMethodDescription) {
		this.paymentMethodDescription = paymentMethodDescription;
	}
}
