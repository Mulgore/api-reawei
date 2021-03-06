package cn.reawei.api.common;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个类的作用是: 常量池
 * <p>
 * Created by xingwu on 2017/6/8.
 */
public class Constants {

    public static final int CODE_SUCCESS = 0;
    // 接口调用错误返回CODE码
    public static final int CODE_ERROR_APP_ID_NULL = 1001;
    public static final int CODE_ERROR_DESK_KEY_NULL = 1002;
    public static final int CODE_ERROR_APP_ID_AND_DESK_KEY_NULL = 1003;
    public static final int CODE_ERROR_APP_ID_NOT_PERM = 1004;
    public static final int CODE_ERROR_TOTAL_NUMBER_MAX = 1005;
    public static final int CODE_ERROR_APP_ID_NOT_ENABLED = 1006;

    // 接口ID常量池
    public static final String PHOTO_API_ID = "100078";
    public static final String DOCUMENT_API_ID = "100079";

    public static final int CODE_DOCUMENT_INFO_IS_NULL = 2000;
    public static final int CODE_DOCUMENT_UPDATE_IS_ERROR = 2001;
    public static final int CODE_DOCUMENT_SAVE_IS_ERROR = 2002;
    public static final int CODE_DOCUMENT_REMOVE_IS_ERROR = 2003;

    public static final int CODE_APP_MEMBER_MEMBER_ID_IS_ERROR = 3000;
    public static final int CODE_APP_MEMBER_REMOVE_IS_ERROR = 3001;
    public static final int CODE_APP_MEMBER_MEMBER_RESULT_IS_ERROR = 3002;

}
