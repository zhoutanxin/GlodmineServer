package com.doadway.glodmine.core.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doadway.glodmine.core.biz.MemberBiz;
import com.doadway.glodmine.core.dao.MemberMapper;
import com.doadway.glodmine.core.model.Member;
import com.doadway.glodmine.core.model.MemberExample;
import com.doadway.glodmine.core.model.MemberExample.Criteria;
@Service
public class MemberBizImpl implements MemberBiz {
	@Resource
	MemberMapper memberDAO;
	@Override
	public boolean registerMember(Member member) {
		if(!ifHaveMobile(member.getMobilePhone())){
			return memberDAO.insert(member)>0;
		}
		return false;
	}

	@Override
	public Member findByMobile(String mobilephone) {
		Member member=null;
		MemberExample example=new MemberExample() ;
		Criteria memCri=example.createCriteria();
		memCri.andMobilePhoneEqualTo(mobilephone);
		List<Member> memberList=memberDAO.selectByExample(example);
		if(memberList.size()>0){
			member=memberList.get(0);
		}
		return member;
	}
	@Override
	public Member findByEmail(String email) {
		Member member=null;
		MemberExample example=new MemberExample() ;
		Criteria memCri=example.createCriteria();
		memCri.andImailEqualTo(email);
		List<Member> memberList=memberDAO.selectByExample(example);
		if(memberList.size()>0){
			member=memberList.get(0);
		}
		return member;
	}

	@Override
	public boolean ifHaveMobile(String mobilephone) {
		boolean flag = false;
		if(findByMobile(mobilephone)!=null){
			flag=true;
		}
		return flag;
	}

	@Override
	public boolean ifHaveEmail(String email) {
		boolean flag = false;
		if(findByEmail(email)!=null){
			flag=true;
		}
		return flag;
	}

	@Override
	public boolean saveOrUpdateMember(Member member) {
		if( member.getId()!=null ){
			return memberDAO.updateByPrimaryKey(member)>0;
		}else{
			return memberDAO.insert(member)>0;
		}
	}

}
