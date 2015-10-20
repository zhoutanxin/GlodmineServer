package com.doadway.glodmine.core.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doadway.framework.action.WWAction;
import com.doadway.framework.pager.Page;
import com.doadway.glodmine.core.biz.IncomeBiz;
import com.doadway.glodmine.core.model.Income;
@Controller
public class IncomeController extends WWAction {
	@Resource
	IncomeBiz  incomeBiz;
	
	@RequestMapping(value="income/save",method=RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("deprecation")
	public  String sav(Income income)  {
		/*设置时分秒*/
		 Calendar cal = Calendar.getInstance();
		 Date currentDate=cal.getTime();
		 income.getIdate().setHours(currentDate.getHours());
		 income.getIdate().setMinutes(currentDate.getMinutes());
		 income.getIdate().setSeconds(currentDate.getSeconds());
		if(incomeBiz.saveIncome(income)){
			jsonMap.put("flag", true);
			jsonMap.put("msg", "保存成功");
		} else{
			jsonMap.put("msg", "保存失败");
			jsonMap.put("flag", false);
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="income/get",method=RequestMethod.POST)
	@ResponseBody
	public  String get(Integer id)  {
		Income income=incomeBiz.findIncome(id);
		if(income!=null){
			jsonMap.put("flag", true);
			jsonMap.put("result", income);
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("result", null);
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="income/delete",method=RequestMethod.POST)
	@ResponseBody
	public  String delete(Integer id)  {
		Income income=incomeBiz.findIncome(id);
		if(income!=null){
			incomeBiz.delById(income.getId());
			jsonMap.put("flag", true);
			jsonMap.put("result", "信息已经删除");
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("result", "信息不存在");
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="income/querylist",method=RequestMethod.POST)
	@ResponseBody
	public  String querylist(Page page,Date startTime,Date endTime,Integer categoryId)  {
		Map<String, Object> params =new HashMap<String,Object>();
		if(startTime!=null){
			params.put("startTime", startTime);
		}
		if(endTime!=null){
			params.put("endTime", endTime);
		}
		//查询最近30天的记录
		if(startTime==null&&endTime==null){
			params.put("endTime", new Date(System.currentTimeMillis()));
		
			Calendar date = Calendar.getInstance();
			Date currentDate = new Date(System.currentTimeMillis());
			date.setTime(currentDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 30);
			params.put("startTime", date.getTime());
		}
		if(categoryId!=0&&categoryId!=-1){
			params.put("categoryId", categoryId);
		}
		List<Income> incomeList=incomeBiz.findIncomeByPage(page, params);
		if(incomeList!=null&&incomeList.size()>0){
			jsonMap.put("flag", true);
			jsonMap.put("result", incomeList);
			jsonMap.put("page", page);
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("result", null);
			jsonMap.put("msg", "信息不存在");
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="income/countbydate",method=RequestMethod.POST)
	@ResponseBody
	public  String countbydate(Date startTime,Date endTime,Integer categoryId)  {
		Map<String, Object> params =new HashMap<String,Object>();
		if(startTime!=null){
			params.put("startTime", startTime);
		}
		if(endTime!=null){
			params.put("endTime", endTime);
		}
		//查询最近30天的记录
		if(startTime==null&&endTime==null){
			params.put("endTime", new Date(System.currentTimeMillis()));
			
			Calendar date = Calendar.getInstance();
			Date currentDate = new Date(System.currentTimeMillis());
			date.setTime(currentDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 30);
			params.put("startTime", date.getTime());
		}
		List<Income> incomeList=incomeBiz.countIncomeByDate(params);
		if(incomeList!=null&&incomeList.size()>0){
			jsonMap.put("flag", true);
			jsonMap.put("result", incomeList);
			jsonMap.put("page", page);
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("result", null);
			jsonMap.put("msg", "信息不存在");
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	

}
