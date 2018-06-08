package cn.cloudx.importdata.parse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author zhanghao
 * @date 2018/06/06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ImportDataToDataBaseTest {
    @Autowired
    private ImportDataToDataBase importDataToDataBase;

    @Test
    public void importUserToMaximo() throws IOException {
        importDataToDataBase.startUserImport();
    }

    @Test
    public void importDepartment() throws IOException {
        importDataToDataBase.startDepartmentImport();
    }


    @Test
    public void importPost() throws IOException {
        importDataToDataBase.startPostImport();
    }


    @Test
    public void updateUserInfo() throws IOException {
        importDataToDataBase.updateUserInfo();
    }
}