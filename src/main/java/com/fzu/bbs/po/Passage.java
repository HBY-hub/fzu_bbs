package com.fzu.bbs.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private Date createTime;
    private Date updateTime;
    @TableField(exist = false)
    private List<Image> images;
    private Integer readNum;
    private String content;
    private String theme;
    private String userName;
    private Integer likeNum;
}
