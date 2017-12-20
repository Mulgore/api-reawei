package cn.reawei.api.service;

import cn.reawei.api.model.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRwWebSocketService {

    void sendMsg(ResponseMessage message);

    void sendToUsers(List<String> users, ResponseMessage message);

    void sendToUser(String userName, ResponseMessage message);
}
