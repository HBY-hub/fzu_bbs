package com.fzu.bbs.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.bbs.mapper.DetailMapper;
import com.fzu.bbs.po.Detail;
import com.fzu.bbs.services.DetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailServicesImpl implements DetailServices {
    @Autowired
    private DetailMapper detailMapper;
    @Override
    public String getDetailById(Integer id) {
        QueryWrapper<Detail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passage_id",id);
        Detail detail = detailMapper.selectOne(queryWrapper);
        return detail.getDetail();
    }

    @Override
    public Integer updateDetailById(Integer id, String s) {

        QueryWrapper<Detail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passage_id",id);
        Detail detail = detailMapper.selectOne(queryWrapper);
        detail.setDetail(s);
        return detailMapper.updateById(detail);
    }

    @Override
    public String insertPassage(Integer id) {
        Detail detail = new Detail();
        detail.setPassageId(id);
        detailMapper.insert(detail);
        return null;
    }
}
