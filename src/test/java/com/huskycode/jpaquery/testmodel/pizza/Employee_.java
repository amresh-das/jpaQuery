package com.huskycode.jpaquery.testmodel.pizza;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employee.class)
public class Employee_ {
	public static volatile SingularAttribute<Employee, Long> employeeId;
	public static volatile SingularAttribute<Employee, Long> employeeAddressId;
	
	public static volatile SingularAttribute<Employee, String> employeeName;
	public static volatile SingularAttribute<Employee, String> employeePhone;
	public static volatile SingularAttribute<Employee, String> otherEmployeeDetails;
}
