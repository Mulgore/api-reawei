package cn.reawei.api.common.utils;

/**
 * Created by xingwu on 2017/6/9.
 */
import java.security.MessageDigest;

public class MD5Util {
    private static final char[] HEXD = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public MD5Util() {
    }

    public static final String encode(String s) {
        return encode(s, (String)null);
    }

    public static final String encode(String s, String encoding) {
        char[] hexDigits = HEXD;

        try {
            byte[] strTemp = encoding == null?s.getBytes():s.getBytes(encoding);
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var11) {
            return null;
        }
    }
}