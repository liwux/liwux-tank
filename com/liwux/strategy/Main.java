package com.liwux.strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Dog[] dogs = {new Dog(3),new Dog(2),new Dog(4)};
        Sorter<Dog> sorter = new Sorter();
        sorter.sort(dogs, (o1,o2)->{
            if(o1.food<o2.food) return -1;
            else if (o1.food>o2.food) return 1;
            return 0;
        });
        System.out.println(Arrays.toString(dogs));
    }
}
