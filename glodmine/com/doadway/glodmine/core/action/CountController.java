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
import com.doadway.glodmine.core.biz.IncomeBiz;
import com.doadway.glodmine.core.biz.SpeedBiz;
import com.doadway.glodmine.core.model.Income;
import com.doadway.glodmine.core.model.Speed;
import com.doadway.glodmine.core.security.UserRealm.ShiroUser;
@Controller
public class CountController extends WWAction {
	@Resource
	SpeedBiz  speedBiz;
	@Resource
	IncomeBiz  incomeBiz;
	
	@RequestMapping(value="count/countbydate",method=RequestMethod.POST)
	@ResponseBody
	public  String count(Date startTime,Date endTime)  {
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
		//支出
		loadCountSpeedList(params);
		//收入
		loadCountIncomeList(params);
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	
	private void loadCountSpeedList(Map<String,Object> params){
		//支出
		List<Speed> speedList=speedBiz.countSpeedByDate(params);
		if(speedList!=null&&speedList.size()>0){
			jsonMap.put("speedflag", true);
			jsonMap.put("speedresult", speedList);
		}else{
			jsonMap.put("speedflag", false);
			jsonMap.put("speedresult", null);
			jsonMap.put("speedmsg", "信息不存在");
		}
	}
	private void loadCountIncomeList(Map<String,Object> params){
		//收入
		List<Income> incomeList=incomeBiz.countIncomeByDate(params);
		if(incomeList!=null&&incomeList.size()>0){
			jsonMap.put("incomeflag", true);
			jsonMap.put("incomeresult", incomeList);
		}else{
			jsonMap.put("incomeflag", false);
			jsonMap.put("incomeresult", null);
			jsonMap.put("incomemsg", "信息不存在");
		}	
	}
	
}
