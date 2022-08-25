package com.liwux.tank60.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("./config"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object get(String key){
        if (props == null) return null;
        return props.get(key);
    }

    //int getInt
    //getString(key)

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }

}
