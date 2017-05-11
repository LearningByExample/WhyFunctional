package org.learning.by.example;

import org.junit.Test;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.learning.by.example.TestSuite5.Wrapper.From;

public class TestSuite5 {
    static class Wrapper<Type> {
        private final Type value;

        private Wrapper(final Type value) {
            this.value = value;
        }

        static <AnyType> Wrapper<AnyType> From(final AnyType value) {
            return new Wrapper<>(value);
        }

        static <AnyType> Wrapper<AnyType> From(final Supplier<AnyType> supplier) {
            return From(supplier.get());
        }

        <OtherType> Wrapper<OtherType> pipe(final Function<Type, OtherType> transformer) {
            return From(transformer.apply(value));
        }

        void pipe(final Consumer<Type> consumer) {
            consumer.accept(value);
        }
    }

    private int random() {
        Random rand = new Random();

        return rand.nextInt();
    }

    private String reverse(String value) {
        return new StringBuffer(value).reverse().toString();
    }

    private void print(Object value) {
        System.out.println(value.toString());
    }

    private int square(final int value) {
        return (int) Math.pow(value, 2);
    }

    private String text(final int value) {
        return Integer.toString(value);
    }

    @Test
    public void test1(){
        From("hello world")
            .pipe(this::reverse)
            .pipe(this::print);
    }

    @Test
    public void test2(){
        From(this::random)
            .pipe(this::print);
    }

    @Test
    public void test3() {
        From(12545)
            .pipe(this::square)
            .pipe(this::text)
            .pipe(this::reverse)
            .pipe(this::print);
    }
}
