package com.doadway.framework.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 网络工具类 
 * @author $Author:$
 * @version $Revision:$ $Date:$ 
 * @since 2014-8-28
 *
 */
public class NetworkUtil {

	/**
	 * 得到本机的IP地址
	 * @return String
	 */
	public static String getLocalIP() {
		String ip = "";
		if (ip == null) {
			try {
				Enumeration<NetworkInterface> netInterfacesEnum = NetworkInterface.getNetworkInterfaces();
				BRPOINT: while (netInterfacesEnum.hasMoreElements()) {
					NetworkInterface networkInterface =  netInterfacesEnum.nextElement();
					Enumeration<InetAddress> inetAddressEnum = networkInterface.getInetAddresses();
					while (inetAddressEnum.hasMoreElements()) {
						InetAddress iAdd = inetAddressEnum.nextElement();
						if ("eth0".equalsIgnoreCase(networkInterface.getName()) && !iAdd.isLoopbackAddress()
								&& iAdd.getHostAddress().indexOf(":") == -1) {
							ip = iAdd.getHostAddress();
							break BRPOINT;
						} else {
							iAdd = null;
						}
					}
				}
			} catch (Exception e) {
				ip = null;
			}
		}

		if (ip == null) {
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				return "未知";
			}
		}
		return ip;
	}
}
