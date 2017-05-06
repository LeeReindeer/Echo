package ObjectC.Message;

/**
 * Created by lee on 4/8/17.
 */
public class BaseMsg {
    protected String ToUserName;
    protected String FromUserName;
    protected Long CreateTime;
    protected String MsgType;
    protected String Event;

    /*public BaseMsg(String msgType) {
        this.MsgType = msgType;
    }*/

    public String getToUserName() {
        return ToUserName;
    }
    public void setToUserName(String toUserName) {
        this.ToUserName = toUserName;
    }
    public String getFromUserName() {
        return FromUserName;
    }
    public void setFromUserName(String fromUserName) {
        this.FromUserName = fromUserName;
    }
    public Long getCreateTime() {
        return CreateTime;
    }
    public void setCreateTime(Long createTime) {
        this.CreateTime = createTime;
    }
    public String getMsgType() {
        return MsgType;
    }
    public void setMsgType(String MsgType) {
        this.MsgType=MsgType;
    }
    public String getEvent(){
        return Event;
    }

}
