package com.fzu.bbs.controller;

import com.fzu.bbs.services.DetailServices;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DetailController {
    @Autowired
    DetailServices detailServices;

    @PostMapping("/updateDetail")
    @ResponseBody
    public R updateDetail(@RequestBody Map<String,Object>args){
        Integer id = (Integer)  args.get("id");
        String detail = (String)  args.get("detail");
        detailServices.updateDetailById(id,detail);
        return R.ok();
    }
    @PostMapping("/getDetail")
    @ResponseBody
    public R getDetail(@RequestBody Map<String,Object> args){
        Integer id = (Integer) args.get("id");
        return R.ok(detailServices.getDetailById(id));
    }
}
