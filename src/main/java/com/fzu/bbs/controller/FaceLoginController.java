package com.fzu.bbs.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.fzu.bbs.po.User;
import com.fzu.bbs.services.UserServices;
import com.fzu.bbs.utils.R;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class FaceLoginController {
    @Autowired
    private UserServices userServices;

    @Value("${face.url}")
    private String faceUrl;
    @Value("${face.location}")
    private String faceLoc;

    @PostMapping("/faceLogin")
    public R faceLogin(MultipartFile file) throws Exception {
        final String url = faceUrl+"/uploadfile";

        RestTemplate restTemplate = new RestTemplate();

        File image = multipartFileToFile(file, faceLoc);

        try {
            FileSystemResource resource = new FileSystemResource(image);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("file", resource);
            //使用HttpEntity构建需要传递的参数
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.add("Accept","application/json");
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param,headers);

            String body = restTemplate.postForEntity(url, httpEntity, String.class).getBody();
            body = body.substring(1,body.length()-1);
            User user = userServices.checkFace(body);
            if(user!=null){
                StpUtil.login(user.getId());
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                return R.ok(tokenInfo);
            }else{
                return R.fail();
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        } finally {
            image.delete();
        }
        return null;
    }


    @PostMapping("/faceUpload")
    public R faceUpload(MultipartFile file,Integer id) throws Exception {
        final String url = faceUrl+"/uploadfile";

        RestTemplate restTemplate = new RestTemplate();

        File image = multipartFileToFile(file, faceLoc);

        try {
            FileSystemResource resource = new FileSystemResource(image);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("file", resource);
            //使用HttpEntity构建需要传递的参数
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.add("Accept","application/json");
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param,headers);

            String body = restTemplate.postForEntity(url, httpEntity, String.class).getBody();
            body = body.substring(1,body.length()-1);
            userServices.updateUserFace(id,body);
            return R.ok(body,"ok");
        } catch (RestClientException e) {
            e.printStackTrace();
        } finally {
            image.delete();
        }
        return null;
    }
    @PostMapping("/faceCheck")
    public R faceCheck(MultipartFile file) throws Exception {
        final String url = faceUrl+"/uploadfile";

        RestTemplate restTemplate = new RestTemplate();

        File image = multipartFileToFile(file, faceLoc);

        try {
            FileSystemResource resource = new FileSystemResource(image);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("file", resource);
            //使用HttpEntity构建需要传递的参数
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.add("Accept","application/json");
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param,headers);

            String body = restTemplate.postForEntity(url, httpEntity, String.class).getBody();
            body = body.substring(1,body.length()-1);

            User user = userServices.checkFace(body);
            if(user!=null)
            return R.ok(body,"ok");
            else
                return R.fail();
        } catch (RestClientException e) {
            e.printStackTrace();
        } finally {
            image.delete();
        }
        return null;
    }

    public static File multipartFileToFile(MultipartFile file, String convertBasePath) {
        if (file != null) {
            File base = new File(convertBasePath);
            if (!base.exists()) {
                base.mkdirs();
            }
            String filePath = convertBasePath + file.getName() + String.valueOf((int) (100000 * Math.random())) + ".jpeg";

            File conventFile = new File(filePath);
            try {
                boolean isCreateSuccess = conventFile.createNewFile();
                if (isCreateSuccess) {
                    file.transferTo(conventFile);
                    return conventFile;
                }
            } catch (IOException e) {
            }
        }
        return null;
    }


}
