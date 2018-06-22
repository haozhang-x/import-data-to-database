package cn.cloudx.importdata.tools.item;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhanghao
 * @date 2018/06/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportItemToDataBaseTest {

    @Autowired
    private ImportItemToDataBase importItemToDataBase;


    private String site1 = "CNE340322F01";
    private String site2 = "CNE532327G01";
    private String storeLoc1 = "W10001";
    private String storeLoc2 = "W10002";


    @Test
    public void startItemImport() {
        importItemToDataBase.startItemImport(site1, storeLoc1);
        importItemToDataBase.startItemImport(site2, storeLoc2);
    }

    @Test
    public void startToolsItemImport() {
        importItemToDataBase.startToolsItemImport(site1, storeLoc1);
        importItemToDataBase.startToolsItemImport(site2, storeLoc2);
    }
}