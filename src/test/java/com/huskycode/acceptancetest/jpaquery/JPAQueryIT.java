package com.huskycode.acceptancetest.jpaquery;

import static com.huskycode.jpaquery.command.CommandNodeFactory.n;
import static com.huskycode.jpaquery.command.CommandNodesFactory.ns;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Field;
import java.util.List;

import com.huskycode.integration.TestEntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.huskycode.jpaquery.JPAQueryContext;
import com.huskycode.jpaquery.command.CommandNodes;
import com.huskycode.jpaquery.testmodel.pizza.Address;
import com.huskycode.jpaquery.testmodel.pizza.Address_;
import com.huskycode.jpaquery.testmodel.pizza.Customer;
import com.huskycode.jpaquery.testmodel.pizza.Employee;
import com.huskycode.jpaquery.testmodel.pizza.PizzaOrder;
import com.huskycode.jpaquery.testmodel.pizza.PizzaOrdered;
import com.huskycode.jpaquery.testmodel.pizza.Topping;
import com.huskycode.jpaquery.testmodel.pizza.deps.PizzaDeps;
import com.huskycode.jpaquery.types.tree.PersistedResult;
import com.huskycode.jpaquery.util.Maps;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

/**
 * @author Varokas Panusuwan
 */
@Transactional
public class JPAQueryIT {
    private PizzaDeps pizzaDeps;
    private JPAQueryContext context;

    private EntityManager entityManager;
    private EntityTransaction tx;

    @Before
    public void setUp() throws Exception {
        entityManager = TestEntityManager.INSTANCE.getEntityManager();
        tx = entityManager.getTransaction();
        tx.begin();
    }

    @After
    public void tearDown() throws Exception {
        tx.rollback();
    }

    @Test
    public void testCreateClassWithNoDeps() {
    	pizzaDeps = new PizzaDeps();
    	context = JPAQueryContext.newInstance(entityManager, pizzaDeps.getDeps());
		
        PersistedResult result = context.create(Address.class);
        
        assertThat(result.getPersistedObjects().size(), is(1));
        Object persistedObj = result.getPersistedObjects().get(0);
        
        assertThat(persistedObj, is(instanceOf(Address.class)));
        assertThat(((Address)persistedObj).getAddressId(), is(not(nullValue())));
    }
	
	@Test 
    public void testCreateClassWithDeps() {     
    	pizzaDeps = new PizzaDeps();
    	context = JPAQueryContext.newInstance(entityManager, pizzaDeps.getDeps());
		
        PersistedResult result = context.create(PizzaOrder.class);
        
        Address address = result.getForClass(Address.class).get(0);
        Employee employee = result.getForClass(Employee.class).get(0);
        Customer customer = result.getForClass(Customer.class).get(0);
        PizzaOrder pizzaOrder = result.getForClass(PizzaOrder.class).get(0);
        
        assertThat(employee.getEmployeeAddressId(), is(address.getAddressId()));
        assertThat(customer.getCustomerAddressId(), is(address.getAddressId()));
        assertThat(pizzaOrder.getCustomerId(), is(customer.getCustomerId()));
        assertThat(pizzaOrder.getDeliveredByEmployeeId(), is(employee.getEmployeeId()));
        assertThat(pizzaOrder.getTakenByEmployeeId(), is(employee.getEmployeeId()));
    }
	
	@Test 
    public void testCreateFromDependencyDefinition() {
		pizzaDeps = new PizzaDeps();
		context = JPAQueryContext.newInstance(entityManager, pizzaDeps.getDeps());
		
		PersistedResult result = context.createFromDependencyDefinition();
		 
		Address address = result.getForClass(Address.class).get(0);
        Employee employee = result.getForClass(Employee.class).get(0);
        Customer customer = result.getForClass(Customer.class).get(0);
        PizzaOrder pizzaOrder = result.getForClass(PizzaOrder.class).get(0);
        
        assertThat(employee.getEmployeeAddressId(), is(address.getAddressId()));
        assertThat(customer.getCustomerAddressId(), is(address.getAddressId()));
        assertThat(pizzaOrder.getCustomerId(), is(customer.getCustomerId()));
        assertThat(pizzaOrder.getDeliveredByEmployeeId(), is(employee.getEmployeeId()));
        assertThat(pizzaOrder.getTakenByEmployeeId(), is(employee.getEmployeeId()));
	}
	
	@Test 
    public void testCreateFromCommands() {
		pizzaDeps = new PizzaDeps();
		context = JPAQueryContext.newInstance(entityManager, pizzaDeps.getDeps());
		
		CommandNodes commands = ns(n(Address.class,
									n(PizzaOrder.class),
									n(PizzaOrder.class)));
		PersistedResult result = context.create(commands);
		 
		Address address = result.getForClass(Address.class).get(0);
        Employee employee = result.getForClass(Employee.class).get(0);
        Customer customer = result.getForClass(Customer.class).get(0);
        List<PizzaOrder> pizzaOrder = result.getForClass(PizzaOrder.class);
        
        assertThat(employee.getEmployeeAddressId(), is(address.getAddressId()));
        assertThat(customer.getCustomerAddressId(), is(address.getAddressId()));
        assertThat(pizzaOrder.get(0).getCustomerId(), is(customer.getCustomerId()));
        assertThat(pizzaOrder.get(0).getDeliveredByEmployeeId(), is(employee.getEmployeeId()));
        assertThat(pizzaOrder.get(0).getTakenByEmployeeId(), is(employee.getEmployeeId()));
        assertThat(pizzaOrder.get(1).getCustomerId(), is(customer.getCustomerId()));
        assertThat(pizzaOrder.get(1).getDeliveredByEmployeeId(), is(employee.getEmployeeId()));
        assertThat(pizzaOrder.get(1).getTakenByEmployeeId(), is(employee.getEmployeeId()));
	}
	
	@Test 
    public void testCreateWithSpecifiedValue() {
		pizzaDeps = new PizzaDeps();
		context = JPAQueryContext.newInstance(entityManager, pizzaDeps.getDeps());
		
		String expectedCityName = "Seattle";

		CommandNodes commands = ns(n(Address.class)
									.withValues(Maps.of((Field)Address_.city.getJavaMember(), expectedCityName))
									.with(n(PizzaOrder.class)));
		PersistedResult result = context.create(commands);
		assertThat(result.getForClass(Address.class).get(0).getCity(), is(expectedCityName));
	}
	
	@Test 
    public void testMultipleLinkBetweenTwoClasses() {
		pizzaDeps = new PizzaDeps();
		context = JPAQueryContext.newInstance(entityManager, pizzaDeps.getDeps());
		
		CommandNodes commands = ns(n(Topping.class));
		PersistedResult result = context.create(commands);
		
		PizzaOrdered resultPizzaOrdered = result.getForClass(PizzaOrdered.class).get(0);
		Topping resultTopping = result.getForClass(Topping.class).get(0);
		
		assertThat(resultTopping.getOrderId(), is(resultPizzaOrdered.getOrderId()));
		assertThat(resultTopping.getPizzaSequenceNumber(), is(resultPizzaOrdered.getPizzaSequenceNumber()));
	}
}
