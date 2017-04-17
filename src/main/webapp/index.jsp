
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="Util.UserInfo;"%>
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
	%>

    <center>
	    <div id="users"><p>
    	<form>

    	<h1>Echo</h1>
    	<center>
    	  <form method=get>
          hi <input type=text name=schoolid>
          <input type=submit value="Submit">
          </form>
    	</center>

    	</p></div>
    </center>

    <% out.print(user.getSchoolid()); %>

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