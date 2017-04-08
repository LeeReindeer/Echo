package Servlet;

import Bean.Message.TextMsg;
import Util.AccessToken;
import Util.MsgUtil;
import Util.SignUtil;
import Util.WechatUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by lee on 4/8/17.
 */
public class WechatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request
            , HttpServletResponse response)
            throws ServletException,IOException {
        String signature=request.getParameter("signature");
        String timestamp=request.getParameter("timestamp");
        String nonce=request.getParameter("nonce");
        String echostr=request.getParameter("echostr");
        PrintWriter writer=response.getWriter();
        if (SignUtil.checkSignsure(signature,timestamp,nonce)){
            //如果结果一致,返回随机数
            writer.print(echostr);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request
            , HttpServletResponse response)
        throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer=response.getWriter();
        MsgUtil msgUtil=new MsgUtil();
        try {
            //解析xml
            Map<String,String> map=msgUtil.parseXml(request);
            String ToUserName=map.get("ToUserName");
            String FromUserName=map.get("FromUserName");
            Long CreateTime= Long.valueOf(Integer.valueOf(map.get("CreateTime")));
            String Content=map.get("Content");
            String MsgType=map.get("MsgType");

            TextMsg textMsg=new TextMsg();
            textMsg.setFromUserName(ToUserName);
            textMsg.setToUserName(FromUserName);
            textMsg.setCreateTime(new Date().getTime());
            textMsg.setMsgType("text");
            //获取access_token
            WechatUtil wechatUtil=new WechatUtil();
            AccessToken token=wechatUtil.getAcessToken();
            String access_token=token.getAccess_token();
            int expires_in=token.getExpires_in();
            textMsg.setContent("Hello"+Content+"\n"+access_token+"\n"+expires_in);
            String sendMsg=msgUtil.messageToXml(textMsg);
            writer.print(sendMsg);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (writer!=null){
                writer.close();
            }
        }
    }
}
