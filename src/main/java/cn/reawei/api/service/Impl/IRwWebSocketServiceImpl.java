package cn.reawei.api.service.Impl;

import cn.reawei.api.model.ResponseMessage;
import cn.reawei.api.service.IRwWebSocketService;
import cn.reawei.common.constants.Constant;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("rwWebSocketService")
public class IRwWebSocketServiceImpl implements IRwWebSocketService {


    @Resource
    private SimpMessagingTemplate template;

    /**
     * 广播
     * 发给所有在线用户
     *
     * @param msg 消息
     */
    public void sendMsg(ResponseMessage msg) {
        template.convertAndSend(Constant.PRODUCER_PATH, msg);
    }

    /**
     * 发送给指定用户
     *
     * @param users 标示集合
     * @param msg 消息
     */
    public void sendToUsers(List<String> users, ResponseMessage msg) {
        users.forEach(userName -> {
            template.convertAndSendToUser(userName, Constant.P2P_PUSH_PATH, msg);
        });
    }

    /**
     * 发送给单个用户
     *
     * @param userName 标实
     * @param msg 消息
     */
    public void sendToUser(String userName, ResponseMessage msg) {
        template.convertAndSendToUser(userName, Constant.P2P_PUSH_PATH, msg);
    }
}
