package services;

import com.fzu.bbs.mapper.MessageRecordMapper;
import com.fzu.bbs.po.MessageRecord;
import com.fzu.bbs.services.MessageRecordServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {com.fzu.bbs.BbsApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageRecordTest {

    @Autowired
    MessageRecordServices messageRecordServices;

    @Test
    public void test1(){
        messageRecordServices.clearMessageRecord(3,1);

        System.out.println(messageRecordServices.getMessageRecord(3, 1));
    }
}
