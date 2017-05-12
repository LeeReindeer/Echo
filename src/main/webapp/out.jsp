<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <title>login out</title>
</head>
<body>
<%
     Cookie[]cookies=null;
     cookies=request.getCookies();
     //删除cookies
     if (cookies!=null){

         for (Cookie temp:cookies){
             temp.setMaxAge(0);
             response.addCookie(temp);
         }
     response.setHeader("Refresh","0;URL=/Echoo1/");
    }
%>
</body>
</html>