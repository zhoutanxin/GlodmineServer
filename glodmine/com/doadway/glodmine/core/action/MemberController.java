package com.doadway.glodmine.core.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doadway.framework.action.WWAction;
import com.doadway.glodmine.core.biz.MemberBiz;
import com.doadway.glodmine.core.model.Member;
@Controller
public class MemberController extends WWAction {
	@Resource
	MemberBiz  userBiz;
	
	@RequestMapping(value="/regsiter",method=RequestMethod.POST)
	@ResponseBody
	public  String reg(HttpServletRequest request,Member user,Map<String,Object> mv)  {
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
	public  String ifExistMobile(HttpServletRequest request,String mobilephone)  {
		if(userBiz.ifHaveMobile(mobilephone)){
			return "true";
		} else{
			return "false";
		}
	}
	@RequestMapping(value="/ifhasemail",method=RequestMethod.POST)
	@ResponseBody
	public  String ifExistEmail(HttpServletRequest request,String mobilephone)  {
		if(userBiz.ifHaveMobile(mobilephone)){
			return "true";
		} else{
			return "false";
		}
	}

}
