package com.javacto.dao;

import com.javacto.po.City;
import com.javacto.po.Province;

import java.util.List;

/**
 * liu
 **/
public interface ProvinceCityDao {
    /**
     * 获取省
     */
    public List<Province> getProvince();
    /**
     * 获取市
     * Pid省id
     */
    public List<City> getCity(String Pid);
}
