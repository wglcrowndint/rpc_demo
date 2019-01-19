package com.itheima.demo;

import com.itheima.demo.framework.ProxyFactory;
import com.itheima.demo.pojo.Brand;
import com.itheima.demo.service.BrandService;


import java.util.List;

/**
 * Created by crowndint on 2019/1/15.
 */
public class Consumer {

    public static void main(String[] args) {

        BrandService brandService = ProxyFactory.getProxy(BrandService.class);
        List<Brand> brandList = brandService.findAll();
        for (Brand brand : brandList) {
            System.out.println(brand);
        }
    }
}
