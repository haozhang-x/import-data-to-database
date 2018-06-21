package cn.cloudx.importdata.controller;

import cn.cloudx.importdata.tools.item.ImportItemToDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghao
 * @date 2018/06/20
 */
@RestController
public class IndexController {

    @Autowired
    private ImportItemToDataBase importItemToDataBase;

    @GetMapping("/")
    public String index() {
        importItemToDataBase.deleteAll();
        importItemToDataBase.startToolsItemImport();
        return "导入成功";
    }
}
