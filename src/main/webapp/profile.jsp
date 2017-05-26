<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
  <%
        String school=null;
        String name=null;
        String schoolid=null;
        String schoolname=null;
        //todo cookies自动登录
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
         }
         if(name!=null&&school!=null&&schoolid!=null){

  %>
<head>
    <title>Echo-<%=schoolid%></title>
    <!-- Meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="home/favicon.ico">
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,300italic,400italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <!-- Global CSS -->
    <link rel="stylesheet" href="home/assets/plugins/bootstrap/css/bootstrap.min.css">
    <!-- Plugins CSS -->
    <link rel="stylesheet" href="home/assets/plugins/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="home/assets/plugins/prism/prism.css">
    <!-- Theme CSS -->
    <link id="theme-style" rel="stylesheet" href="home/assets/css/styles.css">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
        <!-- ******HEADER****** -->
        <header id="header" class="header">
            <div class="container">
                <h1 class="logo pull-left">
                    <a href="/Echoo1">
                        <span class="logo-title">Echo</span>
                    </a>
                </h1><!--//logo-->
                <nav id="main-nav" class="main-nav navbar-right" role="navigation">
                    <div class="navbar-header">
                        <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button><!--//nav-toggle-->
                    </div><!--//navbar-header-->
                    <div class="navbar-collapse collapse" id="navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li class="active nav-item sr-only"><a href="#promo">Home</a></li>
                            <li class="nav-item"><a href="schedule.jsp">Schedule</a></li>
                            <li class="nav-item"><a href="out.jsp">Login out</a></li>
                        </ul><!--//nav-->
                    </div><!--//navabr-collapse-->
                </nav><!--//main-nav-->
            </div>
        </header><!--//header-->
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>


<section class="contact section has-pattern">
<div class="container">
    <div class="contact-innner">
              <div class="author-message">
                    <div class="profile">
                        <img class="img-responsive" src="home/assets/images/lee.jpg" alt="" />
                    </div><!--//profile-->
                    <div class="speech-bubble">
                        <h3 class="sub-title"><%=name%></h3>
                        <p><%=school%></p>
                        <p><%=schoolid%></p><br>
                        <a href="/Echoo1">返回</a> <a href="/Echoo1/OAuth2Servlet">绑定微信</a><br>
                        <a href="out.jsp">登出</a><br>
                    </div><!--//speech-bubble-->
              </div><!--//message-->
    </div>
</div>
</section>

  <%
    }else {
             //out.print("请先登录");
             //fail_key=1 没有登录,无本地cookies
             response.setHeader("Refresh","0;URL=login.jsp?fail_key=1");
           }
  %>
      <!-- Javascript -->
      <script type="text/javascript" src="home/assets/plugins/jquery-1.11.3.min.js"></script>
      <script type="text/javascript" src="home/assets/plugins/jquery.easing.1.3.js"></script>
      <script type="text/javascript" src="home/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
      <script type="text/javascript" src="home/assets/plugins/jquery-scrollTo/jquery.scrollTo.min.js"></script>
      <script type="text/javascript" src="home/assets/plugins/prism/prism.js"></script>
      <script type="text/javascript" src="home/assets/js/main.js"></script>
 </body>
</html>