package com.epam.pp.hasan.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Yosin_Hasan
 */
public class ColorTest {

    private Color object;

    @Before
    public void setUp() throws Exception {
        object = new Color();
    }

    @Test
    public void testGetName() throws Exception {
        String expected = "test";
        object.setName(expected);
        String actual = object.getName();
        assertEquals(actual, expected);

    }
}