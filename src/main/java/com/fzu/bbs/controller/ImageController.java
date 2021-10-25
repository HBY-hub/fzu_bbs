package com.fzu.bbs.controller;

import com.fzu.bbs.po.Image;
import com.fzu.bbs.services.ImageServices;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ImageController {
    @Autowired
    ImageServices imageServices;

    @GetMapping("/images")
    public R GetImages(@RequestParam Map<String,Object> args){
        Integer passage = Integer.valueOf((String) args.get("id"));
        List<Image> images = imageServices.getPassageImages(passage);
        return R.ok(images);
    }
}
