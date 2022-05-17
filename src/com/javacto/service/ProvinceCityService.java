package com.javacto.service;

import com.javacto.po.City;
import com.javacto.po.Province;

import java.util.List;

/**
 * liu
 **/
public interface ProvinceCityService {
    /**
     * 获取省
     */
    public List<Province> getProvince();
    /**
     * 获取市
     * Pid省id
     */
    public List<City> getCity(String Pid);

    /**
     * 获取省
     */
    public String getProvinceJson();
    /**
     * 获取市
     * Pid省id
     */
    public String getCityJson(String Pid);
}
