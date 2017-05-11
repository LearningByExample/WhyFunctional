package org.learning.by.example;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.learning.by.example.TestSuite4.Wrapper.From;

public class TestSuite4 {
    static class Wrapper {
        private final String value;

        private Wrapper(final String value) {
            this.value = value;
        }

        static Wrapper From(final String value) {
            return new Wrapper(value);
        }

        static Wrapper From(final Supplier<String> supplier) {
            return From(supplier.get());
        }

        Wrapper pipe(final Function<String, String> transformer) {
            return From(transformer.apply(value));
        }

        void pipe(final Consumer<String> consumer) {
            consumer.accept(value);
        }
    }

    private String name() {
        return "functional";
    }

    private String reverse(String value) {
        return new StringBuffer(value).reverse().toString();
    }

    private void print(String value) {
        System.out.println(value);
    }

    @Test
    public void test1() {
        From(this::name)
            .pipe(this::reverse)
            .pipe(this::print);
    }

    @Test
    public void test2() {
        From("hello world")
            .pipe(this::reverse)
            .pipe(this::reverse)
            .pipe(this::print);
    }
}
