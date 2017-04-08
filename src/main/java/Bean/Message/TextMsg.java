package Bean.Message;

/**
 * Created by lee on 4/8/17.
 */
public class TextMsg extends BaseMsg{
    /**
     * 文本消息内容
     */
    private String Content;
    private final String MsgType="text";
    /**
     * 消息id，64位整型
     */
    private Long MsgId;


    public String getContent() {
        return Content;
    }
    public void setContent(String content) {
        this.Content = content;
    }
    public Long getMsgId() {
        return MsgId;
    }
    public void setMsgId(Long msgId) {
        this.MsgId = msgId;
    }
}
