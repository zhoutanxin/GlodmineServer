package com.doadway.glodmine.core.biz;

import com.doadway.glodmine.core.model.Member;

public interface MemberBiz {
	public boolean registerMember(Member member);
	
	public Member findByMobile(String mobilephone);
	public Member findByEmail(String mobilephone);
	
	public boolean ifHaveMobile(String mobilephone);
	
	public boolean ifHaveEmail(String mobilephone);
	
	public boolean updateMember(Member member);
	
	public boolean updatePwd(String mobilePhone,String pwd);
}
