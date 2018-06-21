package cn.cloudx.importdata.constant;

import lombok.Getter;

/**
 * 电厂编码
 *
 * @author zhanghao
 * @date 2018/06/13
 */
@Deprecated
@Getter
public enum PowerPlantCodeConstant {
    ;
    private String code;
    private String description;


    PowerPlantCodeConstant(String code, String description) {
        this.code = code;
        this.description = description;
    }


}
