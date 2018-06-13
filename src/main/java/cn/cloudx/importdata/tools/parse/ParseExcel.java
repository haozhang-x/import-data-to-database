package cn.cloudx.importdata.tools.parse;

import cn.cloudx.importdata.constant.ExcelTypeConstant;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author zhanghao
 * @date 2018/06/13
 */
public class ParseExcel {


    private static Workbook readExcel(InputStream file, String type) throws IOException {
        if (ExcelTypeConstant.XLS.equals(type)) {
            POIFSFileSystem fss = new POIFSFileSystem(file);
            return new HSSFWorkbook(fss);
        }
        if (ExcelTypeConstant.XLSX.equals(type)) {
            return new XSSFWorkbook(file);
        }

        return null;
    }

    @SuppressWarnings("all")
    public static String[][] loadImport(InputStream file, String type, int readStartRow, int iTotalOfColumns) throws IOException {
        //*****************读取execl表格数据*****************
        Workbook wb = readExcel(file, type);
        if (wb == null) {
            return null;
        }
        int iTotalOfSheetRows = 0;
        //遍历所有sheet，并将需要解析的sheet 索引添加到列表对象中。
        ArrayList<Integer> sheetIndexArrys = new ArrayList<>();
        for (int sheetIndex = 0; sheetIndex < 1; sheetIndex++) {
            Sheet sheet = wb.getSheetAt(sheetIndex);
            //当sheet中最大行不足3行，不再对此sheet进行解析
            if (sheet.getLastRowNum() > 1 && !wb.isSheetHidden(sheetIndex)) {
                iTotalOfSheetRows += (sheet.getLastRowNum() + 1 - readStartRow);
                sheetIndexArrys.add(sheetIndex);
            }
        }
        //将excel解析的所有数据，保存在字符串数组对象中。
        //1、初始化数组
        String[][] excelStr = new String[iTotalOfSheetRows][iTotalOfColumns];
        int rowIndex = 0;
        //2、解析excel，当sheet 整行数据为空时，停止对该sheet解析。
        for (Integer sheetIndex : sheetIndexArrys) {
            Sheet sheet = wb.getSheetAt(sheetIndex);
            //行数不到3 或 sheet隐藏的 跳过
            if (sheet.getLastRowNum() <= 1 || wb.isSheetHidden(sheetIndex)) {
                continue;
            }
            int iTotalOfRows = sheet.getLastRowNum() + 1 - readStartRow;
            //循环行
            for (int i = 0; i < iTotalOfRows; i++, rowIndex++) {
                Row row = sheet.getRow(readStartRow + i);
                if (row == null) {
                    break;
                }
                //行隐藏跳过
                if (row.getZeroHeight()) {
                    continue;
                }
                //循环列
                for (int j = 0; j < iTotalOfColumns; j++) {
                    // 实例单元格对象
                    Cell cell = row.getCell(j);
                    //将所有数据列解析成字符串，对数字类型（数组、时间）按指定格式解析
                    if (cell != null) {
                        // 设置为字符串类型
                        cell.setCellType(CellType.STRING);
                        excelStr[rowIndex][j] = cell.getStringCellValue();
                    }
                }
            }
        }
        return excelStr;

    }
}
