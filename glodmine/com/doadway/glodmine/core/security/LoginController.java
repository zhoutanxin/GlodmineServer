package com.doadway.glodmine.core.security;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doadway.framework.action.WWAction;
import com.doadway.glodmine.core.biz.MemberBiz;
import com.doadway.glodmine.core.model.Member;
import com.doadway.glodmine.core.security.UserRealm.ShiroUser;
@Controller
public class LoginController extends WWAction {
	@Resource
	MemberBiz  userBiz;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public  String login(HttpServletRequest request,Member user,Map<String,Object> mv)  {
		Subject currentUser = SecurityUtils.getSubject();
		String  psw=user.getPassword();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getMobilePhone(),psw);
		//记住我功能不是记住密码而是在整个会话过程记住会话ID,对未登录用户时用购物车有点用
		/*
		if( rememberMe != null ){
			if( rememberMe ){
				token.setRememberMe(true);
			}
		}
		*/
		try {
		  currentUser.login(token);
		  //验证是否成功登录的方法
		  if(currentUser.isAuthenticated()){
			  ShiroUser shiroUser=(ShiroUser)currentUser.getPrincipal();
			  jsonMap.put("flag",true);
			  jsonMap.put("msg",shiroUser);
			  return JSONObject.fromObject(jsonMap).toString();
		  }			  
		} catch (UnknownAccountException uae ) { 
			jsonMap.put("msg","账号不存在");
		} catch (IncorrectCredentialsException ice ) {
			jsonMap.put("msg","密码错误");
		} catch (LockedAccountException lae ) { 
			jsonMap.put("msg","用户已经被锁定不能登录,请与管理员联系");
		} catch (ExcessiveAttemptsException eae ) {
			jsonMap.put("msg","错误次数过多");
			return "错误次数过多";
		} catch (AuthenticationException ae ) {
			jsonMap.put("msg","用户密码错误");
		}
		jsonMap.put("flag",false);
		return JSONObject.fromObject(jsonMap).toString(); 

	}

	@RequestMapping("/loginout")
	@ResponseBody
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			// session 会销毁，在SessionListener监听session销毁，清理权限缓存
			subject.logout();
			jsonMap.put("flag", true);
			jsonMap.put("msg", "已退出登录");
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("msg", "请先登录");		
		}
		JSONObject tempMap = JSONObject.fromObject(this.jsonMap);
		return tempMap.toString();
	}
    @RequestMapping(value = "/chklogin", method = RequestMethod.GET)  
    @ResponseBody
    public String chkLogin() {  
        Subject currentUser = SecurityUtils.getSubject();  
        if (!currentUser.isAuthenticated()) {  
            return "false";  
        }  
        return "true";  
    }  
	@RequestMapping("/admin/profile")
	public  String getProfile(Map<String,Object> mv)  {
		 Subject currentUser = SecurityUtils.getSubject(); 
		 ShiroUser user=(ShiroUser)currentUser.getPrincipal();
		 Member uDto=userBiz.findByMobile(user.mobliephone);
		 if(uDto!=null){
			 mv.put("userDto", uDto);
		 }
		return "/personal/profile";   
	}	
}
