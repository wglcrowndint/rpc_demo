package com.itheima;


import com.alibaba.fastjson.JSON;
import com.itheima.demo.framework.HttpServer;
import com.itheima.demo.framework.URL;
import com.itheima.demo.register.LocalRegister;
import com.itheima.demo.register.RegisterCenter;
import com.itheima.demo.service.BrandService;
import com.itheima.demo.service.impl.BrandServiceImpl;

import java.util.List;

/**
 * Created by crowndint on 2019/1/15.
 */
public class Provider {

    public static void main(String[] args) {

        //本地注册 (服务名:实现类)
        LocalRegister.regist(BrandService.class.getName(), BrandServiceImpl.class);

        //注册提供服务的地址
        URL url = new URL("localhost", 8085);
        RegisterCenter.regist(BrandService.class.getName(), url);

        //启动tomcat
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }
}
