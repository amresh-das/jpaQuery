package com.huskycode.jpaquery.testmodel.pizza;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ToppingKey implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	@Column private Long orderId;
	@Column private Integer pizzaSequenceNumber;
	@Column private Integer toppingSequenceNumber;
}
