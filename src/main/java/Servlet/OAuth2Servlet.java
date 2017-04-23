package Servlet;

import ObjectC.OAuth2AccessToken;
import ObjectC.UserInfo;
import Util.MysqlUtil;
import Util.WebUtil;
import Util.dbConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import static Util.MysqlUtil.OPRNID;

/**
 * Created by lee on 4/15/17.
 */
public class OAuth2Servlet extends HttpServlet {

    //private UserInfo userInfo=null;
    private String stateOpenid;

    @Override
    protected void doGet(HttpServletRequest request
            , HttpServletResponse response)
            throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action=request.getParameter("action");
        //连接数据库.
        Connection connection = dbConnector.getConnector();
        MysqlUtil mysqlUtil = new MysqlUtil(connection);
        // 用户同意授权后，能获取到code
        /*if (action.equals("add")){

            String schoolId=request.getParameter("schoolid");
            mysqlUtil.updateUser(userInfo,1);
            request.setAttribute("UserSchoolId",userInfo);
        }else {*/
            String code = request.getParameter("code");
            if (!"authdeny".equals(code)) {
                WebUtil webUtil = new WebUtil();
                //通过code换取网页授权的access_token
                OAuth2AccessToken oAuth2AccessToken = webUtil.getOAuth2AccessToken(code);
                //access_token
                String access_token = oAuth2AccessToken.getAccess_token();
                //openid
                String openid = oAuth2AccessToken.getOpenid();
                //获取用户信息
                UserInfo userInfo = webUtil.getUserInfo(access_token, openid);
                UserInfo userInfo1=userInfo;
                //TODO ADD user's schoolid and school from usersetting.jsp
                /*String schoolid=request.getParameter("schoolid");
                String password=request.getParameter("password");
                String school=request.getParameter("school");
                userInfo1.setSchoolid(schoolid);
                userInfo1.setPassword(password);
                userInfo1.setSchool(school);
                */
                //若数据库中没有此openid
                if (!mysqlUtil.isUserInSql(openid,OPRNID)) {
                    try {
                        mysqlUtil.addUser(userInfo1);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
                request.setAttribute("UserInfo", userInfo1);
            }

            /*if (action.equals("deleteUser")) {

            }

        }*/
        request.getRequestDispatcher("message.jsp").forward(request,response);
    }
}
