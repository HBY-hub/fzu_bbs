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

    @Override
    public List<MessageRecord> getMessageRecord(Integer fromUserId, Integer toUserId) {
        QueryWrapper<MessageRecord> wrapper = new QueryWrapper<>();
        wrapper
                .eq("from_user_id",fromUserId)
                .eq("to_user_id",toUserId)
                .or()
                .eq("from_user_id",toUserId)
                .eq("to_user_id",fromUserId)
                .orderByAsc("create_time");
        List<MessageRecord> messageRecordList = messageRecordMapper.selectList(wrapper);
        return messageRecordList;
    }

    @Override
    public List<MessageRecord> getUnreadMessage(Integer toUserId) {
        QueryWrapper<MessageRecord> wrapper = new QueryWrapper<>();
        wrapper
                .eq("to_user_id",toUserId)
                .eq("is_read",false)
                .orderByAsc("create_time");
        List<MessageRecord> messageRecordList = messageRecordMapper.selectList(wrapper);
        return messageRecordList;
    }

    @Override
    public boolean deleteMessageRecord(Integer fromUserId, Integer toUserId) {
        QueryWrapper<MessageRecord> wrapper = new QueryWrapper<>();
        wrapper
                .eq("from_user_id",fromUserId)
                .eq("to_user_id",toUserId);

        int clums = messageRecordMapper.delete(wrapper);
        if (clums < 0) return false;
        else return true;
    }
    @Override
    public boolean addMessageRecord(Integer fromUserId, Integer toUserId, String messageContext) {
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setFromUserId(fromUserId);
        messageRecord.setToUserId(toUserId);
        messageRecord.setMessage(messageContext);

        int clums = messageRecordMapper.insert(messageRecord);
        if (clums > 0) return true;
        else return false;
    }

    @Override
    public boolean addFileRecord(Integer fromUserId, Integer toUserId, String messageContext) {
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setFromUserId(fromUserId);
        messageRecord.setToUserId(toUserId);
        messageRecord.setMessage(messageContext);
        messageRecord.setIsFile(true);

        int clums = messageRecordMapper.insert(messageRecord);
        if (clums > 0) return true;
        else return false;
    }

    @Override
    public boolean deleteMessageRecord(Integer messageRecordId) {
        int clums = messageRecordMapper.deleteById(messageRecordId);
        if (clums > 0) return true;
        else return false;
    }
}
