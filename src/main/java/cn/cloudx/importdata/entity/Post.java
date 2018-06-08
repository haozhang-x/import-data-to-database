package cn.cloudx.importdata.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "postid", sequenceName = "POSTIDSEQ", allocationSize = 1)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postid")
    private Integer postid;
    private String description;
    private String siteid;
    private String orgid;
    private Integer hasld = 0;
    private String remark;
    private String status = "活动";
    private Date statusdate;
    private String createperson;
    private Date createdate;
    private String postnum;
    private String personid;
    private String maxgroupPostnum;
    private String persongroupPostnum;
    private String persongroup;
    private String depnum;
    private String teamnum;
    private String departmentsid;
    private String changeperson;
    private Date changedate;

}
