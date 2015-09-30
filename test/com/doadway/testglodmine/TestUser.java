package com.doadway.testglodmine;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.doadway.glodmine.core.biz.MemberBiz;
import com.doadway.glodmine.core.model.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/dataAccessContext-jdbc.xml","classpath:/config/dispatcher-servlet.xml" })
public class TestUser {
	  @Resource
	  private MemberBiz memberBiz;
	  
	  @Test
	  public void testExecute(){
		  Member member=new Member();
		  member.setBrithday(new Date(System.currentTimeMillis()));
		  member.setEmail("zhoutanxin@hotmail.com");
		  member.setGender(2);
		  member.setMobilePhone("13683027377");
		  member.setNiceName("niceXin");
		  member.setPassword("A45115517");
		  member.setRealName("周潭新");
		  member.setRegisterTime(new Date(System.currentTimeMillis()));
		  member.setStatus(1);
		  member.setUpdateTime(new Date(System.currentTimeMillis()));
		memberBiz.registerMember(member);
	  }
	  
}
