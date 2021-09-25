package com.fzu.bbs.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fzu.bbs.mapper.PassageMapper;
import com.fzu.bbs.po.Passage;
import com.fzu.bbs.services.PassageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassageServicesImpl implements PassageServices {
    @Autowired
    PassageMapper passageMapper;

    @Override
    public boolean addPassage(Passage passage) {
        Integer result = passageMapper.insert(passage);
        return result>0;
    }

    @Override
    public boolean deletePassageById(Integer id) {
        Integer result = passageMapper.deleteById(id) ;
        return result>0;
    }

    @Override
    public List<Passage> getLatestPassage(Integer number,Integer page) {
        QueryWrapper<Passage> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("update_time");
        IPage<Passage> passageIPage = new Page<>(page,number);
        List<Passage> passageList = passageMapper.selectPage(passageIPage, queryWrapper).getRecords();
        return passageList;
    }
}
