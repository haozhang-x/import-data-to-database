package cn.cloudx.importdata.entity.item;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "itemstruct", sequenceName = "itemstructseq", allocationSize = 1)
public class Itemstruct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemstruct")
    private Integer itemstructid;
    private String itemnum;
    private Integer instance = 0;
    private String parent;
    private String parinst;
    private String itemid = itemnum;
    private Integer quantity = 1;
    private String remark;
    private String ias1;
    private String ias2;
    private String ias3;
    private Date ias4;
    private String ias5;
    private String itemsetid = "ITEMSET";
    private String langcode = "ZH";
    private Integer hasld = 0;
}
