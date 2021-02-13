package org.gmssl;

public class Sm3 {

    /*sm3摘要算法*/
    private static String SM3_DIGEST_ALG = "SM3";

    /*类初始化gmssl对象，调用jni接口*/
    private static final GmSSL gmssl = new GmSSL();


    public static byte[] digest(byte[] in) {
        return gmssl.digest(SM3_DIGEST_ALG,in);
    }


}
