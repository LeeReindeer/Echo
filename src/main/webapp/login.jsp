<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <!-- Meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login in Echo</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,300italic,400italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <!-- Global CSS -->
    <link rel="stylesheet" href="home/assets/plugins/bootstrap/css/bootstrap.min.css">
    <!-- Plugins CSS -->
    <link rel="stylesheet" href="home/assets/plugins/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="home/assets/plugins/prism/prism.css">
    <!--My CSS -->
    <link rel="stylesheet" href="login.css" >
    <!--<link id="theme-style" rel="stylesheet" href="home/assets/css/styles.css">-->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

  <body>
    <!-- ******HEADER****** -->
    <header id="header" class="header">
        <div class="container-fluid">
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
                        <li class="active nav-item sr-only"><a href="/Echoo1">Home</a></li>
                        <li class="nav-item"><a href="/Echoo1">Return</a></li>
                    </ul><!--//nav-->
                </div><!--//navabr-collapse-->
            </nav><!--//main-nav-->
        </div>
    </header><!--//header-->

    <div class="container">
      <form method="post" role="form" class="form-signin" action="LoginServlet" >
        <h2 class="form-signin-heading">Login in Echo</h2>
        <div class="form-group">
        <label for="inputschool">School</label>
        <select class="form-control" name="school">
          <option value="浙江海洋大学">****大学</option>
          <option>**大学</option>
        </select>
        </div>
        <div class="form-group">
        <label for="inputid">Your school ID</label>
        <input type="text" name="schoolid" id="inputid" class="form-control" placeholder="Your school id" required autofocus>
        </div>
        <div class="form-group">
        <label for="inputPassword">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="Submit">Login in</button>
      </form>
    </div> <!-- /container -->
          <!-- Javascript -->
          <script type="text/javascript" src="home/assets/plugins/jquery-1.11.3.min.js"></script>
          <script type="text/javascript" src="home/assets/plugins/jquery.easing.1.3.js"></script>
          <script type="text/javascript" src="home/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
          <script type="text/javascript" src="home/assets/plugins/jquery-scrollTo/jquery.scrollTo.min.js"></script>
          <script type="text/javascript" src="home/assets/plugins/prism/prism.js"></script>
          <script type="text/javascript" src="home/assets/js/main.js"></script>
  </body>
</html>