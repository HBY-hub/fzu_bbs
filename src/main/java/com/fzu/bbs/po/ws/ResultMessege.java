package com.fzu.bbs.po.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//ċċşç
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessege {
    Integer fromUserId;
    Integer toUserId;
    String message;
}
