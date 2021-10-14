package com.fzu.bbs.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzu.bbs.po.User;
import com.fzu.bbs.po.ws.ReceiveMessege;
import com.fzu.bbs.utils.WebsocketUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatEndpoint {
    //存放当前在线的用户
    public static Map<Integer,ChatEndpoint> userList = new ConcurrentHashMap<>();
    //当前用户的session
    public Session session;
    //当前用户的httpsession
    public HttpSession httpSession;

    @OnOpen
    public void OnOpen(Session session, EndpointConfig endpointConfig){
        //获取session
        this.session = session;
        //获取httpsession
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        //得到User
        User user = (User) httpSession.getAttribute("user");
        //把自己加入map
        userList.put(user.getId(),this);
    }


    @OnMessage
    public void OnMessege(String receiveMessegeStr,Session session){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //得到receiveMessege
            ReceiveMessege receiveMessege = objectMapper.readValue(receiveMessegeStr,ReceiveMessege.class);

            // 封装要发送的信息
            String res = WebsocketUtil.getResultMessege(receiveMessege.getFromUserId(),receiveMessege.getToUserId(),receiveMessege.getMessege());
            userList.get(receiveMessege.getToUserId()).session.getBasicRemote().sendText(res);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void Onclose(Session session) {
        User user = (User) httpSession.getAttribute("user");
        userList.remove(user.getId());
    }

}
