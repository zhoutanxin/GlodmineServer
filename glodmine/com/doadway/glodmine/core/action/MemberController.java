package com.doadway.glodmine.core.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doadway.framework.action.WWAction;
import com.doadway.framework.captcha.ValidCodeServlet;
import com.doadway.framework.util.CodeUtil;
import com.doadway.framework.util.StringUtil;
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
	@RequestMapping(value="/memberInfo",method=RequestMethod.POST)
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
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	@ResponseBody
	public  String updatePwd(HttpServletRequest request,Member member)  {
		boolean flag=false;
		if(member.getMobilePhone()!=null){
			flag= userBiz.updatePwd(member.getMobilePhone(),member.getPassword());
			if(flag){
				jsonMap.put("flag", flag);
				jsonMap.put("msg", "密码已修改");
			}else{
				jsonMap.put("flag", flag);
				jsonMap.put("msg","修改失败,请检查操作");
			}
			
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="/sendSms",method=RequestMethod.POST)
	@ResponseBody
	public  String sendSmsCode(HttpServletRequest request,HttpSession session,String mobilePhone,String type)  {
		Map<String,Object>resultMap=new HashMap<String,Object>();
		SMSUtils smsUtils = new SMSUtils();
		String accountSid=smsUtils.getAccountSid();
		String token=smsUtils.getAuthToken();
		String appId="5a8cb8c20c7a432eb395aad9e5b232d8";
		String templateId="15425";
		if(StringUtil.isEmpty(type)){
			type="";
		}
		if(!StringUtil.isEmpty(type) &&type.equals("pwd")){
			templateId="15503";
		}
		String to="13683027377";
		if(mobilePhone!=null&&!mobilePhone.trim().equals("")){
			to=mobilePhone;
		}
		String para=CodeUtil.getSMSCode();
		String result=SMSUtils.templateSMS(true, accountSid,token,appId, templateId,to,para);
		System.out.println("手机验证码为:"+para);
		JSONObject jsonRes=JSONObject.fromObject(JSONObject.fromObject(result).get("resp"));//=null;

		if(jsonRes!=null&&jsonRes.get("respCode").equals("000000")){
			session.setAttribute("sms_code"+type, para);
			session.setAttribute("sms_code_time"+type, System.currentTimeMillis());
			resultMap.put("flag", true);
		}else{
			resultMap.put("flag", false);
			resultMap.put("msg","发送失败:"+(String)jsonRes.get("respCode"));//
		}
			
		return JSONObject.fromObject(resultMap).toString(); 
	}
	@RequestMapping(value="/getVCode",method=RequestMethod.POST)
	@ResponseBody
	public  String getVCode(HttpServletRequest request,HttpSession session)  {
		Map<String,Object>resultMap=new HashMap<String,Object>();
		boolean flag=false;
		String vcode=(String)session.getAttribute(ValidCodeServlet.SESSION_VALID_CODE);
		Long maxTime=System.currentTimeMillis()-(Long)session.getAttribute(ValidCodeServlet.SESSION_VALID_TIME);
		if(vcode!=null&&maxTime<=90*1000)flag=true;
		if(flag){
			resultMap.put("flag", flag);
			resultMap.put("msg", "验证码请求成功");
			resultMap.put("vcode",vcode);
		}else{
			resultMap.put("flag", flag);
			resultMap.put("vcode",null);
			resultMap.put("msg","验证码已过期");
		}

		return JSONObject.fromObject(resultMap).toString(); 
	}
	@RequestMapping(value="/getSmsCode",method=RequestMethod.POST)
	@ResponseBody
	public  String getSmsCode(HttpServletRequest request,HttpSession session,String type)  {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		boolean flag=false;
		if(StringUtil.isEmpty(type)){
			type="";
		}
		String smscode="";
		Long maxTime=0l;
		if(session.getAttribute("sms_code"+type)!=null){
			smscode=(String)session.getAttribute("sms_code"+type);
			maxTime=System.currentTimeMillis()-(Long)session.getAttribute("sms_code_time"+type);
		}
		if(!StringUtil.isEmpty(smscode)&&maxTime<=90*1000)flag=true;
		if(flag){
			resultMap.put("flag", flag);
			resultMap.put("msg", "手机验证码请求成功");
			resultMap.put("smscode",smscode);
		}else{
			resultMap.put("flag", flag);
			resultMap.put("msg","手机验证码已过期");
			resultMap.put("smscode",null);
		}
		
		return JSONObject.fromObject(resultMap).toString(); 
	}
}
