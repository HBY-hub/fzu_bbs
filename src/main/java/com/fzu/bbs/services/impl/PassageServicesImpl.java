package com.fzu.bbs.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fzu.bbs.mapper.CommentMapper;
import com.fzu.bbs.mapper.PassageMapper;
import com.fzu.bbs.po.Comment;
import com.fzu.bbs.po.Passage;
import com.fzu.bbs.po.User;
import com.fzu.bbs.services.PassageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PassageServicesImpl implements PassageServices {
    @Autowired
    PassageMapper passageMapper;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public boolean addPassage(Passage passage) {
        Integer result = passageMapper.insert(passage);
        return result>0;
    }

    @Override
    public boolean deletePassageById(Integer id) {
        //删除其评论
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("passage",id);
        if(commentMapper!=null){
            commentMapper.delete(wrapper);
        }
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

    //根据名称模糊查询passage
    @Override
    public List<Passage> getPassagesByName(String passageName) {
        List<Passage> passageList = new ArrayList();
        QueryWrapper<Passage> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("title",passageName);
        passageList = passageMapper.selectList(queryWrapper);
        passageList.sort(new Comparator<Passage>() {
            @Override
            public int compare(Passage o1, Passage o2) {
                return o1.getUserName().length()-o2.getUserName().length();
            }
        });
        return passageList;
    }


}
