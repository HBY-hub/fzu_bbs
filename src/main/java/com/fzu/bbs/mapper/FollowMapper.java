package com.fzu.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fzu.bbs.po.Follow;
import com.fzu.bbs.po.PassageTheme;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper extends BaseMapper<Follow> {
}
