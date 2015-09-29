package com.doadway.framework.email;
import java.io.StringWriter;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class MailSender {
	private static Logger log = LogManager.getLogger(MailSender.class);
	@Resource
	private JavaMailSenderImpl javaMailSender;
	/**
	 * 默认线程数是5个
	 */
	private static int SIZE=5;
	
	private static ExecutorService service = Executors.newFixedThreadPool(SIZE);
	
	
	public MailSender(){}
	
	/**
	 * @param size 开启线程数量
	 */
	public MailSender(int size){
		if(size>0){
			service = Executors.newFixedThreadPool(size);
		}
	}
	
	/**
	 * 同步发送邮件
	 * @param mail
	 */
	public void syncSendMail(Mail mail){
		sendNotifyMail(mail);
	}
	
	/**
	 * 异步发送邮件
	 * @param mail
	 */
	public void sendMail(final Mail mail){
		service.execute(new Runnable() {
			@Override
			public void run() {
				sendNotifyMail(mail);
			}
		});
	}
	
	/**
	 * 发送邮件
	 * @param message
	 */
	private void sendNotifyMail(Mail mail) {
		log.info("发送邮件:" + mail.getTo());
		String subject = null;
		String content = null;
		String to = mail.getTo();
		VelocityEngine vce = null;
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			subject = mail.getSubject();
			vce = new VelocityEngine();
			vce.setProperty(Velocity.RESOURCE_LOADER, "class");
			vce.setProperty("class.resource.loader.class",
					"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			StringWriter sw = new StringWriter();

			VelocityContext context1 = new VelocityContext();
			Set<String> keys = mail.getContent().keySet();
			for(String key : keys){
				context1.put(key, mail.getContent().get(key));
			}
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"utf-8");
			Template template = vce.getTemplate(mail.getTemplate(), "UTF-8");
			template.merge(context1, sw);
			sw.flush();

			content = sw.getBuffer().toString();
			messageHelper.setSubject(subject);
			messageHelper.setText(content,true);
			messageHelper.setTo(to);
			messageHelper.setFrom(MimeUtility.encodeText(mail.getFromText()) + "<"
					+ javaMailSender.getUsername() + ">");
		//	javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			System.out.println("发送通知邮件错误!!!"+ mail.getTo()+e.toString());
			log.debug("发送通知邮件错误!!!"+ mail.getTo(), e);
		}
	}

	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
}
