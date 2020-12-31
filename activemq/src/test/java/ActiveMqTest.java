import com.cn.activemq.ActivemqApplication;
import com.cn.activemq.producer.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yc
 * @date 2020/12/31
 */

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivemqApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActiveMqTest {

    @Autowired
    private Producer producer;

    @Test
    public void sendSimpleQueueMessage() {
        this.producer.sendMsg("6666666666666666666666666666666");
    }

    @Test
    public void sendSimpleTopicMessage() {
        this.producer.sendTopic("6666666666666666666666666666666");
    }

}
