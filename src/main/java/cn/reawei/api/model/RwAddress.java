package cn.reawei.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员地址
 */
@Data
public class RwAddress implements Serializable {

    private static final long serialVersionUID = -4081388707865210991L;

    private Long id;

    private Integer status;

    private Long memberId;

    private String mobile;

    private String address;

    private String postcode;

    private String description;

    private Date createTime;

    private Date modifyTime;

}