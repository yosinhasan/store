package com.epam.pp.hasan.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Yosin_Hasan
 */
public class EntityTest {


    private Entity object;

    @Before
    public void setUp() throws Exception {
        object = new Entity();
    }

    @Test
    public void testGetId() throws Exception {
        Long expected = 1L;
        object.setId(expected);
        Long actual = object.getId();
        assertEquals(actual, expected);
    }
}