
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="ObjectC.UserInfo"%>
<html>
<head>
	<title>账号设置</title>
	<meta name="viewport" content="width=device-width,user-scalable=0">
	<style type="text/css">
		*{margin:0; padding:0}
		table{border:1px dashed #B9B9DD;font-size:12pt}
		td{border:1px dashed #B9B9DD;word-break:break-all; word-wrap:break-word;}
		#under{width: 200px;height: 20px;margin-top: 350px;}
	</style>
</head>
<body>
	<%
		UserInfo user = (UserInfo)request.getAttribute("UserInfo");
		if(null != user) {
		String name=user.getName();
		String openid=user.getOpenid();
		String schoolid=user.getSchoolid();
	%>

    <center>
	    <div id="users"><p>
    	  <%=name %>
    	  <%=schoolid%>
    	  <%=openid%>
           绑定成功！<br />
    	<a href="profile.jsp">返回个人设置</a>
	</center>

    <center>
	<div id="under">
	<p>Copyright © 2016-2017 Yappy Doggie</p>
	</div>
	</center>

	<%
		}
		else
			out.print("用户不同意授权,未获取到用户信息！");
	%>
</body>
</html>