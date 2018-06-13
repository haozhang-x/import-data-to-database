package cn.cloudx.importdata.entity.item;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@SequenceGenerator(name = "INVSTATUSSEQ", sequenceName = "INVSTATUSSEQ", allocationSize = 1)
public class Invstatus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVSTATUSSEQ")
    private Integer invstatusid;
    private String itemnum;
    private String itemsetid = "ITEMSET";
    /**
     * 库房
     */
    private String location = "W10001";

    /**
     * 地点
     */
    private String siteid;
    private String orgid = "CNE";
    private String status = "活动";
    private Date changedate = new Date();
    private String changeby = "MAXADMIN";
    private String memo;


}
