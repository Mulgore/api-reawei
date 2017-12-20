package cn.reawei.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 照片
 */
@Data
public class RwPhotoInfo implements Serializable {

    private static final long serialVersionUID = -2307364508217301467L;

    private Long id;

    private String imgUrl;

    private Integer status;

    private String title;

    private String author;

    private String comment;


}