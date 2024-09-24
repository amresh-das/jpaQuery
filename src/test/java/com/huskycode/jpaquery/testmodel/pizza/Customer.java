package com.huskycode.jpaquery.testmodel.pizza;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * Model taken from the design here
 * 
 * http://www.databaseanswers.org/data_models/pizza_deliveries/index.htm
 * 
 * @author Varokas Panusuwan
 *
 */
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	
	private long customerAddressId;
	private String paymentMethodCode;
	private String customerName;
	private String customerPhone;
	private Date dateOfFirstOrder;
	private String otherCustomerDetails;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public long getCustomerAddressId() {
		return customerAddressId;
	}
	public void setCustomerAddressId(long customerAddressId) {
		this.customerAddressId = customerAddressId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Date getDateOfFirstOrder() {
		return dateOfFirstOrder;
	}
	public void setDateOfFirstOrder(Date dateOfFirstOrder) {
		this.dateOfFirstOrder = dateOfFirstOrder;
	}
	public String getOtherCustomerDetails() {
		return otherCustomerDetails;
	}
	public void setOtherCustomerDetails(String otherCustomerDetails) {
		this.otherCustomerDetails = otherCustomerDetails;
	}
	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}
	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}
}
