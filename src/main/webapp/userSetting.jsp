<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="ObjectC.UserInfo;"%>
<html>
<head>
<meta name="viewport" content="width=device-width,user-scalable=0">
<title>个人设置</title>
</head>
<body>
<%
        UserInfo user = (UserInfo)request.getAttribute("UserInfo");
        String school=user.getSchool();
        String name=user.getName();
        String schoolid=user.getSchoolid();
	    //jsp回调
	    String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx65bef057c253d2e1&redirect_uri=http://120.24.73.230/Echoo1/OAuth&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
%>
  <div align="center">
   <%=name %>
   欢迎您，登陆成功！<br />
   <font color="blue">登陆用户信息：</font>
   <table border =1 >
    <tr>
     <td>&nbsp;姓名：&nbsp;</td>
     <td>&nbsp;&nbsp;<%=name %>&nbsp;&nbsp;</td>
    </tr>
    <tr>
     <td>&nbsp;学号：&nbsp;</td>
     <td>&nbsp;&nbsp;<%=schoolid %>&nbsp;&nbsp;</td>
    </tr>
    <tr>
     <td>&nbsp;学校：&nbsp;</td>
     <td>&nbsp;&nbsp;<%=school %>&nbsp;&nbsp;</>
    </tr>
   </table>
   <a href="login.jsp">返回</a> <a href="<%=url%>">绑定微信</a>
  </div>
 </body>
</html>