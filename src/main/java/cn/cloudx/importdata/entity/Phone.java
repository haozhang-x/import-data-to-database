package cn.cloudx.importdata.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author zhang
 */
@Entity
@Data
@SequenceGenerator(name = "PHONESEQ", sequenceName = "PHONESEQ", allocationSize = 1)
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PHONESEQ")
    private Integer phoneid;
    private String personid;
    private String phonenum;
    private Integer isprimary = 1;
}
