package com.fzu.bbs.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_message_record")
//储存两个人的聊天记录
public class MessageRecord {
    Integer fromUserId;
    Integer toUserId;
    String message;
    Date createTime;
    Date updateTime;
}