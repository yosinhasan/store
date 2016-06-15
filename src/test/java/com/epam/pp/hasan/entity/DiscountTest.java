package com.epam.pp.hasan.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Yosin_Hasan
 */
public class DiscountTest {
    private Discount object;

    @Before
    public void setUp() throws Exception {
        object = new Discount();

    }

    @Test
    public void testDiscount() throws Exception {
        Integer expected = 50;
        object.setDiscount(expected);
        Integer actual = object.getDiscount();
        assertEquals(actual, expected);

    }
}