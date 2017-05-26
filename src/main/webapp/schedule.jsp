<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="Util.*"%>
<%@ page import="Bean.*"%>
<jsp:useBean id="table" scope="session" class="Bean.TableBean" />
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Schedule</title>
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
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button><!--//nav-toggle-->
                </div><!--//navbar-header-->
                <div class="navbar-collapse collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active nav-item sr-only"><a href="#promo">Home</a></li>
                        <li class="nav-item"><a href="profile.jsp">Profile</a></li>
                        <li class="nav-item"><a href="out.jsp">Login out</a></li>
                    </ul><!--//nav-->
                </div><!--//navabr-collapse-->
            </nav><!--//main-nav-->
        </div>
    </header><!--//header-->

	  <div class="container-fluid">
	    <div class="row">
        <main class="col-ms-2 ">
	  	<div class="table-responsive">
	  	<table class="table table-hover table table-bordered">
	  	<thead>
	  	<tr>
	  	<th></th>
	    <th>星期一</th>
    	<th>星期二</th>
        <th>星期三</th>
        <th>星期四</th>
    	<th>星期五</th>
        <th>星期六</th>
    	<th>星期天</th>
	  	</tr>
	    </thead>
        <tbody>
        <%
        String [][]scheduleArray=table.processRequest(request);
        for(int i=1;i<=12;i++){
        %>
        <tr>
        <th>第<%=i%>节</th>
        <%
            for(int j=1;j<=7;j++){
            if(scheduleArray[i][j]==null){
                scheduleArray[i][j]="";
            }
            //rowspan=endclass-startclass+1
        %>
        <td rowspan="1"><%=scheduleArray[i][j]%></td>
        <%
            }
        %>
        <tr>
        <%
        }
        %>
        </tbody>
	    </table>
	    </div>

      <nav aria-label="Page navigation">
      <ul class="pagination">
      <li>
      <a href="?week=prev" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <%
    for(int i=1;i<=19;i++){
    %>
    <li><a href="?week=<%=i%>"><%=i%></a></li>
    <%
    }
    %>
    <li>
      <a href="?week=next" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
  </nav>
	</main>
	</div>
	</div>
      <!-- Javascript -->
      <script type="text/javascript" src="home/assets/plugins/jquery-1.11.3.min.js"></script>
      <script type="text/javascript" src="home/assets/plugins/jquery.easing.1.3.js"></script>
      <script type="text/javascript" src="home/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
      <script type="text/javascript" src="home/assets/plugins/jquery-scrollTo/jquery.scrollTo.min.js"></script>
      <script type="text/javascript" src="home/assets/plugins/prism/prism.js"></script>
      <script type="text/javascript" src="home/assets/js/main.js"></script>
</body>
</html>