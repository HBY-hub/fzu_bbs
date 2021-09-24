package com.fzu.bbs.services;

import com.fzu.bbs.po.Passage;

public interface PassageServices {
    boolean addPassage(Passage passage);
    boolean deletePassageById(int id);
    Passage getLatestPassage();
}
