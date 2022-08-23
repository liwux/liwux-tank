package com.liwux.strategy;

@FunctionalInterface
public interface Comparator<T> {
    int Compare(T o1,T o2);

    default void main(){
        System.out.println("m");
    }
}
