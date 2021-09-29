package com.fzu.bbs.controller;

import com.fzu.bbs.utils.AliyunOSSUtil;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@RestController("/oss")
public class UploadController {
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @GetMapping("/ossUpload")
    public String getUpload(){
        return "post";
    }

    @PostMapping("/ossUpload")
    public R toUploadBlogPost(MultipartFile file) {
        try {

            if (null != file) {
                String filename = file.getOriginalFilename();
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    //上传到OSS
//                    return aliyunOSSUtil.uploadFile(newFile);
                    return R.ok(aliyunOSSUtil.uploadFile(newFile));

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return R.fail();
    }
}