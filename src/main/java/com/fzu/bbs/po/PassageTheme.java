package com.fzu.bbs.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_passage_theme")
public class PassageTheme {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer passageId;
    private Integer themeId;
    private String passage;
    private String theme;
}
