package com.fzu.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fzu.bbs.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
