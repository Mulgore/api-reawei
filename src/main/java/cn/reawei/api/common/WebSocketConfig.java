package cn.reawei.api.common;

import cn.reawei.common.constants.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //注册一个Stomp的节点（endpoint）,并指定使用SockJS协议。
        stompEndpointRegistry.addEndpoint(Constant.PATH).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //服务端发送消息给客户端的域,多个用逗号隔开
        registry.enableSimpleBroker(Constant.BROADCAST_PATH, Constant.P2P_PUSH_BASE_PATH);
        //定义一对一推送的时候前缀
        registry.setUserDestinationPrefix(Constant.P2P_PUSH_BASE_PATH);
        //定义webSocket前缀
        registry.setApplicationDestinationPrefixes(Constant.PATH_PERFIX);
    }
}
