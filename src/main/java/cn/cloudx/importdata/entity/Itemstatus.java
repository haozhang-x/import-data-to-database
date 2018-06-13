package cn.cloudx.importdata.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@SequenceGenerator(name = "itemstatus",sequenceName = "itemstatus")
public class Itemstatus {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE,generator ="itemstatus" )
    private Integer itemstatusid;
    private String itemnum;
    private String itemsetid = "ITEMSET";
    private String status = "活动";
    private Date changedate = new Date();
    private String changeby = "MAXADMIN";
    private String memo;


}
