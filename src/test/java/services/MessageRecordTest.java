package services;

import com.fzu.bbs.mapper.MessageRecordMapper;
import com.fzu.bbs.po.MessageRecord;
import com.fzu.bbs.services.MessageRecordServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Time;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {com.fzu.bbs.BbsApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageRecordTest {

    @Autowired
    MessageRecordServices messageRecordServices;

    @Test
    public void test1(){
//        messageRecordServices.addMessageRecord(1,2,"aa");
    }

    @Test
    public void test2() throws Exception{
        messageRecordServices.addMessageRecord(1,2,"111",true);
        Thread.currentThread().sleep(1);
        messageRecordServices.addMessageRecord(1,2,"222",true);
        Thread.currentThread().sleep(1);
        messageRecordServices.addMessageRecord(1,2,"333",true);
        Thread.currentThread().sleep(1);
        messageRecordServices.addMessageRecord(2,1,"444",true);
        Thread.currentThread().sleep(1);
        messageRecordServices.addMessageRecord(2,1,"555",true);
        Thread.currentThread().sleep(1);
        messageRecordServices.addMessageRecord(1,2,"666",true);
        Thread.currentThread().sleep(1);
        messageRecordServices.addMessageRecord(2,1,"777",true);
        Thread.currentThread().sleep(1);
        messageRecordServices.addMessageRecord(1,2,"888",true);
    }

    @Test
    public void test3(){
        List<MessageRecord> messageRecordList = messageRecordServices.getMessageRecord(1,2);

        for (MessageRecord record : messageRecordList) {
            System.out.println(record.getFromUserId()+" : "+record.getMessage());
        }
    }


}
