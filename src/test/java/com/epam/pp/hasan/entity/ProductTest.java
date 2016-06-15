package com.epam.pp.hasan.entity;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Yosin_Hasan
 */
public class ProductTest {
    private Product object;

    @Before
    public void setUp() throws Exception {
        object = new Product();
    }

    @Test
    public void testGetAvailable() throws Exception {
        object.setAvailable(Status.AVAILABLE);
        assertEquals(Status.AVAILABLE, object.getAvailable());
    }

    @Test
    public void testGetName() throws Exception {
        String name = "test";
        object.setName(name);
        assertEquals(name, object.getName());
    }

    @Test
    public void testGetDescription() throws Exception {
        String description = "test";
        object.setDescription(description);
        assertEquals(description, object.getDescription());
    }

    @Test
    public void testGetPrice() throws Exception {
        BigDecimal price = BigDecimal.valueOf(10000);
        object.setPrice(price);
        assertEquals(price, object.getPrice());
    }

    @Test
    public void testGetImage() throws Exception {
        String image = "//source";
        object.setImage(image);
        assertEquals(image, object.getImage());
    }

    @Test
    public void testGetCatId() throws Exception {
        Long id = 1L;
        object.setCatId(id);
        assertEquals(id, object.getCatId());
    }

    @Test
    public void testGetBrandId() throws Exception {
        Long id = 1L;
        object.setBrandId(id);
        assertEquals(id, object.getBrandId());
    }
}