package Servlet;

import ObjectC.UserInfo;
import Util.MysqlUtil;
import Util.dbConnector;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lee on 4/22/17.
 */



public class LoginServlet extends HttpServlet{

    private static final int MAXTIME=5*365*24*60*60;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String school=request.getParameter("school");
        String schoolid=request.getParameter("schoolid");
        String  cschool= URLEncoder.encode(request.getParameter("school"),"UTF-8");
        String cschoolid= URLEncoder.encode(request.getParameter("schoolid"),"UTF-8");
        String password=request.getParameter("password");
        HttpSession session=request.getSession();
        UserInfo userInfo=new UserInfo();
        userInfo.setSchoolid(schoolid);
        userInfo.setPassword(password);
        userInfo.setSchool(school);
        Cookie cSchool=new Cookie("school",cschool);
        Cookie cSchoolid=new Cookie("schoolid",cschoolid);
        cSchool.setMaxAge(MAXTIME);
        cSchoolid.setMaxAge(MAXTIME);
        response.addCookie(cSchool);
        response.addCookie(cSchoolid);
        try {
            if (schoolid != null && password != null) {
                Connection connection = dbConnector.getConnector();
                MysqlUtil mysqlUtil = new MysqlUtil(connection);
                //密码和学号是否相匹配
                if (mysqlUtil.checkUser(userInfo)) {
                    ResultSet rs = mysqlUtil.checkResult(userInfo);
                    //从数据库提取数据
                    String name = rs.getString("name");
                    userInfo.setName(name);
                    String cname=URLEncoder.encode(name,"UTF-8");
                    Cookie cName=new Cookie("name",cname);
                    cName.setMaxAge(MAXTIME);
                    response.addCookie(cName);
                    session.setAttribute("school",school);
                    session.setAttribute("name",name);
                    session.setAttribute("schoolid",schoolid);
                    //request.getRequestDispatcher("profile.jsp").forward(request, response);
                    response.sendRedirect("profile.jsp");
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
