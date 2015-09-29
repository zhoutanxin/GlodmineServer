package com.doadway.framework.email;

import java.util.HashMap;
import java.util.Map;

public class Mail {
	/**
	 * 邮件主题
	 */
	private String subject="";
	
	
	/**
	 * 收件人地址
	 */
	private String to;
	
	/**
	 * 发件人显示名称
	 */
	private String fromText="多维矩阵邮件系统";
	
	/**
	 * 模板名称
	 * 例如：mailtemplate/activity.vm
	 */
	private String template;
	
	/**
	 * <pre>
	 * 邮件内容实体
	 * content内容需要将参数变量设置为key,参数值为value
	 * 例如：
	 * 比如vm模板引擎中设置参数变量:start_time,需要设定的值是:2014-6-6.
	 * 设置如下:content.put("start_time","2014-6-6")
	 * </pre>
	 */
	private Map<String, Object> content = new HashMap<String, Object>();

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFromText() {
		return fromText;
	}

	public void setFromText(String fromText) {
		this.fromText = fromText;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Map<String, Object> getContent() {
		return content;
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

}
