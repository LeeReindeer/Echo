package Servlet;

import ObjectC.AccessToken;
import ObjectC.UserInfo;
import Util.MysqlUtil;
import Util.PsqlUtil;
import Util.WechatUtil;
import Util.dbConnector;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lee on 5/5/17.
 */
public class SendMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String content=req.getParameter("content");
        String num=req.getParameter("num");
        WechatUtil wechatUtil=new WechatUtil();
        AccessToken token=wechatUtil.getAcessToken();
        String access_token=token.getAccess_token();
        Connection pconnection= dbConnector.getPConnector();
        PsqlUtil psqlUtil=new PsqlUtil(pconnection);
        Connection connection=dbConnector.getConnector();
        MysqlUtil mysqlUtil=new MysqlUtil(connection);
        String schoolid="";
        String name="";
        String school="";
        UserInfo user=new UserInfo();
        Cookie[]cookies=null;
        cookies=req.getCookies();
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
        //todo 网页选择要发送的学生的学号->openid
        user.setSchool(school);
        user.setName(name);
        user.setSchoolid(schoolid);
        int sum=5;
        //for (int i = 0; i < sum; i++) {
            ResultSet rs=mysqlUtil.checkResult(user);
            String openid="";
            try {
                openid = rs.getString("openid");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String modmsg= JSONObject.fromObject(wechatUtil.initModMsg(content,openid)).toString();
            //发送消息
            int errcode1=(wechatUtil.sendModMessage(modmsg,access_token));
            if (errcode1==0){
                System.out.println("发送模板信息成功");
                System.out.println(modmsg);
            }else {
                System.out.println("发送模板信息失败"+errcode1);
            }
        //}


        resp.sendRedirect("sendmsg.jsp");
    }
}
