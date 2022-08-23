package com.liwux.strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Dog[] dogs = {new Dog(3),new Dog(2),new Dog(4)};
        Sorter<Dog> sorter = new Sorter();
        sorter.sort(dogs, new DogComparator());
        System.out.println(Arrays.toString(dogs));
    }
}
