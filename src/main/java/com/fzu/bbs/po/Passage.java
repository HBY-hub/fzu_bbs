package com.fzu.bbs.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_passage")
public class Passage {
    private Integer id;
    private String title;
    private String description;
    private Date createTime;
    private Date updateTime;
    private Integer readNum;
    private String content;
    private String userName;
    private Integer likeNum;
}
