package com.fzu.bbs.controller;

import com.alibaba.fastjson.JSON;
import com.fzu.bbs.po.MessageRecord;
import com.fzu.bbs.po.User;
import com.fzu.bbs.services.MessageRecordServices;
import com.fzu.bbs.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}
