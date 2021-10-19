package com.fzu.bbs.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzu.bbs.po.MessageRecord;
import com.fzu.bbs.po.User;
import com.fzu.bbs.po.ws.ReceiveMessege;
import com.fzu.bbs.services.MessageRecordServices;
import com.fzu.bbs.utils.WebsocketUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
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

    @Autowired
    MessageRecordServices messageRecordServices;

    @OnOpen
    public void OnOpen(Session session, EndpointConfig endpointConfig){
        //获取session
        this.session = session;
        //获取httpsession
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        //得到User
        User user = (User) httpSession.getAttribute("user");
        //将此用户的所有未读信息发送给该用户
        List<MessageRecord> messageRecordList = messageRecordServices.getUnreadMessage(user.getId());
        for (MessageRecord record : messageRecordList) {
            //设置消息已读
            messageRecordServices.setMessageReaded(record.getId());
            //封装消息
            String res = WebsocketUtil.getResultMessege(record.getFromUserId(),record.getToUserId(),record.getMessage());
            try {
                //发送消息
                userList.get(record.getToUserId()).session.getBasicRemote().sendText(res);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //把自己加入map
        userList.put(user.getId(),this);
    }


    @OnMessage
    public void OnMessege(String receiveMessegeStr,Session session){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //得到receiveMessege
            ReceiveMessege receiveMessege = objectMapper.readValue(receiveMessegeStr,ReceiveMessege.class);

            if (userList.containsKey(receiveMessege.getToUserId())){//如果消息的接受者当前在线的话
                //将消息存进数据库,同时将这条消息设置为已读
                messageRecordServices.addMessageRecord(receiveMessege.getFromUserId(), receiveMessege.getToUserId(), receiveMessege.getMessege(),true);
                //封装消息
                String res = WebsocketUtil.getResultMessege(receiveMessege.getFromUserId(),receiveMessege.getToUserId(),receiveMessege.getMessege());
                //发送消息
                userList.get(receiveMessege.getToUserId()).session.getBasicRemote().sendText(res);
            }
            else {//不在线
                //将消息存进数据库，同时将这条消息设置为未读
                messageRecordServices.addMessageRecord(receiveMessege.getFromUserId(), receiveMessege.getToUserId(), receiveMessege.getMessege(),false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void Onclose(Session session) {
        User user = (User) httpSession.getAttribute("user");
        //不在线则移出userList
        userList.remove(user.getId());
    }

}
