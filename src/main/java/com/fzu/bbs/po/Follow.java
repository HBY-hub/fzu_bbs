package com.fzu.bbs.po;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("tb_follow")
public class Follow {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer toId;
    private Integer fromId;
    private String to;
    private String from;
}
