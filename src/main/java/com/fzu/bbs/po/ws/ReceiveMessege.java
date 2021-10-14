package com.fzu.bbs.po.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//收到的
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveMessege {
    Integer fromUserId;
    Integer toUserId;
    String messege;
}
