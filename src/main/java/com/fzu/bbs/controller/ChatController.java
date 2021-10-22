package com.fzu.bbs.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzu.bbs.po.MessageRecord;
import com.fzu.bbs.po.User;
import com.fzu.bbs.services.MessageRecordServices;
import com.fzu.bbs.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = {"实时聊天相关的接口"})
public class ChatController {

    @Autowired
    MessageRecordServices messageRecordServices;

    @GetMapping("/getMessageRecord")
    public R getMessageRecord(@RequestBody Map<String,Object> args){
        MessageRecord messageRecord = JSON.parseObject(JSON.toJSONString(args),MessageRecord.class);

        List<MessageRecord> messageRecordList = messageRecordServices.getMessageRecord(messageRecord.getFromUserId(),messageRecord.getToUserId());
        return R.ok(messageRecordList);
    }

    @GetMapping("/toChatroom")
    public String toChatroom(){
        return "chat";
    }

    @GetMapping("/getUser")
    @ResponseBody
    public String getUser() throws JsonProcessingException{
        System.out.println("get User!!");
        User user = (User) StpUtil.getSession().get("user");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }

}