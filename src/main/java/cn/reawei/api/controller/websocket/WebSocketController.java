package cn.reawei.api.controller.websocket;

import cn.reawei.api.model.RequestMessage;
import cn.reawei.api.model.ResponseMessage;
import cn.reawei.api.service.IRwWebSocketService;
import cn.reawei.common.constants.Constant;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebSocketController {

    @Resource
    private IRwWebSocketService rwWebSocketService;

    @MessageMapping(value = Constant.FORE_TO_SERVER_PATH)//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
    @SendTo(value = Constant.PRODUCER_PATH)//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    public ResponseMessage say(RequestMessage message) throws Exception {
        List<String> users = new ArrayList();
        users.add("d892bf12bf7d11e793b69c5c8e6f60fb");//此处写死只是为了方便测试,此值需要对应页面中订阅个人消息的userId
        rwWebSocketService.sendToUsers(users, new ResponseMessage("admin hello"));

        return new ResponseMessage("Welcome, " + message.getName() + "!");
    }
}
