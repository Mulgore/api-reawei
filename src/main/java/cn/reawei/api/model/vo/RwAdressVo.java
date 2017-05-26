package cn.reawei.api.model.vo;

import java.io.Serializable;

/**
 * Created by xingwu on 2017/5/26.
 */
public class RwAdressVo implements Serializable{

    private static final long serialVersionUID = -3581890585079985746L;

    private Integer status;

    private Long memberId;

    private String mobile;

    private String address;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
