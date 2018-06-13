package cn.cloudx.importdata.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@SequenceGenerator(name = "INVSTATUSSEQ", sequenceName = "INVSTATUSSEQ", allocationSize = 1)
public class Invstatus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "INVSTATUSSEQ")
    private String invstatusid;
    private String itemnum;
    private String itemsetid = "ITEMSET";
    private String location;
    private String siteid;
    private String orgid = "CNE";
    private String status = "活动";
    private Date changedate;
    private String changeby = "MAXADMIN";
    private String memo;


}
