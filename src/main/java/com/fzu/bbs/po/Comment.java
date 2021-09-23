package com.fzu.bbs.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_comment")
public class Comment {
    private Long id;
    private Integer father;
    private Integer passage;
    private String user_name;
    private String content;
    private Date createTime;
}
