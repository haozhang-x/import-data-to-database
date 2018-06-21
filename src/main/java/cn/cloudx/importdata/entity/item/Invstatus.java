package cn.cloudx.importdata.entity.item;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhang
 */
@Entity
@Data
public class Invstatus {

    @Id
    @SequenceGenerator(name = "invstatus", sequenceName = "invstatusseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "invstatus")
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
