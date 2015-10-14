package com.doadway.glodmine.core.action;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doadway.framework.action.WWAction;
import com.doadway.framework.util.CodeUtil;
import com.doadway.glodmine.core.biz.MemberBiz;
import com.doadway.glodmine.core.model.Member;
import com.doadway.glodmine.core.security.UserRealm.ShiroUser;
@Controller
public class MemberController extends WWAction {
	@Resource
	MemberBiz  userBiz;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	@ResponseBody
	public  String reg(HttpServletRequest request,Member user,Map<String,Object> mv)  {
		user.setRegisterTime(new Date(System.currentTimeMillis()));
		user.setStatus(1);
		user.setGender(1);
		user.setUpdateTime(new Date(System.currentTimeMillis()));
		user.setNiceName(CodeUtil.getAccountCode());
		if(userBiz.registerMember(user)){
			jsonMap.put("flag", true);
			jsonMap.put("msg", "注册成功");
		} else{
			jsonMap.put("msg", "注册失败");
			jsonMap.put("flag", false);
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="/ifhasmob",method=RequestMethod.GET)
	@ResponseBody
	public  String ifExistMobile(HttpServletRequest request,String mobilePhone)  {
		if(!userBiz.ifHaveMobile(mobilePhone)){
			return "true";
		} else{
			return "false";
		}
	}
	@RequestMapping(value="/ifhasemail",method=RequestMethod.GET)
	@ResponseBody
	public  String ifExistEmail(HttpServletRequest request,String imail)  {
		if(!userBiz.ifHaveEmail(imail)){
			return "true";
		} else{
			return "false";
		}
	}
	@RequestMapping(value="/memberInfo",method=RequestMethod.GET)
	@ResponseBody
	public  String findByMobile(HttpServletRequest request,String mobilephone)  {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			ShiroUser shiroUser=(ShiroUser)currentUser.getPrincipal();
			Member user = userBiz.findByMobile(shiroUser.getMobliephone());
			jsonMap.put("flag", true);
			jsonMap.put("result", user);
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("msg","登录用户不存在,请先登录");
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}

}
