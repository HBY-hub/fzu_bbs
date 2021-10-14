package com.fzu.bbs.po.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//发出的
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessege {
    Integer fromUserId;
    Integer toUserId;
    String messege;
}
