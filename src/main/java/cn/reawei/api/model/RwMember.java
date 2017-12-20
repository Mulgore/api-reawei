package cn.reawei.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员
 */
@Data
public class RwMember implements Serializable {

    private static final long serialVersionUID = 8554212943432650227L;

    private Long id;

    private String name;

    private String nickname;

    private Integer status;

    private Integer age;

    private Integer sex;

    private Date birthday;

    private String idcard;

    private String description;

    private Date createTime;

    private Date modifyTime;
}