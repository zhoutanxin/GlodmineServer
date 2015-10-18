package com.doadway.glodmine.core.action;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doadway.framework.action.WWAction;
import com.doadway.glodmine.core.biz.CategoryBiz;
import com.doadway.glodmine.core.model.IncomeType;
import com.doadway.glodmine.core.model.SpeedType;
import com.doadway.glodmine.core.model.dto.CategoryDTO;
import com.doadway.glodmine.core.security.UserRealm.ShiroUser;
@Controller
public class CategoryController extends WWAction {
	@Resource
	CategoryBiz  categoryBiz;
	
	@RequestMapping(value="category/save",method=RequestMethod.POST)
	@ResponseBody
	public  String save(CategoryDTO category,Integer categoryflag)  {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			ShiroUser shiroUser=(ShiroUser)currentUser.getPrincipal();
			category.setMemberId(shiroUser.getId());
		}
		//1代表收入,2代表支出
		if(categoryflag==1){
			IncomeType incomeType=new IncomeType();
			BeanUtils.copyProperties(category, incomeType);
			if(categoryBiz.saveIncomeType(incomeType)){
				jsonMap.put("flag", true);
				jsonMap.put("msg", "信息已保存");
			}else{
				jsonMap.put("flag", false);
				jsonMap.put("msg", "保存失败");
			}

		} else{
			SpeedType speedType=new SpeedType();
			BeanUtils.copyProperties(category, speedType);
			if(categoryBiz.saveSpeedType(speedType)){
				jsonMap.put("flag", true);
				jsonMap.put("msg", "信息已保存");
			}else{
				jsonMap.put("flag", false);
				jsonMap.put("msg", "保存失败");
			}
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="category/update",method=RequestMethod.POST)
	@ResponseBody
	public  String update(CategoryDTO category,Integer categoryflag)  {
		//1代表收入,2代表支出
		if(categoryflag==1){
			IncomeType incomeType=categoryBiz.findIncomeTypeById(category.getId());
			incomeType.setIcategory(category.getIcategory());
			incomeType.setSays(category.getSays());
			if(categoryBiz.updateIncomeType(incomeType)){
				jsonMap.put("flag", true);
				jsonMap.put("msg", "信息已修改");
			}else{
				jsonMap.put("flag", false);
				jsonMap.put("msg", "修改失败");
			}
			
		} else{
			SpeedType speedType=categoryBiz.findSpeedTypeById(category.getId());
			speedType.setIcategory(category.getIcategory());
			speedType.setSays(category.getSays());
			if(categoryBiz.updateSpeedType(speedType)){
				jsonMap.put("flag", true);
				jsonMap.put("msg", "信息已修改");
			}else{
				jsonMap.put("flag", false);
				jsonMap.put("msg", "修改失败");
			}
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="category/delete",method=RequestMethod.POST)
	@ResponseBody
	public  String delete(CategoryDTO category,Integer categoryflag)  {
		//1代表收入,2代表支出
		if(categoryflag==1){
			IncomeType incomeType=categoryBiz.findIncomeTypeById(category.getId());
			if(incomeType!=null){
				categoryBiz.delIncomeTypeById(incomeType.getId());
				jsonMap.put("flag", true);
				jsonMap.put("msg", "信息已删除");
			}else{
				jsonMap.put("flag", false);
				jsonMap.put("msg", "信息不存在");
			}
			
		} else{
			SpeedType speedType=categoryBiz.findSpeedTypeById(category.getId());
			if(speedType!=null){
				categoryBiz.delSpeedTypeById(speedType.getId());
				jsonMap.put("flag", true);
				jsonMap.put("msg", "信息已删除");
			}else{
				jsonMap.put("flag", false);
				jsonMap.put("msg", "信息不存在");
			}
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="category/findIncome",method=RequestMethod.POST)
	@ResponseBody
	public  String findIncome(Integer categoryId)  {
			IncomeType incomeType=categoryBiz.findIncomeTypeById(categoryId);
			if(incomeType!=null){
				jsonMap.put("flag", true);
				jsonMap.put("result", incomeType);
			}else{
				jsonMap.put("flag", false);
				jsonMap.put("result", null);
			}
			
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="category/findSpeed",method=RequestMethod.POST)
	@ResponseBody
	public  String findSpeed(Integer categoryId)  {
		SpeedType speedType=categoryBiz.findSpeedTypeById(categoryId);
		if(speedType!=null){
			jsonMap.put("flag", true);
			jsonMap.put("result", speedType);
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("result", null);
		}
		
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="category/getall4incometyp",method=RequestMethod.POST)
	@ResponseBody
	public  String getall4incometyp()  {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			ShiroUser shiroUser=(ShiroUser)currentUser.getPrincipal();
			List<IncomeType> incomeTypeList = categoryBiz.findAll4IncomeTypeByMemberId(shiroUser.getId());
			if(incomeTypeList.size()>0){
				jsonMap.put("flag", true);
				jsonMap.put("msg", "收入类型列表查询成功");
				jsonMap.put("result", incomeTypeList);
			}else{
				jsonMap.put("flag", false);
				jsonMap.put("msg", "列表不存在");
				jsonMap.put("result", "");
			}
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("msg","登录用户不存在,请先登录");
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}
	@RequestMapping(value="category/getall4speedtyp",method=RequestMethod.POST)
	@ResponseBody
	public  String getall4speedtyp()  {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()){
			ShiroUser shiroUser=(ShiroUser)currentUser.getPrincipal();
			List<SpeedType> speedTypeList = categoryBiz.findAll4SpeedTypeByMemberId(shiroUser.getId());
			if(speedTypeList.size()>0){
				jsonMap.put("flag", true);
				jsonMap.put("msg", "收入类型列表查询成功");
				jsonMap.put("result", speedTypeList);
			}else{
				jsonMap.put("flag", false);
				jsonMap.put("msg", "列表不存在");
				jsonMap.put("result", "");
			}
		}else{
			jsonMap.put("flag", false);
			jsonMap.put("msg","登录用户不存在,请先登录");
		}
		return JSONObject.fromObject(jsonMap).toString(); 
	}

}
