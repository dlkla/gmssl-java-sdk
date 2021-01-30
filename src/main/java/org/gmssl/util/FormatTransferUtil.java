package org.gmssl.util;

/**
 * 格式转换工具类
 */
public class FormatTransferUtil {

    private static final String PARAMETER_ERROR = "参数错误";
    private static final String HEX_TEMPLATE = "0123456789ABCDEF";

    /**
     * 生成十六进制字符串，由String.format("%2X",byte)拼接得到;
     * @param byteArr byte[]
     * @return String
     */
    public static String byteArrToHexStr(byte[] byteArr) {
        if (byteArr == null || byteArr.length == 0) {
            throw new IllegalArgumentException(PARAMETER_ERROR);
        }
        StringBuilder builder = new StringBuilder();
        for (byte b : byteArr) {
            builder.append(String.format("%02X",b));
        }
        return builder.toString();
    }

    /**
     * 解析String.format("%2X",byte)格式的字符串为字节数组
     * @param hexStr String
     * @return byte[]
     */
    public static byte[] hexStrToByteArray(String hexStr) {
        if (hexStr == null || hexStr.length() == 0) {
            throw new IllegalArgumentException(PARAMETER_ERROR);
        }
        hexStr = hexStr.toUpperCase();
        byte[] arr = new byte[hexStr.length() / 2];
        for (int i = 0; i < (hexStr.length() / 2); i++) {
            byte b =(byte) (((HEX_TEMPLATE.indexOf(hexStr.charAt(2*i))) << 4) + HEX_TEMPLATE.indexOf(hexStr.charAt(2*i + 1)));
            arr[i] = b;
        }
        return arr;
    }
}
