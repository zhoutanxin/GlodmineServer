package com.doadway.framework.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class IbatisOgnlListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}
	/*
	 * 关闭IbatisOgnl安全管理器,解决线上虚拟主机Ibatis OGNL异常问题.
	 * */
	public void contextInitialized(ServletContextEvent arg0) {
		org.apache.ibatis.ognl.OgnlRuntime.setSecurityManager(null);
	}

}
