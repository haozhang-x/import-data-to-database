package cn.cloudx.importdata.entity.item;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "itemorginfoid", sequenceName = "itemorginfo", allocationSize = 1)
public class Itemorginfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemorginfoid")
    private Integer itemorginfoid;

    private String itemnum;
    private String itemsetid = "ITEMSET";
    private String orgid = "CNE";
    private String hazardid;
    private String toolrate;
    private String glaccount;
    private String controlacc;
    private String tax1Code;
    private String tax2Code;
    private String tax3Code;
    private String tax4Code;
    private String tax5Code;
    private String vendor;
    private String status = "活动";
    private Date statusdate = new Date();
    private String category = "库存";
    private String taxexempt;
    private String receipttolerance;

}
