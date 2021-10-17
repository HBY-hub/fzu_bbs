package com.fzu.bbs.services.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fzu.bbs.mapper.MessageRecordMapper;
import com.fzu.bbs.po.MessageRecord;
import com.fzu.bbs.services.MessageRecordServices;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageRecordServicesImpl implements MessageRecordServices {
    @Autowired
    MessageRecordMapper messageRecordMapper;

    //获取一对用户的聊天记录
    @Override
    public MessageRecord getMessageRecord(Integer fromUserId, Integer toUserId) {

        QueryWrapper<MessageRecord> wrapper = new QueryWrapper<>();
        wrapper
                .eq("from_user_id",fromUserId)
                .eq("to_user_id",toUserId);

        List<MessageRecord> messageRecordList = messageRecordMapper.selectList(wrapper);

        //当前无此聊天记录就创建一个
        if (messageRecordList==null || messageRecordList.size()==0){
            createMessageRecord(fromUserId, toUserId);
            return new MessageRecord(fromUserId,toUserId,"",null,null);
        }

        return messageRecordList.get(0);
    }

    //清除一个聊天记录
    @Override
    public boolean clearMessageRecord(Integer fromUserId, Integer toUserId) {
        QueryWrapper<MessageRecord> wrapper = new QueryWrapper<>();
        wrapper
                .eq("from_user_id",fromUserId)
                .eq("to_user_id",toUserId);
        MessageRecord messageRecord = getMessageRecord(fromUserId,toUserId);
        messageRecord.setMessage("");
        updateMessageRecord(messageRecord);
        return true;
    }

    //用MessageRecord对象更新一个聊天记录
    @Override
    public boolean updateMessageRecord(MessageRecord messageRecord) {
        System.out.println(messageRecord);
        MessageRecord messageRecord1 = getMessageRecord(messageRecord.getFromUserId(),messageRecord.getToUserId());

        UpdateWrapper<MessageRecord> wrapper1 = new UpdateWrapper<>();
        wrapper1.eq("from_user_id",messageRecord.getFromUserId())
                .eq("to_user_id",messageRecord.getToUserId());
        messageRecordMapper.update(messageRecord,wrapper1);
        return true;
    }

    //为一对 fromUserId->toUserId 创建空的聊天记录
    @Override
    public boolean createMessageRecord(Integer fromUserId, Integer toUserId) {
        QueryWrapper<MessageRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("from_user_id",fromUserId)
                .eq("to_User_id",toUserId);
        List<MessageRecord> messageRecordList = messageRecordMapper.selectList(wrapper);
        if (messageRecordList==null || messageRecordList.size()==0) {
            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setFromUserId(fromUserId);
            messageRecord.setToUserId(toUserId);
            messageRecord.setMessage("");
            messageRecordMapper.insert(messageRecord);
        }
        return true;
    }

    @Override
    public boolean addMessageRecord(Integer fromUserId, Integer toUserId, String addMessage) {
        MessageRecord messageRecord = getMessageRecord(fromUserId,toUserId);
        messageRecord.setMessage(messageRecord.getMessage()+addMessage);
        updateMessageRecord(messageRecord);
        return true;
    }
}
