package com.itheima.demo.framework;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by crowndint on 2019/1/19.
 */
public class RegisterCenter {




    public static void regist(String interfaceName, URL url)  {

        Jedis jedis = new Jedis("localhost");
        String str = jedis.get(interfaceName);
        if (str == null || "".equals(str)) {
            str = "[]";
        }
        System.out.println(str);
        List<URL>  list = JSON.parseArray(str, URL.class);
        list.add(url);
        jedis.set(interfaceName, JSON.toJSONString(list));
    }

    public static URL random(String interfaceName) {

        Jedis jedis = new Jedis("localhost");
        String str = jedis.get(interfaceName);
        List<URL> list = JSON.parseArray(str, URL.class);
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }


    private static Map<String, List<URL>> REGISTER = new HashMap<>();
    /**
     * 序列化
     *
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {

        }
        return null;
    }


    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("c:\\temp\\temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("c:\\temp\\temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
