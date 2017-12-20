package cn.reawei.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文档
 */
@Data
public class RwDocument implements Serializable {

    private static final long serialVersionUID = -2190090824159963932L;

    private Long id;

    private String title;

    private String author;

    private Integer clickRate;

    private Date createTime;

    private Date modifyTime;

    private String description;

    private String contents;

}