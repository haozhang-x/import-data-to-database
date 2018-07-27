package cn.cloudx.importdata.tools.companies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhanghao
 * @date 2018/06/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ImportCompaniesTest {
    @Autowired
    private ImportCompanies importCompanies;

    @Test
    public void importCompanies() {
        importCompanies.importCompanies();
    }
}