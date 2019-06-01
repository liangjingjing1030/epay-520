package org.epay.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 类名: MD5生成私钥工具类
 * 作者: HappyDan
 * 时间: 2019年5月1日
 * 版本: V1.0
 */
public class MD5AndCreateKeyUtil {
	
	private static String encodingCharset = "UTF-8";
	private static AtomicLong req_key = new AtomicLong(0L);
	private static String req_key_prefix = "REQ";
	private static AtomicLong res_key = new AtomicLong(0L);
	private static String res_key_prefix = "RES";
	
	public static String reqKey() {
		return createKey(req_key_prefix, req_key);
	}
	
	public static String getReqKey() {
		return md5(reqKey(), encodingCharset).toUpperCase();
	}
	
	public static String resKey() {
		return createKey(res_key_prefix, res_key);
	}
	
	public static String getResKey() {
		return md5(resKey(), encodingCharset).toUpperCase();
	}
	
	private static String createKey(String prefix, AtomicLong seq) {
		int random = (int)(Math.random()*9+1)*100000;
		return String.format("%s%s%06d", prefix, DateUtil.getSeqString(), random);
	}
	
	public static String toHex(byte input[]) {
		if (input == null)
			return null;
		StringBuffer output = new StringBuffer(input.length * 2);
		for (int i = 0; i < input.length; i++) {
			int current = input[i] & 0xff;
			if (current < 16)
				output.append("0");
			output.append(Integer.toString(current, 16));
		}

		return output.toString();
	}
	public static String md5(String value, String charset) {
		MessageDigest md = null;
		try {
			byte[] data = value.getBytes(charset);
			md = MessageDigest.getInstance("MD5");
			byte[] digestData = md.digest(data);
			return toHex(digestData);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		String reqKey = getReqKey();
		System.out.println("reqKey=" + reqKey);
		String resKey = getResKey();
		System.out.println("resKey=" + resKey);
	}
}
