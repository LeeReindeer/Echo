<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="net.sf.json.JSONObject"%>
<html>
<head>
<title>发送通知</title>
</head>
<body>

  <div align="center">
   <br/>
   <form method=POST action=SendMessageServlet >
    <br> 消息内容<input  type=text  name="content" >
    <br> <input type="submit" name="Submit" value="发送" />
   </form>
   <br/>
  </div>

 </body>
</html>