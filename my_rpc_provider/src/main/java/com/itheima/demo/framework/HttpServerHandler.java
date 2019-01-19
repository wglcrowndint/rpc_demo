package com.itheima.demo.framework;

import com.alibaba.fastjson.JSON;
import com.itheima.demo.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

/**
 * Created by crowndint on 2019/1/15.
 */
public class HttpServerHandler {

    public void handler(ServletRequest req, ServletResponse res) {

        try {
            ServletInputStream inputStream = req.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation) objectInputStream.readObject();
            String interfaceName = invocation.getInterfaceName();
            String methodName = invocation.getMethodName();
            //从注册中心获取存放的实现类
            Class implClass = LocalRegister.get(interfaceName);
            Method method = implClass.getMethod(methodName, invocation.getParamTypes());
            //调用方法获取结果
            Object result = method.invoke(implClass.newInstance(), invocation.getPrams());
            //把结果写入到Response
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(res.getOutputStream());
            objectOutputStream.writeObject(result);
            //IOUtils.write(JSON.toJSONString(result), res.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
