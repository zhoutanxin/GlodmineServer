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
		} catch (UnknownAccountException uae ) { 
			return "账号不存在";
		} catch (IncorrectCredentialsException ice ) { 
			return "密码错误！";
		} catch (LockedAccountException lae ) { 
			return "用户已经被锁定不能登录,请与管理员联系";
		} catch (ExcessiveAttemptsException eae ) { 
			return "错误次数过多";
		} catch (AuthenticationException ae ) {
			ae.printStackTrace();
			return "用户密码错误";
		}
		//验证是否成功登录的方法
		if(currentUser.isAuthenticated()){
			ShiroUser shiroUser=(ShiroUser)currentUser.getPrincipal();
			JSONObject json = JSONObject.fromObject(shiroUser);
			System.out.println("返回"+json.toString());
			return json.toString();
		}
		return "失败";   

	}

	@RequestMapping("/loginout")
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			// session 会销毁，在SessionListener监听session销毁，清理权限缓存
			subject.logout();
		}
	}
    @RequestMapping(value = "/chklogin", method = RequestMethod.POST)  
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
