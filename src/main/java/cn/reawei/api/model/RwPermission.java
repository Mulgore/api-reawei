package cn.reawei.api.model;

import lombok.Data;

@Data
public class RwPermission {
    private Long id;

    private Long pid;

    private String title;

    private Integer state;

    private String url;

    private String icon;

    private String permCode;

    private String description;
}