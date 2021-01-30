package org.gmssl;
import org.gmssl.util.FormatTransferUtil;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashSet;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.gmssl.util.FormatTransferUtil.byteArrToHexStr;

public class Sm4 {

	private static final String[] CIPHER_VALUES = { "SMS4", "SMS4-CBC","SMS4-CFB",
			"SMS4-CFB1", "SMS4-CFB8", "SMS4-CTR", "SMS4-OFB" };

	private static final LinkedHashSet<String> CIPHERS_SUPPORTED = new LinkedHashSet<>(Arrays.asList(CIPHER_VALUES));

	private static final String UN_SUPPORTED_CIPHER = "输入的cipher不支持";

	private static final String PARAMETER_ERROR = "参数错误";

	private static final GmSSL gmssl = new GmSSL();


	/**
	 * 生成指定长度的随机数字节数组
	 * @param length int
	 * @return byte[]
	 */
	public static byte[] generateRandom (int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("length应该大于0");
		}
		return gmssl.generateRandom(length);
	}

	/**
	 * 通过随机数生成十六进制(%2X)格式的初始化向量IV字符串,长度由cipher决定
	 * @param cipher String
	 * @return String
	 */
	public static String generateIVHexStr (String cipher) {
		byte[] bytes = generateIV(cipher);
		return byteArrToHexStr(bytes);
	}

	/**
	 * 通过随机数生成十六进制(%2X)格式的key字符串,长度由cipher决定
	 * @param cipher String
	 * @return String
	 */
	public static String generateKeyHexStr (String cipher) {
		byte[] bytes = generateKey(cipher);
		return byteArrToHexStr(bytes);
	}

	/**
	 * 对称加密
	 * @param cipher String
	 * @param in byte[]
	 * @param keyHexStr String
	 * @param ivHexStr String
	 * @return byte[]
	 */
	public static byte[] symmetricEncrypt(String cipher, byte[] in, String keyHexStr, String ivHexStr) {

		if (keyHexStr == null || keyHexStr.length() == 0) {
			throw new IllegalArgumentException(PARAMETER_ERROR);
		}
		if (ivHexStr == null || ivHexStr.length() == 0) {
			throw new IllegalArgumentException(PARAMETER_ERROR);
		}

		byte[] key = FormatTransferUtil.hexStrToByteArray(keyHexStr);
		byte[] iv = FormatTransferUtil.hexStrToByteArray(ivHexStr);
		return symmetricEncrypt(cipher, in, key, iv);
	}


	/**
	 * 对称解密
	 * @param cipher String
	 * @param in byte[]
	 * @param keyHexStr String
	 * @param ivHexStr String
	 * @return byte[]
	 */
	public static byte[] symmetricDecrypt(String cipher, byte[] in, String keyHexStr, String ivHexStr) {

		if (keyHexStr == null || keyHexStr.length() == 0) {
			throw new IllegalArgumentException(PARAMETER_ERROR);
		}
		if (ivHexStr == null || ivHexStr.length() == 0) {
			throw new IllegalArgumentException(PARAMETER_ERROR);
		}

		byte[] key = FormatTransferUtil.hexStrToByteArray(keyHexStr);
		byte[] iv = FormatTransferUtil.hexStrToByteArray(ivHexStr);
		return symmetricDecrypt(cipher, in, key, iv);
	}


	/**
	 * 通过随机数生成初始化向量IV字节数组,长度由cipher决定
	 * @param cipher String
	 * @return byte[]
	 */
	private static byte[] generateIV (String cipher) {
		if (cipher == null || !CIPHERS_SUPPORTED.contains(cipher.toUpperCase())) {
			throw new IllegalArgumentException(UN_SUPPORTED_CIPHER);
		}
		int cipherIVLength = gmssl.getCipherIVLength(cipher);
		return generateRandom(cipherIVLength);
	}

	/**
	 * 通过随机数生成key字节数组,长度由cipher决定
	 * @param cipher String
	 * @return byte[]
	 */
	private static byte[] generateKey (String cipher) {
		if (cipher == null || !CIPHERS_SUPPORTED.contains(cipher.toUpperCase())) {
			throw new IllegalArgumentException(UN_SUPPORTED_CIPHER);
		}
		int cipherKeyLength = gmssl.getCipherKeyLength(cipher);
		return generateRandom(cipherKeyLength);
	}


	/**
	 * 对称加密
	 * @param cipher String
	 * @param in byte[]
	 * @param key byte[]
	 * @param iv byte[]
	 * @return byte[]
	 */
	private static byte[] symmetricEncrypt(String cipher, byte[] in, byte[] key, byte[] iv) {
		if (cipher == null || ! CIPHERS_SUPPORTED.contains(cipher.toUpperCase())) {
			throw new IllegalArgumentException(UN_SUPPORTED_CIPHER);
		}
		if (key == null || key.length != gmssl.getCipherKeyLength(cipher)) {
			throw new IllegalArgumentException(PARAMETER_ERROR);
		}
		if (iv == null || iv.length != gmssl.getCipherIVLength(cipher)) {
			throw new IllegalArgumentException(PARAMETER_ERROR);
		}

		return gmssl.symmetricEncrypt(cipher, in, key, iv);
	}


	/**
	 * 对称解密
	 * @param cipher String
	 * @param in byte[]
	 * @param key byte[]
	 * @param iv byte[]
	 * @return byte[]
	 */
	private static byte[] symmetricDecrypt(String cipher, byte[] in, byte[] key, byte[] iv) {
		if (cipher == null || ! CIPHERS_SUPPORTED.contains(cipher.toUpperCase())) {
			throw new IllegalArgumentException(UN_SUPPORTED_CIPHER);
		}
		if (key == null || key.length != gmssl.getCipherKeyLength(cipher)) {
			throw new IllegalArgumentException(PARAMETER_ERROR);
		}
		if (iv == null || iv.length != gmssl.getCipherIVLength(cipher)) {
			throw new IllegalArgumentException(PARAMETER_ERROR);
		}

		return gmssl.symmetricDecrypt(cipher, in, key, iv);
	}

	/**
	 * http://gmssl.org/docs/sm4.html
	 * @param args
	 */
	public static void main(String[] args) {

		// 一：sdk支持的sm4模式测试

		String plaintext = "abcdefghijklmnopqrstuvwxyz";
		System.out.println("java sdk支持的sm4加密模式一共"+ CIPHERS_SUPPORTED.size() + "种");
		System.out.println(CIPHERS_SUPPORTED);
		System.out.println("测试数据的字节数:" + plaintext.getBytes(UTF_8).length);
		int j = 0;
		for (String cipher : CIPHERS_SUPPORTED) {

			System.out.println("-------------------------------------------");
			System.out.println(++j + ")算法" + cipher);
			// 生成密钥,iv
			String keyHexStr = generateKeyHexStr(cipher);
			System.out.println("keyHExStr:" + keyHexStr);
			String ivHexStr = generateIVHexStr(cipher);
			System.out.println("ivHexStr:" + ivHexStr );
			// 加密,解密
			long t3 = System.currentTimeMillis();
			byte[] cipherText = symmetricEncrypt(cipher, plaintext.getBytes(StandardCharsets.UTF_8), keyHexStr, ivHexStr);
			byte[] decryptByteArray = symmetricDecrypt(cipher, cipherText, keyHexStr, ivHexStr);
			String decryptStr = new String(decryptByteArray,UTF_8);
			if (decryptStr.equals(plaintext)) {
				System.out.println(cipher + "加解密成功");
			} else {
				System.out.println(cipher + "加解密失败");
			}
			long t4 = System.currentTimeMillis();
			System.out.println("完成一次加解密耗时" + (t4-t3) + "ms");
		}
		System.out.println("-------------------------------------------");
		System.out.println();

		// 二：sm4加解密性能测试

		String cipher = "sms4-cbc";
		System.out.println(cipher +  "加解密性能测试:");
		long t_start = System.currentTimeMillis();
		// 设置加解密次数
		int testNum = 1000;
		for (int i=0; i < testNum; i++) {
			// 密钥,iv，返给用户
			String keyHexStr = generateKeyHexStr(cipher);
			String ivHexStr = generateIVHexStr(cipher);

			byte[] cipherText = symmetricEncrypt(cipher, plaintext.getBytes(UTF_8), keyHexStr, ivHexStr);
			byte[] decryptByteArray = symmetricDecrypt(cipher, cipherText, keyHexStr, ivHexStr);
			String decryptStr = new String(decryptByteArray, UTF_8);
			if (!decryptStr.equals(plaintext)) {
				System.out.println("第"  + i + "次加解密失败");
			}
		}
		System.out.println("完成" + testNum + "次加解密耗时:" + (System.currentTimeMillis() - t_start) + "ms");

	}
}
