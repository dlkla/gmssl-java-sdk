package org.gmssl;

import java.nio.charset.StandardCharsets;

public class Sm2 {

    /**类初始化gmssl对象，调用jni接口*/
    private static final GmSSL gmssl = new GmSSL();

    /**默认对称加密算法*/
    private static final String DEFAULT_SYMMETRIC_ALG = "sms4-cbc";

    /**sm2加密算法*/
    private static final String SM2_ENCRYPT_ALG = "sm2encrypt-with-sm3";

    /**sm2签名算法*/
    private static final String SM2_SIGN_ALG = "sm2sign";


    /**
     * 产生PEM格式私钥
     * @param algor String 对称算法
     * @param passPhrase String 密钥,可以为null
     * @return String
     */
    public static String generatePEMPriKey(String algor, String passPhrase) {
        if (algor == null) {
            algor = DEFAULT_SYMMETRIC_ALG;
        }
        String tempPriKey = gmssl.generatePEMPriKey(algor, passPhrase);
        // passPhrase = null时, 最后部分有字节乱码
        String pemPrikey = tempPriKey.substring(0,tempPriKey.lastIndexOf("-----") + 5) + "\n";
        return pemPrikey;
    }

    /**
     * 产生PEM格式公钥
     * @param algor String 对称算法
     * @param passPhrase String 密钥,可以为null
     * @return String
     */
    public static String getPEMPubKey(String algor, String passPhrase) {
        if (algor == null) {
            algor = DEFAULT_SYMMETRIC_ALG;
        }
        String tempPubKey =  gmssl.getPEMPubKey(algor, passPhrase);
        // passPhrase = null时, 最后部分有字节乱码
        String pemPubKey = tempPubKey.substring(0,tempPubKey.lastIndexOf("-----") + 5) + "\n";
        return pemPubKey;
    }

    /**
     * 将私钥PEM转成DER二进制格式
     * @param pemPriKey String PEM格式私钥
     * @param passPhrase String 密钥，可以为null
     * @return byte[]
     */
    private static byte[] transPriPemToByteArr(String pemPriKey, String passPhrase) {
        return gmssl.transPriPemToByteArr(pemPriKey, passPhrase);
    }

    /**
     * 将公钥PEM转成DER二进制格式
     * @param pemPubKey String PEM格式公钥
     * @return byte[]
     */
    private static byte[] transPubPemToByteArr(String pemPubKey) {
        return gmssl.transPubPemToByteArr(pemPubKey);
    }

    /**
     * sm2公钥加密
     * @param data byte[] 待加密内容
     * @param pemPubKey String PEM格式公钥
     * @return byte[] 加密后字节数组
     */
    public static byte[] publicKeyEncrypt(byte[] data, String pemPubKey) {
        byte[] pubKeyByteArr = transPubPemToByteArr(pemPubKey);
        return gmssl.publicKeyEncrypt(SM2_ENCRYPT_ALG, data, pubKeyByteArr);
    }

    /**
     * sm2私钥解密
     * @param data byte[] 待解密内容
     * @param pemPriKey String PEM格式私钥
     * @param phrasePass String 密钥，可以为null
     * @return byte[]
     */
    public static byte[] publicKeyDecrypyt(byte[] data, String pemPriKey, String phrasePass) {
        byte[] priKeyByteArr = transPriPemToByteArr(pemPriKey, phrasePass);
        return gmssl.publicKeyDecrypt(SM2_ENCRYPT_ALG, data, priKeyByteArr);
    }

    /**
     * 签名
     * @param data byte[] 待签名数据
     * @param pemPriKey String PEM格式私钥
     * @param phrasePass String 密钥，可以为null
     * @return byte[]
     */
    public static byte[] sign(byte[] data, String pemPriKey,String phrasePass) {
        byte[] priKeyByteArr = gmssl.transPriPemToByteArr(pemPriKey, phrasePass);
        return gmssl.sign(SM2_SIGN_ALG, data, priKeyByteArr);
    }

    /**
     * 验签
     * @param data byte[] 待验签数据
     * @param sign byte[] 签名结果
     * @param pemPubKey String PEM格式公钥
     * @return int 1: 验签成功 else: 验签失败
     */
    public static int verify(byte[] data, byte[] sign, String pemPubKey) {

        int result = 0;
        try {
            byte[] pubKeyByteArr = gmssl.transPubPemToByteArr(pemPubKey);
            result = gmssl.verify(SM2_SIGN_ALG, data, sign, pubKeyByteArr);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {

        String passPhrase = "123456";
        // 产生私钥
        String pemPriKey = Sm2.generatePEMPriKey(DEFAULT_SYMMETRIC_ALG, passPhrase);
        System.out.println("sm2私钥:\n" + pemPriKey);

        // 根据私钥获取公钥
        String pemPubKey = Sm2.getPEMPubKey(pemPriKey, passPhrase);
        System.out.println("sm2公钥:\n" + pemPubKey);

        // 私钥签名；公钥验签
        byte[] dgst = Sm3.digest("abc".getBytes());
        byte[] sig = Sm2.sign(dgst, pemPriKey, passPhrase);
        int vret = Sm2.verify(dgst, sig, pemPubKey);
        System.out.println("Verification result = " + vret);

        // 公钥加密
        String msg = "gmssl";
        byte[] data = msg.getBytes(StandardCharsets.UTF_8);
        byte[] encrypt = Sm2.publicKeyEncrypt(data, pemPubKey);

        // 私钥解密
        byte[] decrypyt = Sm2.publicKeyDecrypyt(encrypt, pemPriKey, passPhrase);
        if (!msg.equals(new String(decrypyt,StandardCharsets.UTF_8))) {
            System.out.println("\nSM2 encryption/decryption failure");
        } else {
            System.out.println("\nSM2 encryption/decryption success");
        }
    }
}
