package cn.reawei.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户与接口关联权限
 */
@Data
public class RwAppMember implements Serializable {

    private static final long serialVersionUID = 2993191498407201315L;

    private Long id;

    private Long memberId;

    private Long apiId;

    private Integer level;

    private Integer numberTotal;

    private Date createTime;

    private Date lastTime;

    private Integer status;

    private String privateKey;

    private String publicKey;
}