package Servlet;

import Util.OAuth2AccessToken;
import Util.UserInfo;
import Util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lee on 4/15/17.
 */
public class OAuth2Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request
            , HttpServletResponse response)
            throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 用户同意授权后，能获取到code
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

            request.setAttribute("UserInfo", userInfo);
        }
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

}
