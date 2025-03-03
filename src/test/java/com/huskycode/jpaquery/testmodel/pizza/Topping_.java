package com.huskycode.jpaquery.testmodel.pizza;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Topping.class)
public class Topping_ {
	public static volatile SingularAttribute<Topping, Long> orderId;
	public static volatile SingularAttribute<Topping, Integer> pizzaSequenceNumber;
	public static volatile SingularAttribute<Topping, Integer> toppingSequenceNumber;
	
	public static volatile SingularAttribute<Topping, String> toppingCode;
}
