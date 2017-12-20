package cn.reawei.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户，管理员
 */
@Data
public class RwUser implements Serializable {

    private static final long serialVersionUID = -5164162029956078702L;

    private Long id;

    private String loginName;

    private String password;

    private String email;

    private Short type;

    private Short status;

    private Date createTime;

    private Date lastTime;

}