package com.fzu.bbs.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzu.bbs.po.ws.ResultMessege;

public class WebsocketUtil {


    public static String getResultMessege(Integer fromUserId,Integer toUserId,String messege){
        ResultMessege resultMessege = new ResultMessege();

        resultMessege.setToUserId(toUserId);
        resultMessege.setFromUserId(fromUserId);
        resultMessege.setMessage(messege);

        ObjectMapper objectMapper = new ObjectMapper();
        String repStr = null;
        try {
            repStr = objectMapper.writeValueAsString(resultMessege);
        }catch (JsonProcessingException e){
            e.clearLocation();
        }
        return repStr;
    }
}