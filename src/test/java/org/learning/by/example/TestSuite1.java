package org.learning.by.example;

import org.junit.Test;

public class TestSuite1 {

    private String name() {
        return "functional";
    }

    private String reverse(final String value) {
        return new StringBuffer(value).reverse().toString();
    }

    private void print(String value) {
        System.out.println(value);
    }

    @Test
    public void test1() {
        print(reverse(name()));
    }

    @Test
    public void test2() {
        final String name = name();
        final String reverseName = reverse(name);
        print(reverseName);
    }

}