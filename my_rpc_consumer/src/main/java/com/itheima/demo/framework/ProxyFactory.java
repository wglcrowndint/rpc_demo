package com.itheima.demo.framework;

import com.alibaba.fastjson.JSON;
import com.itheima.demo.pojo.Brand;

import java.lang.reflect.*;
import java.util.List;

/**
 * Created by crowndint on 2019/1/15.
 */
public class ProxyFactory {

    public static <T> T getProxy(Class interfaceClass) {

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), args, method.getParameterTypes());
                        //根据接口名获取一个服务提供者的地址
                        URL url = RegisterCenter.random(interfaceClass.getName());
                        //发起远程方法调用
                        Object result = HttpProtocol.send(url, invocation);
                        System.out.println(result);
                        return result;
                        //return JSON.parseArray(result, (Class) type);
                    }
                });
    }

}
