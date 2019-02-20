import com.Gruuy.entity.User;
import com.Gruuy.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class Test {
    @Resource
    private UserService userService;
    @Resource
    private JedisPool jedisPool;
    @org.junit.Test
    public void Test(){
        List<User> userList=userService.getAllUser();
        System.out.println(userList.toString());
    }
    @org.junit.Test
    public void TestRedis(){
        Jedis jedis=jedisPool.getResource();
        List<String> list1=jedis.lrange("list1",0,-1);
        System.out.println(list1);
    }

}
