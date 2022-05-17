package com.javacto.test;

import com.javacto.dao.ProvinceCityDao;
import com.javacto.dao.ProvinceCityDaoImpl;
import com.javacto.po.City;
import com.javacto.po.Province;

import java.util.List;

/**
 * liu
 **/
public class Test1 {
    public static void main(String[] args) {
        ProvinceCityDao provinceCityDao = new ProvinceCityDaoImpl();
        List<Province> province = provinceCityDao.getProvince();
        for (Province province1 : province) {
            System.out.println(province1);
        }

        List<City> city = provinceCityDao.getCity("110000");
        for (City city1 : city) {
            System.out.println(city1);
        }
    }
}
