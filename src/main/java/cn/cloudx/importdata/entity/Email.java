package cn.cloudx.importdata.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author zhang
 */
@Entity
@Data
@Table(schema = "MAXIMO")
@SequenceGenerator(name = "EMAILSEQ", sequenceName = "EMAILSEQ", allocationSize = 1)
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "EMAILSEQ")
    private Integer emailid;
    private String personid;
    private String emailaddress;
    private Integer isprimary = 1;
}
