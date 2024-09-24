package com.huskycode.jpaquery.link;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 * User: Ta
 * Date: 3/26/12
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class AttributeImplTest {

    @Test
    public void testCreate() throws NoSuchFieldException {
        Class<TestClass> c = TestClass.class;
        Field field = c.getDeclaredField("a");

        Attribute<TestClass, Integer> attr = AttributeImpl.newInstance(c, field);

        Assertions.assertEquals(c, attr.getEntityClass());
        Assertions.assertEquals(c.getDeclaredField("a"), attr.getField());
    }

    @Test
    public void testEqual() throws NoSuchFieldException {
        Attribute<TestClass, Integer> attr1 = createAttribute("a");
        Attribute<TestClass, Integer> attr2 = createAttribute("a");

        Assertions.assertEquals(attr1, attr2);

        Class<TestClass> c = TestClass.class;
        Attribute<TestClass, Integer> attr3  = AttributeImpl.newInstance(c, c.getDeclaredField("b"));

        Assertions.assertFalse(attr1.equals(attr3));

        Class<TestClass2> c2 = TestClass2.class;
        Attribute<TestClass2, Integer> attr4  = AttributeImpl.newInstance(c2, c2.getDeclaredField("a"));

        Assertions.assertFalse(attr1.equals(attr4));
    }

    private  Attribute<TestClass, Integer> createAttribute(String fieldName) throws NoSuchFieldException {
        Class<TestClass> c = TestClass.class;
        Field field = c.getDeclaredField(fieldName);
        return AttributeImpl.newInstance(c, field);
    }


    private class TestClass {
        private int a;
        private int b;
    }

    private class TestClass2 {
        private int a;
        private int b;
    }

}
