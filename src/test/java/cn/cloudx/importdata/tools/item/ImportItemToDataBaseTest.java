package cn.cloudx.importdata.tools.item;

import cn.cloudx.importdata.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
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

    @Autowired
    private ItemService itemService;

    //开始导入
    @Test
    @Commit
    public void startItemImport() {
        importItemToDataBase.startItemImport();
    }


    @Test
    @Commit
    public void startInvImport() {
        importItemToDataBase.startInvImport();
    }

    @Test
    @Commit
    public void deleteItemAll() {
        itemService.deleteItemAll();
    }


    @Test
    @Commit
    public void deleteInvAll() {
        itemService.deleteInvAll();
    }
}