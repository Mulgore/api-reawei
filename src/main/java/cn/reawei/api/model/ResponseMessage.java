package cn.reawei.api.model;

import lombok.Data;

@Data
public class ResponseMessage {
    private String responseMessage;

    public ResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
