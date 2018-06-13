package cn.cloudx.importdata.repository;

import cn.cloudx.importdata.entity.user.OrgStructure;
import cn.cloudx.importdata.repository.user.OrgStructureRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OrgStructureRepositoryTest {
    @Autowired
    private OrgStructureRepository repository;

    @Test
    public void findAll() {
        List<OrgStructure> orgStructureList = repository.findAll();
        Assert.assertNotNull(orgStructureList);
        log.info("orgStructureList={}",orgStructureList);
    }
}