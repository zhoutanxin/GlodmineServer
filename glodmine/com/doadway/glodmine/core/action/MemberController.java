package com.doadway.glodmine.core.action;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doadway.framework.action.WWAction;
import com.doadway.framework.util.CodeUtil;
import com.doadway.framework.web.CookieUtils;
import com.doadway.framework.web.SMSUtils;
import com.doadway.glodmine.core.biz.MemberBiz;
import com.doadway.glodmine.core.model.Member;
import com.doadway.glodmine.core.security.UserRealm.ShiroUser;
@Controller
public class MemberController extends WWAction {
	@Resource
	MemberBiz  userBiz;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
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
	@RequestMapping(value="/ifhasmob",method=RequestMethod.POST)
	@ResponseBody
	public  String ifExistMobile(HttpServletRequest request,String mobilePhone)  {
		if(!userBiz.ifHaveMobile(mobilePhone)){
			return "true";
		} else{
			return "false";
		}
	}
	@RequestMapping(value="/hasMob",method=RequestMethod.POST)
	@ResponseBody
	public  String ifNoExistMobile(HttpServletRequest request,String mobilePhone)  {
		if(userBiz.ifHaveMobile(mobilePhone)){
			return "true";
		} else{
			return "false";
		}
	}
	@RequestMapping(value="/ifhasemail",method=RequestMethod.POST)
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
	public  String memberInfo(HttpServletRequest request,String mobilephone)  {
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
	@RequestMapping(value="/updInfo",method=RequestMethod.POST)
	@ResponseBody
	public  String updInfo(HttpServletRequest request,Member member)  {
		boolean flag=false;
		if(member.getMobilePhone()!=null){
			flag= userBiz.updateMember(member);
			if(flag){
				jsonMap.put("flag", flag);
				jsonMap.put("msg", "信息已保存");
			}else{
				jsonMap.put("flag", flag);
				jsonMap.put("msg","更新失败,请检查操作");
			}

		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="/sendSms",method=RequestMethod.POST)
	@ResponseBody
	public  String sendSmsCode(HttpServletRequest request,HttpServletResponse respone,String mobilePhone)  {
		boolean flag=false;
		SMSUtils smsUtils = new SMSUtils();
		String accountSid=smsUtils.getAccountSid();
		String token=smsUtils.getAuthToken();
		String appId="5a8cb8c20c7a432eb395aad9e5b232d8";
		String templateId="15425";
		String to="13683027377";
		String para=CodeUtil.getSMSCode();
		String result=SMSUtils.templateSMS(true, accountSid,token,appId, templateId,to,para);
		CookieUtils.addCookie(request, respone, "COOKIE_SMS_CODE", para, 90, null);
		JSONObject jsonRes=JSONObject.fromObject(JSONObject.fromObject(result).get("resp"));
		if((String)jsonRes.get("respCode")=="000000"){
			flag=true;
			jsonMap.put("flag", flag);
			jsonMap.put("msg", "发送成功");
		}else{
			jsonMap.put("flag", flag);
			jsonMap.put("msg",(String)jsonRes.get("respCode"));
		}
			
		return JSONObject.fromObject(jsonMap).toString(); 
	}

}
