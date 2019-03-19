import com.Gruuy.entity.User;
import com.Gruuy.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        //测试pagehelper
        PageHelper.startPage(1,1);
        List<User> userList=userService.getAllUser();
        //pageinfo对象封装了许多属性，贼几把好用就对了
        PageInfo<User> page=new PageInfo<User>(userList);
        System.out.println(page.getList().get(0).getId());
        System.out.println(page.getList().get(0).getUsername());
        System.out.println(page.getList().get(0).getPassword());
        System.out.println(page.getList().get(0).getLevel());
    }
    @org.junit.Test
    public void TestRedis(){
        Jedis jedis=jedisPool.getResource();
        List<String> list1=jedis.lrange("list1",0,-1);
        System.out.println(list1);
    }

}
