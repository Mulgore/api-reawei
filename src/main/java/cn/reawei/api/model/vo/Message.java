package cn.reawei.api.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private long from;//发送者
    private String fromName;
    private long to;//接收者
    private String toName;
    private String text;//消息内容
    private Date date;//发送时间

    @Override
    public String toString() {
        return "Message{" +
                "from=" + from +
                ", fromName='" + fromName + '\'' +
                ", to=" + to +
                ", toName='" + toName + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
