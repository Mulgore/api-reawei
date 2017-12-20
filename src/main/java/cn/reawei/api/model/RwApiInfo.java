package cn.reawei.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口信息
 */
@Data
public class RwApiInfo implements Serializable {

    private static final long serialVersionUID = -511041935220743666L;

    private Long id;

    private String name;

    private Integer status;

    private String apiUrl;

    private String desc;

}