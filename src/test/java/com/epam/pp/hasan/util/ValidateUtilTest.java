package com.epam.pp.hasan.util;
import static org.junit.Assert.*;

/**
 * @author Yosin_Hasan
 */
public class ValidateUtilTest {

    @org.junit.Test
    public void testIsValidNumber() throws Exception {
        Boolean expected = true;
        Boolean actual = ValidateUtil.isValidNumber("1234");
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void testIsValidNumber1() throws Exception {
        Boolean expected = true;
        Boolean actual = ValidateUtil.isValidNumber(1);
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void testIsValidString() throws Exception {
        Boolean expected = true;
        Boolean actual = ValidateUtil.isValidString("helloworld");
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void testIsValidDate() throws Exception {
        Boolean expected = true;
        Boolean actual = ValidateUtil.isValidDate("2060-12-30");
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void testIsValidPhone() throws Exception {
        Boolean expected = true;
        Boolean actual = ValidateUtil.isValidPhone("+380938213009");
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void testIsValidLength() throws Exception {
        Boolean expected = true;
        Boolean actual = ValidateUtil.isValidLength("helloworld", 4);
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void testIsValidLength1() throws Exception {
        Boolean expected = true;
        Boolean actual = ValidateUtil.isValidLength("helloworld", 3, 20);
        assertEquals(actual, expected);
    }

    @org.junit.Test
    public void testIsValidEmail() throws Exception {
        Boolean expected = true;
        Boolean actual = ValidateUtil.isValidEmail("yosinhasan@gmail.com");
        assertEquals(actual, expected);

    }
}