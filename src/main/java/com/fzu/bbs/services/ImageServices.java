package com.fzu.bbs.services;

import com.fzu.bbs.po.Image;

import java.util.List;

public interface ImageServices {
    List<Image> getPassageImages(Integer id);
    void addImage(Integer id,String url);
}
