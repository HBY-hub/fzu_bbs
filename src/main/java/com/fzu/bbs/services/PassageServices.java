package com.fzu.bbs.services;

import com.fzu.bbs.po.Passage;

import java.util.List;

public interface PassageServices {
    Integer addPassage(Passage passage);
    boolean deletePassageById(Integer id);
    List<Passage> getLatestPassage(Integer num,Integer page,String theme);
    List<Passage> getPassagesByName(String passageName);
    Passage getPassageById(Integer id);
}
