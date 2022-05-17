package com.javacto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javacto.dao.ProvinceCityDao;
import com.javacto.dao.ProvinceCityDaoImpl;
import com.javacto.po.City;
import com.javacto.po.Province;
import com.javacto.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * liu
 **/
public class ProvinceCityServiceImpl implements ProvinceCityService{
    ProvinceCityDao provinceCityDao = new ProvinceCityDaoImpl();

    @Override
    public List<Province> getProvince() {
        return provinceCityDao.getProvince();
    }

    @Override
    public List<City> getCity(String Pid) {
        return provinceCityDao.getCity(Pid);
    }

    @Override
    public String getProvinceJson() {
        //1先从redis中查询数据
        //1.1.获取redis客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        //1.2从redis获取数据
        String province_json = jedis.get("province");
        //1.3.必需判断，province_json 是否有值
        if(province_json == null || province_json.length() == 0){
            //2.1没有数据 需要调用dao层，查询数据
            List<Province> list = provinceCityDao.getProvince();
            //2.2.将list序列化为json格式
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //2.3.将json数据存入redis
            jedis.set("province",province_json);
            //2.4归还连接
            jedis.close();
        }else {
            System.out.println("有数据，直接使用缓存");
        }
        return province_json;
    }

    @Override
    public String getCityJson(String Pid) {
        //1先从redis中查询数据
        //1.1.获取redis客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        //1.2从redis获取数据
        String city_json = jedis.get("city"+Pid);
        //1.3.必需判断，province_json 是否有值
        if(city_json == null || city_json.length() == 0){
            //2.1没有数据 需要调用dao层，查询数据
            List<City> cityList = provinceCityDao.getCity(Pid);
            //2.2.将list序列化为json格式
            ObjectMapper mapper = new ObjectMapper();
            try {
                city_json = mapper.writeValueAsString(cityList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //2.3.将json数据存入redis
            jedis.set("city"+Pid,city_json);
            //2.4归还连接
            jedis.close();
        }else {
            System.out.println("有数据，直接使用缓存");
        }
        return city_json;
    }
}
