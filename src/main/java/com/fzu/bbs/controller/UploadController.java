package com.fzu.bbs.controller;

import com.fzu.bbs.utils.AliyunOSSUtil;
import com.fzu.bbs.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

@RestController("/oss")
@Api(tags={"Upload"})
public class UploadController {
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @GetMapping("/ossUpload")
    public String getUpload(){
        return "post";
    }

    @PostMapping("/ossUpload")
    @ApiOperation("上传BlogPost")
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