package Servlet;

import Util.MsgUtil;
import Util.SignUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by lee on 4/8/17.
 */
public class WechatServlet extends HttpServlet {
    private static final String WELCOMEHINT="感谢你的关注"+"\n"+""+"Enjoy it!";
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
        String sendMsg=null;
        try {
            //解析xml
            Map<String,String> map=msgUtil.parseXml(request);
            String ToUserName=map.get("ToUserName");
            String FromUserName=map.get("FromUserName");
            Long CreateTime= Long.valueOf(Integer.valueOf(map.get("CreateTime")));
            String Content=map.get("Content");
            String MsgType=map.get("MsgType");

            /*TextMsg textMsg=new TextMsg();
            textMsg.setFromUserName(ToUserName);
            textMsg.setToUserName(FromUserName);
            textMsg.setCreateTime(new Date().getTime());
            textMsg.setMsgType("text");
            textMsg.setContent("hello"+Content);
            */
            if (msgUtil.MESSAGE_TEXT.equals(MsgType)){
                sendMsg=msgUtil.initalMessage(ToUserName,FromUserName,"Hello "+Content);
            }

            if (msgUtil.MESSAGE_EVENT.equals(MsgType)){
                String eventype=map.get("Event");
                if (eventype.equals(msgUtil.EVENT_SUB)){
                    sendMsg=msgUtil.initalMessage(ToUserName,FromUserName,WELCOMEHINT);
                }
                if (eventype.equals(msgUtil.EVENT_VIEW)){
                    sendMsg=msgUtil.initalMessage(ToUserName,FromUserName,"view");
                }
                if (eventype.equals(msgUtil.EVENT_CLICK)){
                    sendMsg=msgUtil.initalMessage(ToUserName,FromUserName,"click");
                }
            }
            //sendMsg=msgUtil.messageToXml(textMsg);
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
