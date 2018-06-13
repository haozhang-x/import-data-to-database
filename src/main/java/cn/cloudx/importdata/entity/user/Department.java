package cn.cloudx.importdata.entity.user;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "department", sequenceName = "departmentidseq", allocationSize = 1)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department")
    private Integer departmentid;
    private String description;
    private String depnum;
    private String orgid = "CNE";
    private String siteid;
    private Integer hasld = 0;
    private Date createdate;
    private String createperson = "MAXADMIN";
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department")
    private String departmentnum;
    private String postnum;
    private String remark;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department")
    private String sid;
    private Date statusdate;
    private String teamnum;
    private String status = "活动";
    private String deptshortname;
    private String changeperson = "MAXADMIN";
    private Date changedate;
    private String type;
    private String department;
    private String parentnum;
    private String orgvalue;


}
