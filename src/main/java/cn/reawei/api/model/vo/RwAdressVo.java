package cn.reawei.api.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xingwu on 2017/5/26.
 */
@Data
public class RwAdressVo implements Serializable{

    private static final long serialVersionUID = -3581890585079985746L;

    private Integer status;

    private Long memberId;

    private String mobile;

    private String address;

}
