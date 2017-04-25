<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="ObjectC.UserInfo"%>
<%@ page import="ObjectC.SchoolName"%>
<%@ page import="java.net.*"%>
<html>
<head>
<meta name="viewport" content="width=device-width,user-scalable=0">
<title>个人设置</title>
</head>
<body>
<%
        //UserInfo user = (UserInfo)request.getAttribute("UserInfo");
        String school=null;
        String name="";
        String schoolid=null;
        String schoolname=null;
        Cookie[]cookies=null;
        cookies=request.getCookies();
        if (cookies!=null){
           for (Cookie temp:cookies){
                if (temp.getName().equals("school")){
                    school= URLDecoder.decode(temp.getValue(),"UTF-8");
                }
                if (temp.getName().equals("schoolid")){
                    schoolid=URLDecoder.decode(temp.getValue(),"UTF-8");
                }
                if (temp.getName().equals("name")){
                    name=URLDecoder.decode(temp.getValue(),"UTF-8");
                }
           }
        }else{
                school  = (String)session.getAttribute("school") ;
                name  = (String)session.getAttribute("name") ;
                schoolid  = (String)session.getAttribute("schoolid") ;
        }
        if(school==null&&schoolid==null){
           out.print("请先登录");
           response.setHeader("Refresh","1;URL=/Echoo1/login.jsp");
        }
	    //回调
	    String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx65bef057c253d2e1&redirect_uri=http://120.24.73.230/Echoo1/OAuth&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	    //reUrl=url.replace("STATE",schoolid+","+schoolname);

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
     <td>&nbsp;学校：&nbsp;</td>
     <td>&nbsp;&nbsp;<%=school %>&nbsp;&nbsp;</td>
    </tr>
    <tr>
     <td>&nbsp;学号：&nbsp;</td>
     <td>&nbsp;&nbsp;<%=schoolid %>&nbsp;&nbsp;</>
    </tr>
   </table>
   <a href="login.jsp">返回</a> <a href="<%=url%>">绑定微信</a>
  </div>
 </body>
</html>