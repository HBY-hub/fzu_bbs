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
//储存两个人的一条聊天记录
//此处变更说明：原来是打算一个Message存所有聊天记录，现改成只存一条聊天记录
public class MessageRecord {
    private Integer id;
    private Integer fromUserId;
    private Integer toUserId;
    private Boolean isFile;
    private Boolean isRead;
    private String message;
    private Date createTime;
}