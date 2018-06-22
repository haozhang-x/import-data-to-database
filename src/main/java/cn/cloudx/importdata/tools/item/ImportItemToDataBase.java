package cn.cloudx.importdata.tools.item;

import cn.cloudx.importdata.constant.ExcelTypeConstant;
import cn.cloudx.importdata.constant.ItemTypeConstant;
import cn.cloudx.importdata.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static cn.cloudx.importdata.tools.parse.ParseExcel.loadImport;

/**
 * 导入项目信息到数据库中
 *
 * @author zhanghao
 * @date 2018/06/13
 */
@Component
@Slf4j
@SuppressWarnings("ALL")
public class ImportItemToDataBase {
    private final ItemService itemService;

    @Autowired
    public ImportItemToDataBase(ItemService itemService) {
        this.itemService = itemService;
    }


    /**
     * 导入库存项目
     *
     * @param siteId   位置编号
     * @param storeLoc 仓库区域
     */

    public void startItemImport(String siteId, String storeLoc) {
        try {
            //获取文件
            File file = ResourceUtils.getFile("classpath:" + "public/五河-物资-备品备件.xlsx");
            //文件转换为文件流
            FileInputStream fileInputStream = new FileInputStream(file);
            //将文件流转换为二维数组
            String[][] excels = loadImport(fileInputStream, ExcelTypeConstant.XLSX, 1, 10);
            if (!StringUtils.isEmpty(excels)) {
                for (String[] rows : excels) {
                    String itemNum = rows[0];
                    String description = rows[1];
                    String modelNum = rows[2];
                    String orderUnit = rows[3];
                    String issueUnit = rows[4];
                    if (StringUtils.hasText(itemNum)) {
                        itemService.importItem(itemNum, description, modelNum, orderUnit, issueUnit, storeLoc, siteId, ItemTypeConstant.ITEM);
                    } else {
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 工器具导入
     */

    public void startToolsItemImport(String siteId, String storeLoc) {
        try {
            File file = ResourceUtils.getFile("classpath:" + "public/五河-物资-工器具.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            String[][] excels = loadImport(fileInputStream, ExcelTypeConstant.XLSX, 1, 10);
            if (!StringUtils.isEmpty(excels)) {
                for (String[] rows : excels) {
                    String itemNum = rows[0];
                    String description = rows[1];
                    String modelNum = rows[2];
                    String orderUnit = rows[3];
                    String issueUnit = rows[4];
                    if (StringUtils.hasText(itemNum)) {
                        itemService.importItem(itemNum, description, modelNum, orderUnit, issueUnit, storeLoc, siteId, ItemTypeConstant.TOOL);
                    } else {
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
