package com.fzu.bbs.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.bbs.mapper.ImageMapper;
import com.fzu.bbs.po.Image;
import com.fzu.bbs.services.ImageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServicesImpl implements ImageServices {
    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<Image> getPassageImages(Integer id) {
        QueryWrapper<Image>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passage",id);
        return imageMapper.selectList(queryWrapper);
    }

    @Override
    public void addImage(Integer id, String url) {
        Image image = new Image();
        image.setPassage(id);
        image.setUrl(url);
        imageMapper.insert(image);

        return;
    }
}
