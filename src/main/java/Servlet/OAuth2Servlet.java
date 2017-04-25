package Servlet;

import ObjectC.OAuth2AccessToken;
import ObjectC.UserInfo;
import Util.MysqlUtil;
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

    private static final String SplitField=",";

    @Override
    protected void doGet(HttpServletRequest request
            , HttpServletResponse response)
            throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //连接数据库.
        Connection connection = dbConnector.getConnector();
        MysqlUtil mysqlUtil = new MysqlUtil(connection);
        // 用户同意授权后，能获取到code
            String code = request.getParameter("code");
            //todo  this method failed,delete
        /*
            String state=request.getParameter("state");
            String []schoolField=state.split(SplitField);
            String schoolid=schoolField[0];
            String school=schoolField[1];
            */
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
                //TODO ADD user's schoolid and school from profile.jsp
                String schoolid = null;
                String school=null;
                String name=null;
                /*String schoolid=request.getParameter("schoolid");
                String school=request.getParameter("school");*/
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
                userInfo1.setSchoolid(schoolid);
                userInfo1.setSchool(school);
                userInfo1.setName(name);
                //若数据库中没有此openid
                if (!mysqlUtil.isUserInSql(userInfo1,OPRNID)&&mysqlUtil.isUserInSql(userInfo1,SCHOOLID)) {
                    try {
                        //UPDATE openid link to schoolId
                        mysqlUtil.updateUser(userInfo1,OPRNID);
                        request.setAttribute("UserInfo", userInfo1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            }
        request.getRequestDispatcher("message.jsp").forward(request,response);
    }
}
