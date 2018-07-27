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
import java.text.DecimalFormat;

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

    public void startItemImport() {
        try {
            //获取文件
            File file = ResourceUtils.getFile("classpath:" + "public/五河物资台账.xlsx");
            //文件转换为文件流
            FileInputStream fileInputStream = new FileInputStream(file);
            //将文件流转换为二维数组
            String[][] excels = loadImport(fileInputStream, ExcelTypeConstant.XLSX, 1, 20);
            if (!StringUtils.isEmpty(excels)) {
                for (String[] rows : excels) {
                    //分类编号
                    String classStructureId = rows[10];
                    //分类编码
                    String classificationId = rows[0];

                    String siteId = rows[9];
                    String storeLoc = rows[8];


                    String itemNum;
                    String maxItemNum = itemService.findMaxItemNum(classStructureId);
                    if (StringUtils.hasText(maxItemNum)) {
                        long parseItemNum = Long.parseLong(maxItemNum);
                        itemNum = String.valueOf(++parseItemNum);
                    } else {
                        int defaultStartValue = 1;
                        DecimalFormat decimalFormat = new DecimalFormat("00000");
                        String format = decimalFormat.format(defaultStartValue);
                        itemNum = classificationId.concat(format);
                    }

                    String description = rows[1];
                    String modelNum = rows[2];
                    String orderUnit = rows[3];
                    if (orderUnit == null) {
                        orderUnit = "";
                    }
                    String issueUnit = rows[4];
                    if (issueUnit == null) {
                        issueUnit = "";
                    }
                    String itemType = "";
                    if ("工器具".equals(rows[6])) {
                        itemType = ItemTypeConstant.TOOL;
                    }
                    if ("备品备件".equals(rows[6])) {
                        itemType = ItemTypeConstant.ITEM;
                    }

                    if (StringUtils.hasText(itemNum)) {
                        itemService.importItem(itemNum, description, modelNum, orderUnit, issueUnit, storeLoc, siteId, itemType, classStructureId);
                    } else {
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startInvImport() {
        try {
            //获取文件
            File file = ResourceUtils.getFile("classpath:" + "public/五河物资台账.xlsx");
            //文件转换为文件流
            FileInputStream fileInputStream = new FileInputStream(file);
            //将文件流转换为二维数组
            String[][] excels = loadImport(fileInputStream, ExcelTypeConstant.XLSX, 1, 20);
            if (!StringUtils.isEmpty(excels)) {
                for (String[] rows : excels) {
                    //分类编号
                    String classStructureId = rows[10];
                    //分类编码
                    String classificationId = rows[0];

                    String siteId = rows[8];
                    String storeLoc = rows[7];


                    String itemNum;
                    String maxItemNum = itemService.findMaxItemNum(classStructureId);
                    if (StringUtils.hasText(maxItemNum)) {
                        long parseItemNum = Long.parseLong(maxItemNum);
                        itemNum = String.valueOf(++parseItemNum);
                    } else {
                        int defaultStartValue = 1;
                        DecimalFormat decimalFormat = new DecimalFormat("00000");
                        String format = decimalFormat.format(defaultStartValue);
                        itemNum = classificationId.concat(format);
                    }

                    String description = rows[1];
                    String modelNum = rows[2];
                    String orderUnit = rows[3];
                    if (orderUnit == null) {
                        orderUnit = "";
                    }
                    String issueUnit = rows[4];
                    if (issueUnit == null) {
                        issueUnit = "";
                    }
                    String itemType = "";
                    if ("工器具".equals(rows[6])) {
                        itemType = ItemTypeConstant.TOOL;
                    }
                    if ("备品备件".equals(rows[6])) {
                        itemType = ItemTypeConstant.ITEM;
                    }

                    if (StringUtils.hasText(itemNum)) {
                        itemService.importInv(itemNum, description, modelNum, orderUnit, issueUnit, storeLoc, siteId, itemType);
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
