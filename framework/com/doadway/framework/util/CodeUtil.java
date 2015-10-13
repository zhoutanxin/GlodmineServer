package com.doadway.framework.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;

/**
 * @author orange
 * @Time 2007-5-15 下午02:14:12
 */
public final class CodeUtil {

	/**
	 * 构造函数
	 */
	private CodeUtil() {

	}

	private static long SEED_MILLIS;

	private static long SEED_IP = 0;

	private static Object syncObj = new Object();

	private static long getLocalIpNum() {
		String localIpStr = "";
		String localIp = NetworkUtil.getLocalIP();
		if (StringUtils.isNotBlank(localIp)) {
			String[] numAry = localIp.split("\\.");
			for (int i = 0; i < numAry.length; i++) {
				localIpStr += numAry[i];
			}
		} else {
			localIpStr = "127001";
		}
		return Long.parseLong(localIpStr);
	}

	/**
	 * @return Random
	 */
	private static Random getRandom() {
		synchronized (syncObj) {
			if (SEED_IP == 0) {
				SEED_IP = getLocalIpNum();
			}
			long currentMills = System.currentTimeMillis();
			if (currentMills == SEED_MILLIS) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return getRandom();
			} else {
				SEED_MILLIS = currentMills;
				Random random = new Random(SEED_MILLIS ^ SEED_IP);
				return random;
			}
		}
	}

	/**
	 * 生成虚机ftp初始密码
	 * @return
	 */
	public static String getFTPPassWord() {
		String word1 = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
		String word2 = "23456789";
		StringBuffer buf = new StringBuffer();
		Random r = new Random(System.currentTimeMillis());
		for (int k = 0; k < 8; k++) {
			if (k % 2 == 0) {
				buf.append(word1.charAt(r.nextInt(word1.length())));
			} else {
				buf.append(word2.charAt(r.nextInt(word2.length())));
			}
		}
		return buf.toString();
	}

	public static String getFTPUserName() {
		Random random = getRandom();
		StringBuffer username = new StringBuffer("host");
		Integer i = random.nextInt(9999999);
		for (int j = 0; j < 7 - i.toString().length(); j++) {
			username.append("0");
		}
		username.append(i);
		return username.toString();
	}
	
	/**
	 * 功能描述:生成网站建设的ICP账号
	 * @return
	 */
	public static String getJZUserName() {
		Random random = getRandom();
		StringBuffer username = new StringBuffer("jz");
		Integer i = random.nextInt(9999999);
		for (int j = 0; j < 7 - i.toString().length(); j++) {
			username.append("0");
		}
		username.append(i);
		return username.toString();
	}
	/**
	 * 
	 * 功能描述：vps用户名生成苏州
	 *
	 * @return
	 *
	 * @author 白雪[baix@xinnet.com]
	 *
	 * @since 2012-10-16
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getVPSUserNameSuZhou(){
		Random random = getRandom();
		StringBuffer username = new StringBuffer("vps");
		Integer i = random.nextInt(9999999);
		for (int j = 0; j < 7 - i.toString().length(); j++) {
			username.append("0");
		}
		username.append(i);
		return username.toString();
	}
	/**
	 * 
	 * 功能描述：vps用户名生成歌华机房
	 *
	 * @return
	 *
	 * @author 白雪[baix@xinnet.com]
	 *
	 * @since 2012-10-16
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getVPSUserNameGh(){
		Random random = getRandom();
		StringBuffer username = new StringBuffer("gh");
		Integer i = random.nextInt(9999999);
		for (int j = 0; j < 7 - i.toString().length(); j++) {
			username.append("0");
		}
		username.append(i);
		return username.toString();
	}
	
	/**
	 * 
	 * 功能描述：vps用户名生成广州机房
	 *
	 * @return
	 *
	 * @author 胡子垚
	 *
	 * @since 2012-10-16
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getVPSUserNameGZ(){
		Random random = getRandom();
		StringBuffer username = new StringBuffer("gz");
		Integer i = random.nextInt(9999999);
		for (int j = 0; j < 7 - i.toString().length(); j++) {
			username.append("0");
		}
		username.append(i);
		return username.toString();
	}
	/**
	 * 
	 * 功能描述：vps用户名生成兆维机房
	 *
	 * @return
	 *
	 * @author 白雪[baix@xinnet.com]
	 *
	 * @since 2012-10-16
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getVPSUserNameZw(){
		Random random = getRandom();
		StringBuffer username = new StringBuffer("zw");
		Integer i = random.nextInt(9999999);
		for (int j = 0; j < 7 - i.toString().length(); j++) {
			username.append("0");
		}
		username.append(i);
		return username.toString();
	}
	/**
	 * 生成订单编号
	 * @return sb.toString()
	 */
	public static String getOrderCode() {
		/* 李晓波 升级订单号生成机制,增加4位  */
		Random random = getRandom();
		StringBuffer sb = new StringBuffer("A");
		Integer i = random.nextInt(1000000000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		i = random.nextInt(1000000000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		return sb.toString();
	}

	/**
	 * 生成订单项编号
	 * @return sb.toString()
	 */
	public static String getOrderLineCode() {
		/* 李晓波 升级订单号生成机制,增加4位  */
		Random random = getRandom();
		StringBuffer sb = new StringBuffer("A");
		Integer i = random.nextInt(1000000000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		i = random.nextInt(1000000000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		return sb.toString();
	}

	/**
	 * 生成订单项信息编号
	 * @return sb.toString();
	 */
	public static String getOrderLineInfoCode() {
		/* 李晓波 升级订单号生成机制,增加4位 */
		Random random = getRandom();
		StringBuffer sb = new StringBuffer("A");
		Integer i = random.nextInt(1000000000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		i = random.nextInt(1000000000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		return sb.toString();
	}

	/**
	 * 生成服务编号
	 * @param productType String
	 * @return String
	 */
	public static String getServiceCode(String productType) {
		Random random = getRandom();
		StringBuffer sb = new StringBuffer(productType);
		Integer i = random.nextInt(1000000000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		i = random.nextInt(100000);
		for (int j = 0; j < 5 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		return sb.toString();
	}

	/**
	 * 生成虚机附属服务编号
	 * @param productType String
	 * @return String
	 */
	public static String getSubServiceCode(String productType) {
		Random random = getRandom();
		StringBuffer sb = new StringBuffer(productType);
		Integer i = random.nextInt(1000000000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		i = random.nextInt(100000);
		for (int j = 0; j < 5 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		return sb.toString();
	}

	/**
	 * 生成续费价格编号
	 * @return rc.toString
	 */
	public static String getRenewPriceCode() {
		Random random = new Random(System.currentTimeMillis());
		StringBuffer rc = new StringBuffer("A");
		Integer i = random.nextInt(1000000000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			rc.append("0");
		}
		rc.append(i);
		i = random.nextInt(1000000);
		for (int j = 0; j < 6 - i.toString().length(); j++) {
			rc.append("0");
		}
		rc.append(i);
		return rc.toString();
	}
	// 生成OrderValue编号,此编号在添加目录中的添加url的生成编号，
	public static String getOrderValueCode() {
		Random random = new Random(System.currentTimeMillis());
		StringBuffer pc = new StringBuffer("OR");
		Integer i = random.nextInt(1000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			pc.append("0");
		}
		pc.append(i);
		i = random.nextInt(1000);
		for (int j = 0; j < 7 - i.toString().length(); j++) {
			pc.append("0");
		}
		pc.append(i);
		return pc.toString();
	}

	// 生成产品编号(暂时)
	/**
	 * @return pc.toString
	 */
	public static String getProductCode() {
		Random random = new Random(System.currentTimeMillis());
		StringBuffer pc = new StringBuffer("AE");
		Integer i = random.nextInt(1000000000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			pc.append("0");
		}
		pc.append(i);
		i = random.nextInt(10000000);
		for (int j = 0; j < 7 - i.toString().length(); j++) {
			pc.append("0");
		}
		pc.append(i);
		return pc.toString();
	}

	public static String getCode() {
		Random random = getRandom();
		StringBuffer sb = new StringBuffer();
		Integer i = random.nextInt(1000000000);
		for (int j = 0; j < 9 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		i = random.nextInt(100000);
		for (int j = 0; j < 5 - i.toString().length(); j++) {
			sb.append("0");
		}
		sb.append(i);
		return sb.toString();
	}

	public static String getDomainUserCode() {
		String abc = getCode();
		String word = "abcdefghijklmnopqrstuvwxyz"+abc+"0123456789";
		StringBuffer buf2 = new StringBuffer();
		Random r = new Random(System.currentTimeMillis());
		for (int k = 0; k < 14; k++) {
			buf2.append(word.charAt(r.nextInt(word.length())));
		}
		return buf2.toString();
	}

	/**
	 * 生成域名管理密码,在注册域名时用
	 * @return
	 */
	public static String getRandomLatter(int length) {
		String word = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer buf2 = new StringBuffer();
		Random r = new Random(System.currentTimeMillis());
		for (int k = 0; k < length; k++) {
			buf2.append(word.charAt(r.nextInt(word.length())));
		}
		return buf2.toString();
	}
	/**
	 * 生成域名管理密码,在注册域名时用
	 * @return
	 */
	public static String getDomainPassword() {
		String word = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer buf2 = new StringBuffer();
		Random r = new Random(System.currentTimeMillis());
		for (int k = 0; k < 8; k++) {
			buf2.append(word.charAt(r.nextInt(word.length())));
		}
		return buf2.toString();
	}

	/**
	 * 生成域名的my_dns_password
	 * @return
	 */
	public static String getDNSPassword() {
		String word = "abcdtuvwxyz01NOWX2345ijklmnoPQRSMYTUVBCDEFGHIefghrsJKLZ";
		StringBuffer buf2 = new StringBuffer();
		Random r = new Random(System.currentTimeMillis());
		for (int k = 0; k < 8; k++) {
			buf2.append(word.charAt(r.nextInt(word.length())));
		}
		return buf2.toString();
	}

	public static String getCapinfoCode(String xinnetCode) {
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		StringBuffer sb = new StringBuffer();
		sb.append(simpleDateFormat.format(new Date()));
		sb.append("-");
		sb.append(xinnetCode);
		sb.append("-");
		sb.append(CodeUtil.getCode());
		return sb.toString();
	}

	public static String getPassword(int len) {
		String word1 = "abcdefghjkmnpqrstuvwxyz";
		String word2 = "23456789";
		StringBuffer buf = new StringBuffer();
		Random r = new Random(System.currentTimeMillis());
		for (int k = 0; k < len; k++) {
			if (k % 2 == 0) {
				buf.append(word1.charAt(r.nextInt(word1.length())));
			} else {
				buf.append(word2.charAt(r.nextInt(word2.length())));
			}
		}
		return buf.toString();
	}

	public static String getNewPassword(int len) {
		String word1 = "abcdefghjkmnpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_=!@$&%";
		String word2 = "0123456789";
		StringBuffer buf = new StringBuffer();
		Random r = new Random(System.currentTimeMillis());
		for (int k = 0; k < len; k++) {
			if (k % 2 == 0) {
				buf.append(word1.charAt(r.nextInt(word1.length())));
			} else {
				buf.append(word2.charAt(r.nextInt(word2.length())));
			}
		}
		return buf.toString();
	}
	
	public static String getPassword() {
		String word1 = "abcdefghjkmnpqrstuvwxyz";
		String word2 = "23456789";
		StringBuffer buf = new StringBuffer();
		Random r = new Random(System.currentTimeMillis());
		for (int k = 0; k < 8; k++) {
			if (k % 2 == 0) {
				buf.append(word1.charAt(r.nextInt(word1.length())));
			} else {
				buf.append(word2.charAt(r.nextInt(word2.length())));
			}
		}
		return buf.toString();
	}
	public static String getNewPassword() {
		String word1 = "abcdefghjkmnpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_=!@$&%";
		String word2 = "0123456789";
		StringBuffer buf = new StringBuffer();
		Random r = new Random(System.currentTimeMillis());
		for (int k = 0; k < 8; k++) {
			if (k % 2 == 0) {
				buf.append(word1.charAt(r.nextInt(word1.length())));
			} else {
				buf.append(word2.charAt(r.nextInt(word2.length())));
			}
		}
		return buf.toString();
	}
	public static String getAgentPassword(String password) {
		password = password.trim();

		Random random = new Random();
		int r = random.nextInt(100);
		String rStr = String.valueOf(r);
		if (rStr.length() == 1) {
			rStr = "0" + rStr;
		}
		String salt = "$1$" + rStr + "$";
		return MD5Crypt.crypt(password, salt);
	}

	public static boolean validAgentPassword(String inputPassword, String agentPassword) {
		inputPassword = inputPassword.trim();
		try {
			if (MD5Crypt.crypt(inputPassword, agentPassword.substring(0, 6)).equals(agentPassword)) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * <b>会员编号生成规则</b><br>
	 * @return String
	 */
	public static String getAccountCode(){
		Random random = getRandom();
		StringBuilder sb = new StringBuilder();
		sb.append("DM");
		for(int index = 0;index < 8;index++){
			int ranInt = random.nextInt(9);
			sb.append(ranInt);
		}	
		return sb.toString();
	}
	
	/**
	 * 会员收货地址生成规则
	 * @return
	 */
	public static String getAddressCode(){
		Random random = getRandom();
		StringBuilder sb = new StringBuilder();
		sb.append("addr");
		for(int index = 0;index < 8;index++){
			int ranInt = random.nextInt(9);
			sb.append(ranInt);
		}	
		return sb.toString();
	}
	/**
	 * 
	 * 功能描述：随机生成CRM编号
	 *
	 * @return CRN编号
	 *
	 * @author 林阳
	 *
	 * @since Sep 4, 2012
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getCrmCode(){
		Random random = getRandom();
		StringBuilder sb = new StringBuilder();
		sb.append("CRM");
		for(int index = 0;index < 8;index++){
			int ranInt = random.nextInt(9);
			sb.append(ranInt);
		}	
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(getOrderCode());
		System.out.println(getOrderLineCode());
		System.out.println(getOrderLineInfoCode());
		System.out.println(CodeUtil.getAccountCode());
	}
}
