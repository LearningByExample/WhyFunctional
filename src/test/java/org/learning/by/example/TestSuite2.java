package org.learning.by.example;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestSuite2 {
    private Supplier<String> name() {
        return () -> "functional";
    }

    private Function<String,String> reverse() {
        return value -> new StringBuffer(value).reverse().toString();
    }

    private Consumer<String> print() {
        return value -> System.out.println(value);
    }

    @Test
    public void test1() {
        print().accept(reverse().apply(name().get()));
    }

    @Test
    public void test2() {
        final String name = name().get();
        final String reverseName = reverse().apply(name);
        print().accept(reverseName);
    }
}
