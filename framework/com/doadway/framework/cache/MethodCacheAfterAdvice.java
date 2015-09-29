package com.doadway.framework.cache;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.ehcache.Cache;

import org.springframework.aop.AfterReturningAdvice;

public class MethodCacheAfterAdvice implements AfterReturningAdvice {

	 private Cache cache;  
	  
	 public void setCache(Cache cache) {  
	     this.cache = cache;  
	 }  
	  
	 public MethodCacheAfterAdvice() {  
	     super();  
	 }  
	  
	@SuppressWarnings("unchecked")
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {  
	     String className = arg3.getClass().getName();  
	     List list = cache.getKeys();
	     System.out.println("更新缓存!");
	     for(int i = 0;i<list.size();i++){  
	          String cacheKey = String.valueOf(list.get(i));  
	          if(cacheKey.startsWith(className)){  
	               cache.remove(cacheKey);  
	          }  
	     }  
	 }  

}
