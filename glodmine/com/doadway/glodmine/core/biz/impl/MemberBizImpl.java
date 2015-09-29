package com.doadway.glodmine.core.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doadway.glodmine.core.biz.MemberBiz;
import com.doadway.glodmine.core.dao.MemberMapper;
import com.doadway.glodmine.core.model.Member;
@Service
public class MemberBizImpl implements MemberBiz {
	@Resource
	MemberMapper memberDAO;
	@Override
	public boolean registerMember(Member member) {
		return memberDAO.insert(member)>0;
	}

	@Override
	public Member findByMobile(String mobilephone) {
		// TODO Auto-generated method stub
		return null;
	}

}
