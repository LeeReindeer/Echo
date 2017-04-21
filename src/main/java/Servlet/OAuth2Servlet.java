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

/**
 * Created by lee on 4/15/17.
 */
public class OAuth2Servlet extends HttpServlet {

    private UserInfo userInfo=null;

    @Override
    protected void doGet(HttpServletRequest request
            , HttpServletResponse response)
            throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        String action=request.getParameter("action");
        if (!"authdeny".equals(code)) {
            WebUtil webUtil = new WebUtil();
            //通过code换取网页授权的access_token
            OAuth2AccessToken oAuth2AccessToken = webUtil.getOAuth2AccessToken(code);
            //access_token
            String access_token = oAuth2AccessToken.getAccess_token();
            //openid
            String openid = oAuth2AccessToken.getOpenid();
            //获取用户信息
            userInfo = webUtil.getUserInfo(access_token, openid);
            //写入数据库.
            try {

                Connection connection=dbConnector.getConnector();
                MysqlUtil mysqlUtil=new MysqlUtil(connection);
                if (!mysqlUtil.isUserInSql(openid)){
                    mysqlUtil.addUser(userInfo);
                }
            }catch (Exception e){
                e.printStackTrace();
            }


            request.setAttribute("UserInfo", userInfo);
        }
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request
            , HttpServletResponse response)
            throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

    }
}
