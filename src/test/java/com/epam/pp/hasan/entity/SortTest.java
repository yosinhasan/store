package com.epam.pp.hasan.entity;

import com.epam.pp.hasan.config.Fields;
import com.epam.pp.hasan.util.OrderUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Yosin_Hasan
 */
public class SortTest {
    private Sort sort;

    @Before
    public void setUp() throws Exception {
        sort = OrderUtil.getProductOrder();

    }

    @Test
    public void testGetOrder() throws Exception {
        assertEquals(Fields.ENTITY_NAME, sort.getOrder(0));
    }

    @Test
    public void testGetOrder1() throws Exception {
        assertEquals(Fields.PRODUCT_PRICE, sort.getOrder(1));
    }

    @Test
    public void testGetOrder2() throws Exception {
        assertEquals(Fields.PRODUCT_CATEGORY_ID, sort.getOrder(2));
    }

    @Test
    public void testGetOrder3() throws Exception {
        assertEquals(Fields.PRODUCT_BRAND_ID, sort.getOrder(3));
    }
}