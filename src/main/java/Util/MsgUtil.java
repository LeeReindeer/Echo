package Util;

import Bean.Message.BaseMsg;
import Bean.Message.TextMsg;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lee on 3/29/17.
 */
public class MsgUtil {

    public static final String MESSAGE_EVENT="event";
    public static final String EVENT_SUB="subscribe";
    public static final String EVENT_UNSUB="unsubscribe";
    public static final String EVENT_CLICK="click";
    public static final String EVENT_VIEW="view";
    public static final String MESSAGE_TEXT="text";
    public static final String MESSAGE_IMAGE="image";
    public static final String MESSAGE_VIDEO="video";
    public static final String MESSAGE_LINK="link";
    public static final String MESSAGE_LOCATION="location";
    /**
     * 解析微信发来的请求（XML）
     *
     * @return
     * @throws Exception
     */
    public Map<String, String> parseXml(HttpServletRequest request) throws IOException, DocumentException {

        InputStream inputStream=request.getInputStream();
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        @SuppressWarnings("unchecked")
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        // 显式释放资源
        inputStream.close();
        return map;
    }

    /**
     * 消息对象转换成xml
     *
     * @return
     */
    public String messageToXml(BaseMsg msg) {
        return xstream.toXML(msg);
    }



    private XStream xstream;
    {
        xstream = new XStream(new XppDriver() {
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 对所有xml节点的转换都增加CDATA标记
                    boolean cdata = true;

                    public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
                        super.startNode(name, clazz);
                    }

                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
        xstream.alias("xml", BaseMsg.class);
        xstream.alias("xml", TextMsg.class);
    }

    public String initalMessage(String ToUserName,String FromUserName,String Content){
        TextMsg textMsg=new TextMsg();
        textMsg.setContent(Content);
        textMsg.setFromUserName(ToUserName);
        textMsg.setToUserName(FromUserName);
        textMsg.setMsgType(MESSAGE_TEXT);
        textMsg.setCreateTime(new Date().getTime());
        String xmlmsg=messageToXml(textMsg);
        return xmlmsg;
    }
}