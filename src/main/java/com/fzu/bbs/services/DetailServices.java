package com.fzu.bbs.services;

public interface DetailServices {
    String getDetailById(Integer id);
    Integer updateDetailById(Integer id, String s);
    String insertPassage(Integer id);
}
