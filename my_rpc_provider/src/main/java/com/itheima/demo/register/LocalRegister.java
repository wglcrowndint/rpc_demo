package com.itheima.demo.register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by crowndint on 2019/1/15.
 */
public class LocalRegister {

    private static Map<String, Class> map = new HashMap<>();

    public static void regist(String interfaceName, Class implClass) {

        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName) {

        return map.get(interfaceName);
    }



}
