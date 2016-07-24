package com.doadway.glodmine.core.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doadway.framework.action.WWAction;
import com.doadway.framework.pager.Page;
import com.doadway.glodmine.core.biz.SpeedBiz;
import com.doadway.glodmine.core.model.Speed;
import com.doadway.glodmine.core.security.UserRealm.ShiroUser;
@Controller
public class SpeedController extends WWAction {
	@Resource
	SpeedBiz  speedBiz;
	
	@RequestMapping(value="speed/save",method=RequestMethod.POST)
	@ResponseBody
	public  String sav(Speed speed)  {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			ShiroUser shiroUser=(ShiroUser)currentUser.getPrincipal();
			speed.setMemberId(shiroUser.getId());
		}		
		/*设置时分秒*/
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(speed.getIdate());
		 Calendar caltmp = Calendar.getInstance();
		 caltmp.setTime(new Date(System.currentTimeMillis()));
		 cal.set(Calendar.HOUR_OF_DAY, caltmp.get(Calendar.HOUR_OF_DAY));
		 cal.set(Calendar.MINUTE, caltmp.get(Calendar.MINUTE));
		 cal.set(Calendar.SECOND, caltmp.get(Calendar.SECOND));
		 speed.setIdate(cal.getTime());
		if(speedBiz.saveSpeed(speed)){
			jsonMap.put("flag", true);
			jsonMap.put("msg", "保存成功");
		} else{
			jsonMap.put("msg", "保存失败");
			jsonMap.put("flag", false);
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="speed/get",method=RequestMethod.POST)
	@ResponseBody
	public  String get(Integer id)  {
		Speed speed=speedBiz.findSpeed(id);
		if(speed!=null){
			jsonMap.put("flag", true);
			jsonMap.put("result", speed);
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("result", null);
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="speed/delete",method=RequestMethod.POST)
	@ResponseBody
	public  String delete(Integer id)  {
		Speed speed=speedBiz.findSpeed(id);
		if(speed!=null){
			speedBiz.delById(speed.getId());
			jsonMap.put("flag", true);
			jsonMap.put("result", "信息已经删除");
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("result", "信息不存在");
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="speed/querylist",method=RequestMethod.POST)
	@ResponseBody
	public  String querylist(Page page,Date startTime,Date endTime,Integer categoryId)  {
		Map<String, Object> params =new HashMap<String,Object>();
		/*设置时分秒*/
		 Calendar cal = Calendar.getInstance();
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			ShiroUser shiroUser=(ShiroUser)currentUser.getPrincipal();
			params.put("memberId", shiroUser.getId());
		}			
		if(startTime!=null){
			params.put("startTime", startTime);
		}
		if(endTime!=null){
			cal.setTime(endTime);
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE,59);
			cal.set(Calendar.SECOND,59);
			params.put("endTime", cal.getTime());
		}
		//查询最近30天的记录
		if(startTime==null&&endTime==null){
			cal.setTime(new Date(System.currentTimeMillis()));
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE,59);
			cal.set(Calendar.SECOND,59);			
			params.put("endTime",cal.getTime() );
		
			Calendar date = Calendar.getInstance();
			Date currentDate = new Date(System.currentTimeMillis());
			date.setTime(currentDate);
			date.set(Calendar.HOUR_OF_DAY,0);
			date.set(Calendar.MINUTE,0);
			date.set(Calendar.SECOND,0);		
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 30);
			params.put("startTime", date.getTime());
		}
		if(categoryId!=0&&categoryId!=-1){
			params.put("categoryId", categoryId);
		}
		List<Speed> speedList=speedBiz.findSpeedByPage(page, params);
		if(speedList!=null&&speedList.size()>0){
			jsonMap.put("flag", true);
			jsonMap.put("result", speedList);
			jsonMap.put("page", page);
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("result", null);
			jsonMap.put("msg", "信息不存在");
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="speed/countbydate",method=RequestMethod.POST)
	@ResponseBody
	public  String countbydate(Date startTime,Date endTime,String[] typeSpeed)  {
		Map<String, Object> params =new HashMap<String,Object>();
		/*设置时分秒*/
		 Calendar cal = Calendar.getInstance();		
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			ShiroUser shiroUser=(ShiroUser)currentUser.getPrincipal();
			params.put("memberId", shiroUser.getId());
		}				
		if(startTime!=null){
			params.put("startTime", startTime);
		}
		if(endTime!=null){
			cal.setTime(endTime);
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE,59);
			cal.set(Calendar.SECOND,59);
			params.put("endTime", cal.getTime());
		}
		//查询最近30天的记录
		if(startTime==null&&endTime==null){
			cal.setTime(new Date(System.currentTimeMillis()));
			cal.set(Calendar.HOUR_OF_DAY,23);
			cal.set(Calendar.MINUTE,59);
			cal.set(Calendar.SECOND,59);			
			params.put("endTime",cal.getTime() );			
			
			Calendar date = Calendar.getInstance();
			Date currentDate = new Date(System.currentTimeMillis());
			date.setTime(currentDate);
			date.set(Calendar.HOUR_OF_DAY,0);
			date.set(Calendar.MINUTE,0);
			date.set(Calendar.SECOND,0);				
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 30);
			params.put("startTime", date.getTime());
		}
		if(typeSpeed!=null&&typeSpeed.length>0){
			params.put("typeIds", typeSpeed);
		}
		List<Speed> speedList=speedBiz.countSpeedByDate(params);
		if(speedList!=null&&speedList.size()>0){
			jsonMap.put("flag", true);
			jsonMap.put("result", speedList);
			jsonMap.put("page", page);
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("result", null);
			jsonMap.put("msg", "信息不存在");
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	

}
