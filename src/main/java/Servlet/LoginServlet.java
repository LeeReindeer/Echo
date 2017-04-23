package Servlet;

import ObjectC.UserInfo;
import Util.MysqlUtil;
import Util.dbConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lee on 4/22/17.
 */



public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        /*request.setAttribute("UserInfo", userInfo);
        request.getRequestDispatcher("userSetting.jsp").forward(request,response);*/


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //todo upload user info from login.jsp

        String school=request.getParameter("school");
        String schoolid=request.getParameter("schoolid");
        String password=request.getParameter("password");
        HttpSession session=request.getSession();


        UserInfo userInfo=new UserInfo();
        userInfo.setSchoolid(schoolid);
        userInfo.setPassword(password);
        userInfo.setSchool(school);
        try {
            if (schoolid != null && password != null) {
                Connection connection = dbConnector.getConnector();
                MysqlUtil mysqlUtil = new MysqlUtil(connection);
                //密码和学号相匹配
                if (mysqlUtil.checkUser(userInfo)) {
                    ResultSet rs = mysqlUtil.checkResult(userInfo);
                    //从数据库提取数据
                    String name = rs.getString("name");
                    userInfo.setName(name);
                    request.setAttribute("UserInfo", userInfo);
                    //跳转d
                    request.getRequestDispatcher("userSetting.jsp").forward(request, response);
                } else {
                    session.setAttribute("schoolid",schoolid);
                    session.setAttribute("message", "用户名或密码不匹配。");
                    response.sendRedirect("fail.jsp");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
