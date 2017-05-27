package cn.reawei.api.common.utils;

/**
 * <p>
 * AJAX 返回对象类
 * </p>
 *
 */
public class AjaxResult {

    /**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 失败或成功的提示信息
     */
    private String message;

    /**
     * 返回的数据
     */
    private Object data;


    public AjaxResult( boolean success, String message ) {
        this(success, message, null);
    }


    public AjaxResult( Object data ) {
        this(true, null, data);
    }


    public AjaxResult( boolean success, String message, Object data ) {
        this.success = success;
        this.message = message;
        this.data = data;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage( String message ) {
        this.message = message;
    }


    public Object getData() {
        return data;
    }


    public void setData( Object data ) {
        this.data = data;
    }


    public boolean isSuccess() {
        return success;
    }


    public void setSuccess( boolean success ) {
        this.success = success;
    }

}

