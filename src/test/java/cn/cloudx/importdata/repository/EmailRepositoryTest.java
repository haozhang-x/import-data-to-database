package cn.cloudx.importdata.repository;

import cn.cloudx.importdata.entity.Email;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zhanghao
 * @date 2018/06/05
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailRepositoryTest {
    @Autowired
    private EmailRepository repository;


    @Test
    public void findAll() {
        List<Email> all = repository.findAll();
        Assert.assertNotNull(all);
    }
}