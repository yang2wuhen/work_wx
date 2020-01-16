package com.tencent.wework.util;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加密和解密工具
 *
 * @Author: syj
 * @CreateDate: 2018/7/20 16:52
 */
public class RSAUtil {

	/**
	 * 数字签名，密钥算法
	 */
	private static final String RSA_KEY_ALGORITHM = "RSA";

	/**
	 * 数字签名签名/验证算法
	 */
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/**
	 * RSA密钥长度，RSA算法的默认密钥长度是1024密钥长度必须是64的倍数，在512到65536位之间
	 */
	private static final int KEY_SIZE = 1024;

	/**
	 * 生成密钥对
	 */
	private static Map<String, String> initKey() throws Exception {
		KeyPairGenerator keygen = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);
		SecureRandom secrand = new SecureRandom();
		/**
		 * 初始化随机产生器
		 */
		secrand.setSeed("initSeed".getBytes());
		/**
		 * 初始化密钥生成器
		 */
		keygen.initialize(KEY_SIZE, secrand);
		KeyPair keys = keygen.genKeyPair();

		byte[] pub_key = keys.getPublic().getEncoded();
		String publicKeyString = Base64.encodeBase64String(pub_key);

		byte[] pri_key = keys.getPrivate().getEncoded();
		String privateKeyString = Base64.encodeBase64String(pri_key);

		Map<String, String> keyPairMap = new HashMap<>();
		keyPairMap.put("publicKeyString", publicKeyString);
		keyPairMap.put("privateKeyString", privateKeyString);

		return keyPairMap;
	}

	/**
	 * 密钥转成字符串
	 *
	 * @param key
	 * @return
	 */
	public static String encodeBase64String(byte[] key) {
		return Base64.encodeBase64String(key);
	}

	/**
	 * 密钥转成byte[]
	 *
	 * @param key
	 * @return
	 */
	public static byte[] decodeBase64(String key) {
		return Base64.decodeBase64(key);
	}

	/**
	 * 公钥加密
	 *
	 * @param data      加密前的字符串
	 * @param publicKey 公钥
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encryptByPubKey(String data, String publicKey) throws Exception {
		byte[] pubKey = RSAUtil.decodeBase64(publicKey);
		byte[] enSign = encryptByPubKey(data.getBytes(), pubKey);
		return Base64.encodeBase64String(enSign);
	}

	/**
	 * 公钥加密
	 *
	 * @param data   待加密数据
	 * @param pubKey 公钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPubKey(byte[] data, byte[] pubKey) throws Exception {
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 私钥加密
	 *
	 * @param data       加密前的字符串
	 * @param privateKey 私钥
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encryptByPriKey(String data, String privateKey) throws Exception {
		byte[] priKey = RSAUtil.decodeBase64(privateKey);
		byte[] enSign = encryptByPriKey(data.getBytes(), priKey);
		return Base64.encodeBase64String(enSign);
	}

	/**
	 * 私钥加密
	 *
	 * @param data   待加密的数据
	 * @param priKey 私钥
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static byte[] encryptByPriKey(byte[] data, byte[] priKey) throws Exception {
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 公钥解密
	 *
	 * @param data   待解密的数据
	 * @param pubKey 公钥
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static byte[] decryptByPubKey(byte[] data, byte[] pubKey) throws Exception {
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 公钥解密
	 *
	 * @param data      解密前的字符串
	 * @param publicKey 公钥
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decryptByPubKey(String data, String publicKey) throws Exception {
		byte[] pubKey = RSAUtil.decodeBase64(publicKey);
		byte[] design = decryptByPubKey(Base64.decodeBase64(data), pubKey);
		return new String(design);
	}

	/**
	 * 私钥解密
	 *
	 * @param data   待解密的数据
	 * @param priKey 私钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPriKey(byte[] data, byte[] priKey) throws Exception {
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 私钥解密
	 *
	 * @param data       解密前的字符串
	 * @param privateKey 私钥
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decryptByPriKey(String data, String privateKey) throws Exception {
		byte[] priKey = RSAUtil.decodeBase64(privateKey);
		byte[] design = decryptByPriKey(Base64.decodeBase64(data), priKey);
		return new String(design);
	}

	/**
	 * RSA签名
	 *
	 * @param data   待签名数据
	 * @param priKey 私钥
	 * @return 签名
	 * @throws Exception
	 */
	public static String sign(byte[] data, byte[] priKey) throws Exception {
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		// 生成私钥
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 实例化Signature
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		// 初始化Signature
		signature.initSign(privateKey);
		// 更新
		signature.update(data);
		return Base64.encodeBase64String(signature.sign());
	}

	/**
	 * RSA校验数字签名
	 *
	 * @param data   待校验数据
	 * @param sign   数字签名
	 * @param pubKey 公钥
	 * @return boolean 校验成功返回true，失败返回false
	 */
	public boolean verify(byte[] data, byte[] sign, byte[] pubKey) throws Exception {
		// 实例化密钥工厂
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		// 初始化公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
		// 产生公钥
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		// 实例化Signature
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		// 初始化Signature
		signature.initVerify(publicKey);
		// 更新
		signature.update(data);
		// 验证
		return signature.verify(sign);
	}



}