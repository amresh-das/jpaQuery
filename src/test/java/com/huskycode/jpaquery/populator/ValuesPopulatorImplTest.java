package com.huskycode.jpaquery.populator;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.huskycode.jpaquery.testmodel.pizza.Customer;

public class ValuesPopulatorImplTest {
	@Test
	public void testValueIsPopulatedForTheGivenAttributeValues() throws SecurityException, NoSuchFieldException {
		ValuesPopulatorImpl populator = ValuesPopulatorImpl.getInstance();
		
		Long expectedValue = 1L;
		Map<Field, Object> overrideValues = new HashMap<Field, Object>();
		overrideValues.put(Customer.class.getDeclaredField("customerId"), expectedValue);

		Customer customer = new Customer();
		populator.populateValue(customer, overrideValues);	
		
		Assertions.assertEquals(expectedValue, customer.getCustomerId());	
	}
}
