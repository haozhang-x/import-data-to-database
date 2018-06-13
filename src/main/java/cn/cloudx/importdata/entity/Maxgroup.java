package cn.cloudx.importdata.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@SequenceGenerator(name = "maxgroupseq", sequenceName = "maxgroupseq", allocationSize = 1)
public class Maxgroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maxgroupseq")
    private Integer maxgroupid;
    private String groupname;
    private String description;
    private Integer passwordduration = 99999;
    private Integer passwordwarning = 5;
    private Integer independent=0;
    private Integer authallsites=0;
    private Integer authallgls=1;
    private Integer authallstorerooms=0;
    private Integer authlaborall=0;
    private Integer authlaborcrew=0;
    private Integer authlaborself=0;
    private Integer authlaborsuper=0;
    private String langcode="ZH";
    private Integer sctemplateid;
    private Integer authpersongroup=0;
    private Integer hasld=0;
    private Integer nullrepfac=0;
    private Integer authallrepfac=0;
    private Integer maxschedreport;
    private String dfltapp;
    private Integer adhoccreatelimit;
    private Integer reportstoplimit;
    private Integer sidenav=1;


}
