package com.epam.pp.hasan.repository.impl;

import com.epam.pp.hasan.entity.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Yosin_Hasan
 */
public class BasketRepositoryImplTest {
    private BasketRepositoryImpl basketRepository;
    private Product item;

    @Before
    public void setUp() throws Exception {
        basketRepository = new BasketRepositoryImpl();
        item = new Product();
        item.setId(10L);
        item.setPrice(BigDecimal.valueOf(100));
        basketRepository.addProduct(item);

    }

    @Test
    public void testAddProduct() throws Exception {
        Boolean expected = true;
        Boolean actual = basketRepository.addProduct(new Product());
        assertEquals(actual, expected);
    }

    @Test
    public void testAddProduct1() throws Exception {
        Boolean expected = false;
        Boolean actual = basketRepository.addProduct(null, 10);
        assertEquals(actual, expected);
    }

    @Test
    public void testRemoveProduct() throws Exception {
        item = new Product();
        item.setId(1L);
        basketRepository.addProduct(item);
        Boolean expected = true;
        Boolean actual = basketRepository.removeProduct(1L);
        assertEquals(actual, expected);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Boolean expected = true;
        Boolean actual = basketRepository.updateProduct(10L, 10);
        assertEquals(actual, expected);
    }


    @Test
    public void testFindAll() throws Exception {
        assertNotNull(basketRepository.findAll());
    }

    @Test
    public void testCountAmount() throws Exception {
        Integer expected = new Integer(1);
        Integer actual = basketRepository.countAmount();
        assertEquals(actual, expected);
    }

    @Test
    public void testCountSumm() throws Exception {
        BigDecimal expected = BigDecimal.valueOf(100);
        BigDecimal actual = basketRepository.countSumm();
        assertEquals(actual, expected);
    }

    @Test
    public void testRemoveAll() throws Exception {
        basketRepository.removeAll();
        Integer expected = 0;
        Integer actual = basketRepository.countAmount();
        assertEquals(actual, expected);
    }
}