<%@ page language="java" pageEncoding="UTF-8"%>
<html>
 <head>
  <title>登陆</title>
  <style type="text/css">
  		*{margin:0; padding:0}
  		table{border:1px dashed #B9B9DD;font-size:12pt}
  		td{border:1px dashed #B9B9DD;word-break:break-all; word-wrap:break-word;}
  		#under{width: 200px;height: 20px;margin-top: 300px;}
  	</style>
 </head>
 <body>
  <form method="POST" name="frmLogin" action="LoginServlet">
   <h1 align="center">Echo</h1><br />
   <center>
    <table border=1>
     <tr>
      <td>学校：</td>
      <td>
       <input type="text" name="school"  size="20" maxlength="20"  />
      </td>
     </tr>
     <tr>
      <td>学号：</td>
      <td>
       <input type="text" name="schoolid"  size="20" maxlength="20"  />
      </td>
     </tr>
     <tr>
      <td>密&nbsp;&nbsp;码：</td>
      <td>
       <input type="password" name="password"  size="20" maxlength="20"/>
      </td>
     </tr>
     <tr>
      <td>

      </td>
      <td>
        <input type="submit" name="Submit" value="提交" onClick="return validateLogin()" />
        <input type="reset" name="Reset" value="清除" />
      </td>
     </tr>
    </table>
   </center>
  </form>
  <script language="javascript">
   function validateLogin(){
    var sUserName = document.frmLogin.schoolid.value ;
    var sPassword = document.frmLogin.password.value ;
    var sSchool = document.frmLogin.school.value ;

    if ((sSchool =="") || (sSchool=="")){
                 alert("请输入学校 ('・ω・') ");
                 return false ;
                }
    if ((sUserName =="") && (sPassword=="")){
         alert("请输入学号和密码 ('・ω・') ");
         return false ;
        }else{
           if ((sUserName =="") || (sUserName=="")){
             alert("请输入学号 ('・ω・') ");
             return false ;
            }
           if ((sPassword =="") || (sPassword=="")){
             alert("请输入密码 ('・ω・') ");
             return false ;
            }
       }
   }
  </script>

   <center>
   <div id="under">
   <p>Copyright © 2016-2017 Yappy Doggie</p>
   </div>
   </center>

 </body>
</html>