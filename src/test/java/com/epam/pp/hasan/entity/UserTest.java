package com.epam.pp.hasan.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Yosin_Hasan
 */
public class UserTest {
    private User user;
    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void testGetName() throws Exception {
        String expected = "Test";
        user.setName(expected);
        String actual = user.getName();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetId() throws Exception {
        Long expected = 1L;
        user.setId(expected);
        Long actual = user.getId();
        assertEquals(actual, expected);

    }

    @Test
    public void testGetSurname() throws Exception {
        String expected = "Test";
        user.setSurname(expected);
        String actual = user.getSurname();
        assertEquals(actual, expected);

    }

    @Test
    public void testGetEmail() throws Exception {
        String expected = "test@test.com";
        user.setEmail(expected);
        String actual = user.getEmail();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetPhone() throws Exception {
        String expected = "+380938213009";
        user.setPhone(expected);
        String actual = user.getPhone();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetPassword() throws Exception {
        String expected = "123456789";
        user.setPassword(expected);
        String actual = user.getPassword();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetSex() throws Exception {
        Sex expected = Sex.MALE;
        user.setSex(expected);
        Sex actual = user.getSex();
        assertEquals(actual, expected);

    }
}