package org.learning.by.example;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.learning.by.example.TestSuite3.Wrapper.From;

public class TestSuite3 {
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
        From(name())
            .pipe(reverse())
            .pipe(print());
    }

    @Test
    public void test2() {
        From("hello world")
            .pipe(reverse())
            .pipe(print());
    }
}
