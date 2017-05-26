package Servlet;

import ObjectC.OAuth2AccessToken;
import ObjectC.UserInfo;
import Util.MysqlUtil;
import Util.PsqlUtil;
import Util.WebUtil;
import Util.dbConnector;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;

import static Util.MysqlUtil.OPRNID;
import static Util.MysqlUtil.SCHOOLID;

/**
 * Created by lee on 4/15/17.
 */
public class OAuth2Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        //PrintWriter writer=response.getWriter();
        //writer.println(code);
        //连接数据库.
        Connection connection = dbConnector.getConnector();
        MysqlUtil mysqlUtil = new MysqlUtil(connection);
        Connection pconnection=dbConnector.getPConnector();
        PsqlUtil psqlUtil=new PsqlUtil(pconnection);
        if (code!=null&&!code.equals("")) {
                WebUtil webUtil = new WebUtil();
                //通过code换取网页授权的access_token
                OAuth2AccessToken oAuth2AccessToken = webUtil.getOAuth2AccessToken(code);
                //access_token
                String access_token = oAuth2AccessToken.getAccess_token();
                //openid
                String openid = oAuth2AccessToken.getOpenid();
                //获取用户信息
                UserInfo userInfo = webUtil.getUserInfo(access_token, openid);
                //UserInfo userInfo1=userInfo;
                String schoolid = "";
                String school="";
                String name="";
                //get cookie
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
                userInfo.setSchoolid(schoolid);
                userInfo.setSchool(school);
                userInfo.setName(name);
                //跳转团队业务
                //response.sendRedirect("http://yappyap.com/schedule?openid="+openid);
                //自己的业务
                //todo 跳转到profile.jsp，通过参数显示是否绑定
                request.setAttribute("UserInfo", userInfo);
                request.getRequestDispatcher("message.jsp").forward(request,response);
                if (mysqlUtil.isUserInSql(userInfo,SCHOOLID)&&!mysqlUtil.isUserInSql(userInfo,OPRNID)) {
                    try {
                        //link  openid to schoolId
                        mysqlUtil.updateUser(userInfo,OPRNID);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }

                //postgrensql
                /*if (!psqlUtil.isUserInSql(userInfo,OPRNID)&&mysqlUtil.isUserInSql(userInfo,SCHOOLID)) {
                    try {
                        //UPDATE openid link to schoolId
                        psqlUtil.updateUser(userInfo,STUDENT);
                        request.setAttribute("UserInfo", userInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/
            }

        //request.getRequestDispatcher("message.jsp").forward(request,response);

    }
}
