package com.liwux.singleton;

import java.util.Objects;

/**
 * 饿汉式
 * 类加载到内存后，就是实例化一个单例，JVM保证线程安全
 * 简单使用，推荐使用
 * 唯一缺点，不管用到与否，类加载的时候就完成实例化
 * Class.forName(""),只加在类到内存，不会实例化
 */

public class Mgr01 {

    private  static final Mgr01 INSTANCE = new Mgr01();

    private Mgr01(){}

    public static Mgr01 getInstance(){return INSTANCE;}

    public static void main(String[] args) {
        Mgr01 m1 = Mgr01.getInstance();
        Mgr01 m2 = Mgr01.getInstance();
        System.out.println(Objects.equals(m1, m2));
    }

}


