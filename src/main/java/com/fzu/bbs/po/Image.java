package com.fzu.bbs.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("tb_image")
public class Image {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer passage;
    private String url;
    private Date createTime;
}
