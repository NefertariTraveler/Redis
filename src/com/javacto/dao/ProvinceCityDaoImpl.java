package com.javacto.dao;

import com.javacto.po.City;
import com.javacto.po.Province;
import com.javacto.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * liu
 **/
public class ProvinceCityDaoImpl implements ProvinceCityDao{
    @Override
    public List<Province> getProvince() {
        List<Province> list = new ArrayList<Province>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstm = conn.prepareStatement("SELECT * FROM province");
            rs = pstm.executeQuery();
            while (rs.next()) {
                Province province = new Province();
                province.setId(rs.getString(1));
                province.setCodePid(rs.getString(2));
                province.setName(rs.getString(3));
                list.add(province);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(conn,pstm,rs);
        }
        return list;
    }

    @Override
    public List<City> getCity(String Pid) {
        List<City> listCity = new ArrayList<City>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstm = conn.prepareStatement("SELECT * FROM city WHERE city.code_pid = ?");
            pstm.setObject(1,Pid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getString(1));
                city.setCodeCid(rs.getString(2));
                city.setName(rs.getString(3));
                city.setCodePid(rs.getString(4));
                listCity.add(city);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeAll(conn,pstm,rs);
        }
        return listCity;
    }
}
