package com.javacto.action;

import com.javacto.po.City;
import com.javacto.po.Province;
import com.javacto.service.ProvinceCityService;
import com.javacto.service.ProvinceCityServiceImpl;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * liu
 **/
@WebServlet("/getCity.do")
public class CityAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.处理请求编码格式
        req.setCharacterEncoding("UTF-8");
        //2.处理响映编码格式
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        //3.获取省数据
        String pid = req.getParameter("pid");
        //4.创建业务层
        ProvinceCityService provinceCityService = new ProvinceCityServiceImpl();
        /*//5.调用业务层
        List<City> cityList = provinceCityService.getCity(pid);
        //6.把数据转为json格式
        JSONArray jsonList = JSONArray.fromObject(cityList);
        System.out.println(jsonList);*/
        String cityJson = provinceCityService.getCityJson(pid);
        System.out.println(cityJson);
        out.println(cityJson);
    }
}
