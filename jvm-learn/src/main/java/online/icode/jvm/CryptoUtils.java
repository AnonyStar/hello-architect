package online.icode.jvm;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 本类用于3DES 加解密的工具
 *
 */
public class CryptoUtils {

	// Java 3DES 算法名称
	private static final String ALGORITHM_3DES = "DESede";

	/**
	 * 解密
	 * @param key
	 * @param src
	 * @return
	 * @throws Exception
	 * @return byte[]
	 */
	public static byte[] tripleDesDecryptBytes(String key, byte[] src) throws Exception {

		SecretKey secretKey = new SecretKeySpec(hex2Bytes(key), ALGORITHM_3DES);
		Cipher cipher = Cipher.getInstance(ALGORITHM_3DES);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return cipher.doFinal(src);
	}


	/**
	 * 加密
	 * @param src
	 * @return
	 * @throws Exception
	 * @return byte[]
	 */
	public static byte[] tripleDesEncryptBytes(String key, byte[] src) throws Exception {
		SecretKey secretKey = new SecretKeySpec(hex2Bytes(key), ALGORITHM_3DES);
		Cipher cipher = Cipher.getInstance(ALGORITHM_3DES);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return cipher.doFinal(src);
	}

	/**
	 * 将byte 类型转换成16进制
	 * @param b  byte类型的数据 
	 * @return String
	 */
	public static String byte2hex(byte[] b) {
		String stmp = "";
        StringBuffer sb = new StringBuffer();
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				sb.append("0").append(stmp);
			else
				sb.append(stmp);
		}
		return sb.toString().toUpperCase();
	}

	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try (FileInputStream fis = new FileInputStream(new File(filePath));
			 ByteArrayOutputStream bos = new ByteArrayOutputStream(1000)){
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {

		}
		return buffer;
	}

	public static byte[] hex2Bytes(String hex) {
		if ((hex.length() % 2) != 0) {
			String errMsg = "hex.length()=" + hex.length() + ", not an even number";
			throw new IllegalArgumentException(errMsg);
		}

		final byte[] result = new byte[hex.length() / 2];
		final char[] enc = hex.toCharArray();
		StringBuilder sb = new StringBuilder(2);
		for (int i = 0; i < enc.length; i += 2) {
			sb.delete(0, sb.length());
			sb.append(enc[i]).append(enc[i + 1]);
			result[i / 2] = (byte) Integer.parseInt(sb.toString(), 16);
		}
		return result;
	}

	

	public static String bytes2Hex(byte[] bytes, boolean upperCase) {
		if (bytes == null || bytes.length <= 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return upperCase ? sb.toString().toUpperCase() : sb.toString();
	}

	/**
	 * 加密文件
	 * @param skString
	 * @param srcFilePath
	 * @param desFilePath
	 * @throws Exception
	 */
	public static void tripleDesEncryptFile(String skString,String srcFilePath,String desFilePath) throws Exception {
		FileOutputStream fos = null;
		try {
			byte[] fileBytes = null;
			fileBytes = getBytes(srcFilePath);
			byte[] encryptBytes = tripleDesEncryptBytes(skString, fileBytes);
			fos = new FileOutputStream(new File(desFilePath));
			fos.write(encryptBytes);
		} catch (Exception e) {

			throw e;
		}finally {
			if (fos != null){
				fos.close();
			}
		}
	}


	/**
	 * 加密文件
	 * @param skString
	 * @param context 需要加密的文件内容
	 * @param desFilePath
	 * @throws Exception
	 */
	public static void tripleDesEncryptFile2(String skString,String context,String desFilePath) throws Exception {
		FileOutputStream fos = null;
		try {
			byte[] fileBytes = null;
			fileBytes = context.getBytes(StandardCharsets.UTF_8);
			byte[] encryptBytes = tripleDesEncryptBytes(skString, fileBytes);
			fos = new FileOutputStream(new File(desFilePath));
			fos.write(encryptBytes);
			fos.close();
		} catch (Exception e) {

			throw e;
		}finally {
			if (fos != null){
				fos.close();
			}
		}
	}

	/**
	 * 解密文件
	 * @param skString
	 * @param srcFilePath
	 * @param desFilePath
	 * @throws Exception
	 */
	public static void tripleDesDecryptFile(String skString,String srcFilePath,String desFilePath) throws Exception {
		FileOutputStream fos = null;
		try {
			byte[] fileBytes = getBytes(srcFilePath);
			byte[] encryptBytes = tripleDesDecryptBytes(skString, fileBytes);
			fos = new FileOutputStream(new File(desFilePath));
			fos.write(encryptBytes);
		} catch (Exception e) {

			throw e;
		}finally {
			if (fos != null){
				fos.close();
			}
		}
	}
	/**
	 * 解密文件 返回文件内容 str
	 * @param skString
	 * @param srcFilePath
	 */
	public static String tripleDesDecryptFile(String skString,String srcFilePath) throws Exception {
		FileOutputStream fos = null;
		try {
			byte[] fileBytes = getBytes(srcFilePath);
			byte[] encryptBytes = tripleDesDecryptBytes(skString, fileBytes);
			return new String(encryptBytes,StandardCharsets.UTF_8);
		} catch (Exception e) {

			throw e;
		}
	}
    /**
	 * 对指定的字段进行解密
	 * @param key
	 * @param hexString
	 * @return
	 * @return String
	 */
	public static String tripleDesDecrypt(String key, String hexString) {
		byte[] output = null;
		try {
			byte[] input = hex2Bytes(hexString);
			output = tripleDesDecryptBytes(key, input);
		} catch (Exception e) {

		}
		if (output != null)
		return new String(output, StandardCharsets.UTF_8);

		return null;
	}
	
	
	
	/**
	 * 对指定的字段进行加密
	 * @param key
	 * @param str
	 * @return
	 * @return String
	 */
	public static String fieldEncryption(String key,String str) {
		try {
			byte[] bytesSecret = hex2Bytes(key);
			SecretKey secretKey = new SecretKeySpec(bytesSecret, ALGORITHM_3DES);
			Cipher cipher = Cipher.getInstance(ALGORITHM_3DES);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] doFinal = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
			// 转换成16进制串
			String bytes2Hex = bytes2Hex(doFinal, false);
			return bytes2Hex;
		} catch (Exception e) {
		}
		return str;
	}

}

