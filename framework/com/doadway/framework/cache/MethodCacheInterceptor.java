package com.doadway.framework.cache;

import static com.doadway.framework.util.MyBeanUtils.getFieldValue;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.doadway.framework.util.MyBeanUtils;

public class MethodCacheInterceptor implements MethodInterceptor {
//	private static final Log logger = LogFactory.getLog(MethodCacheInterceptor.class);
	private Cache cache; 
    public void setCache(Cache cache) {  
        this.cache = cache;  
    }  
  
    public MethodCacheInterceptor() {  
        super();  
    }  
    /** 
     * 拦截Service/DAO的方法，并查找该结果是否存在，如果存在就返回cache中的值， 
     * 否则，返回数据库查询结果，并将查询结果放入cache 
     */  
    public Object invoke(MethodInvocation invocation) throws Throwable {  
        String targetName = invocation.getThis().getClass().getName();  
        String methodName = invocation.getMethod().getName();  
        Object[] arguments = invocation.getArguments();  
        Object result;  
        String cacheKey = getCacheKey(targetName, methodName, arguments);  
        Element element = cache.get(cacheKey);  
  
        if (element == null) {  
        	System.out.println("创建缓存Key:"+cacheKey);  
            result = invocation.proceed();  
            element = new Element(cacheKey, (Serializable) result);  
            cache.put(element);  
        }  
        return element.getValue();  
    }  
  
    /** 
     * 获得cache key的方法，cache key是Cache中一个Element的唯一标识 
     * cache key包括 包名+类名+方法名，如com.co.cache.service.UserServiceImpl.getAllUser 
     */  
    private String getCacheKey(String targetName, String methodName, Object[] arguments) {  
        StringBuffer sb = new StringBuffer();  
        sb.append(targetName).append(".").append(methodName);  
        if ((arguments != null) && (arguments.length != 0)) {  
        	sb.append(".");
            for (int i = 0; i < arguments.length; i++) {  
            	if(arguments[i]!=null){
            		String clsName= arguments[i].getClass().getName();
            		if(clsName.endsWith("Example")){
            			Object  oredCriObj= MyBeanUtils.getFieldValue(arguments[i], "oredCriteria");
            			Object[]  elementObjList = (Object[])getFieldValue(oredCriObj, "elementData");
            			for(int m=0;m<elementObjList.length;m++){
            				if(elementObjList[m]!=null){
            					Object  criteriaObj   = MyBeanUtils.getFieldValue(elementObjList[m], "criteria");
            					Object[]  criteriaElementDataList=(Object[])MyBeanUtils.getFieldValue(criteriaObj, "elementData");
            					for(Object currentCriObj:criteriaElementDataList){
            						if(currentCriObj!=null){
            							Object  condition = MyBeanUtils.getFieldValue(currentCriObj,"condition");
            							if(condition!=null){
            								sb.append(condition.toString());
            							}
            							Object  conditionValue = MyBeanUtils.getFieldValue(currentCriObj,"value");
            							if(conditionValue!=null){
            								sb.append(conditionValue.toString());
            							}
            						}
            					}
            				}
            			}
            		} else if(clsName.endsWith("Map")){
            			Object[] mapTable =(Object[] )MyBeanUtils.getFieldValue(arguments[i], "table");
            			for(Object map:mapTable){
            				if(map!=null){
            					Object keyObj=MyBeanUtils.getFieldValue(map, "key");
            					Object valueObj=MyBeanUtils.getFieldValue(map, "value");
            					sb.append(keyObj.toString()+"."+valueObj.toString());
            				}
            			}
            		}else{
            			sb.append(arguments[i]);
            		}
            	}
            }  
        }
        return sb.toString();  
    }
 
}
