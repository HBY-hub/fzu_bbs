package com.fzu.bbs.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CheckFace {
    static public boolean check(String s1,String s2){
        s1 = s1.substring(1,s1.length()-1);
        s2 = s2.substring(1,s2.length()-1);
        String[] split1 = s1.split(",");
        String[] split2 = s2.split(",");


        double ans = 0;
        for (int i = 0;i<512;i++){
            double a = Double.valueOf(split1[i]),b = Double.valueOf(split2[i]);
            ans+=(a-b)*(a-b);
        }
        return Math.sqrt(ans)<1;
    }
}
