package org.epay.common.util;


import java.util.concurrent.atomic.AtomicLong;

/**
 * 名称: 生成全局唯一序列号工具类
 * 作者: HappyDan
 * 版本: V1.0
 */
public class MySeq {

	private static AtomicLong pay_seq = new AtomicLong(0L);
	private static String pay_seq_prefix = "P";
	private static AtomicLong trans_seq = new AtomicLong(0L);
	private static String trans_seq_prefix = "T";
	private static AtomicLong refund_seq = new AtomicLong(0L);
	private static String refund_seq_prefix = "R";
	
	private static AtomicLong account_seq = new AtomicLong(0L);
	private static String account_seq_prefix = "A";

	private static String node = "00";
	static {
		try {
			//URL url = Thread.currentThread().getContextClassLoader().getResource("config" + File.separator + "system.properties");
			//Properties properties = new Properties();
			//properties.load(url.openStream());
			//node = properties.getProperty(ConfigEnum.SERVER_NAME.getKey());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPay() {
		return getSeq(pay_seq_prefix, pay_seq);
	}

	public static String getTrans() {
		return getSeq(trans_seq_prefix, trans_seq);
	}

	public static String getRefund() {
		return getSeq(refund_seq_prefix, refund_seq);
	}

	public static String getAccount() {
		return getSeq(account_seq_prefix, account_seq);
	}
	
	private static String getSeq(String prefix, AtomicLong seq) {
		prefix += node;
		int random = (int)(Math.random()*9+1)*100000;
		return String.format("%s%s%06d", prefix, DateUtil.getSeqString(), random);
	}
	
	

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println("pay=" + getPay());
			System.out.println("trans=" + getTrans());
			System.out.println("refund=" + getRefund());
		}

	}

}