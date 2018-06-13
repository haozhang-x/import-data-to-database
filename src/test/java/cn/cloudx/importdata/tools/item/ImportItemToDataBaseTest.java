package cn.cloudx.importdata.tools.item;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhanghao
 * @date 2018/06/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportItemToDataBaseTest {

    @Autowired
    private ImportItemToDataBase importItemToDataBase;

    @Test
    public void startImport() {
        importItemToDataBase.startImport();
    }
}