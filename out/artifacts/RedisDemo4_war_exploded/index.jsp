<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/23
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>


<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script>

  $(function () {
    //alert("111");
    /*获取省数据*/
    $.get("${pageContext.request.contextPath}/getProvince.do", function(data){

      //再把这个转为json    resp.setContentType("text/html;charset=UTF-8"); 如果是这种
      //var data = JSON.parse(data);
      /* alert("Data Loaded: " + data);
       console.log(data);*/
      //1.获取省province
      var province = $("#province");
      /*province.append("<option value='-1'>--请选择省份--</option>");*/

      //$(data).each();
      $(data).each(function () {
        // {"codePid":"110000","id":"1","name":"湖北省"}
        var option = "<option value='"+this.codePid+"'>"+this.name+"</option>";
        console.log(option);
        //4.调用select的append追加option
        province.append(option);

      });

    });

  });


  //获取市
  function getCity(pid) {
    $.ajax({
      type: "POST",
      url: "${pageContext.request.contextPath}/getCity.do",
      data: "pid="+pid,
      success: function(msg){
        /*alert("Data Loaded: " + msg);
        console.log(msg);*/
        //ar data = JSON.parse(msg);

        //1.获取省province
        var city = $("#city");

        //如果是市 ，每次必需把option 数据清空
        document.getElementById("city").options.length=0;

        city.append("<option value='-1'>--请选择市--</option>");
        //$(data).each();
        $(msg).each(function () {
          // {"codePid":"110000","id":"1","name":"湖北省"}
          var option = "<option value='"+this.codeCid+"'>"+this.name+"</option>";

          //4.调用select的append追加option
          city.append(option);

        });
      }
    });
  }
</script>
<html>
<head>
  <title>$Title$</title>
</head>
<body>
<select id="province" onchange="getCity(this.value)">
  <option value=''>--请选择省份--</option>
</select>

<select id="city">
  <option>--请选择市--</option>

</select>
</body>
</html>
