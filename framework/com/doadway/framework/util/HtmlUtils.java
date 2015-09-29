package com.doadway.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils{

	//根据提供的文本,得到当前页里面的所有文本内容中的URL链接值列表;
	public static Set<String> getAllHref(String str){
		//用Set集合避免重复采集链接内容;
		Set<String> hrefList = new LinkedHashSet<String>();
		//匹配<a>标签中的href;
		Pattern p = Pattern.compile("(?i)<a[^>]*?href=\"([^\"]*)\"[^>]*>");
		Matcher m = p.matcher(str);
		while(m.find()){
			hrefList.add(m.group(1));
			System.out.println(m.group(1));
		}		
		
		return hrefList;
		
	}
	
	//根据URL和读取编码返回一个文本操作;
	public static String urlToText(URL url,String encode){
		String text="";
		//URL文本化;
		try {
			URLConnection urlC = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(urlC.getInputStream(),encode));
			String tempRead = br.readLine();
			while(tempRead!=null){
				text=text+tempRead+"\n";
				tempRead = br.readLine();
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return text;
		
	}

	//把所有的html代码转成纯txt的文件;
	public static String htmlToText(String str){
		
//		  str = str.replaceAll("<a[^>]*>","");   
//		  str = str.replaceAll("</a.*>",""); 
//		  str = str.replaceAll("<h1[^>]*>","");   
//		  str = str.replaceAll("</h1>(?i)","");  
		  str = str.replaceAll("<div[^>]*>(?i)","<div>");   
		  str = str.replaceAll("</div>(?i)","</div>");
		  str = str.replaceAll("<font[^>]*>(?i)","");   
		  str = str.replaceAll("</font>(?i)","");
		  str = str.replaceAll("<p[^>]*>(?i)","<p>");   
		  str = str.replaceAll("</p>(?i)","</p>");
		  str = str.replaceAll("<span[^>]*>(?i)","");   
		  str = str.replaceAll("</span>(?i)","");
		  str = str.replaceAll("<?xml[^>]*>(?i)","");
		  str = str.replaceAll("</?xml>(?i)","");
		  str = str.replaceAll("<o:p[^>]*>(?i)","");
		  str = str.replaceAll("</o:p>(?i)","");
		  str = str.replaceAll("<u[^>]*>(?i)","");
		  str = str.replaceAll("</u>(?i)","");
		  str = str.replaceAll("<b[^>]*>(?i)","");
		  str = str.replaceAll("</b>(?i)",""); 
		  str = str.replaceAll("<meta[^>]*>(?i)","");
		  str = str.replaceAll("</meta>(?i)","");
		  str = str.replaceAll("<!--[^>]*-->(?i)","");//注释内容  
		  str = str.replaceAll("<p[^>]*-->(?i)","");//注释内容       
		  str = str.replaceAll("style=.+?['|\"](?i)","");//去除样式   
		  str = str.replaceAll("class=.+?['|\"](?i)","");//去除样式   
		  str = str.replaceAll("(?i)d=.+?['|\"](?i)","");//去除样式      
		  str = str.replaceAll("/lang=.+?['|\"](?i)","");//去除样式       
		  str = str.replaceAll("/width=.+?['|\"](?i)","");//去除样式    
		  str = str.replaceAll("/height=.+?['|\"](?i)","");//去除样式    
		  str = str.replaceAll("/border=.+?['|\"](?i)","");//去除样式    
		  str = str.replaceAll("/face=.+?['|\"](?i)","");//去除样式 
		  str = str.replaceAll("/face=.+?['|\"]/","");
		  str = str.replaceAll("/face=.+?['|\"]/","");
//		  str = str.replaceAll( "&nbsp;","");
		     return str;		
	}
	    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	    
	    public static String delHTMLTag(String htmlStr) {
	        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	        Matcher m_script = p_script.matcher(htmlStr);
	        htmlStr = m_script.replaceAll(""); // 过滤script标签

	        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	        Matcher m_style = p_style.matcher(htmlStr);
	        htmlStr = m_style.replaceAll(""); // 过滤style标签

	        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	        Matcher m_html = p_html.matcher(htmlStr);
	        htmlStr = m_html.replaceAll(""); // 过滤html标签

	        return htmlStr.trim(); // 返回文本字符串
	    }
	    public static String delScriptAndStyle(String htmlStr) {
	    	Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	    	Matcher m_script = p_script.matcher(htmlStr);
	    	htmlStr = m_script.replaceAll(""); // 过滤script标签
	    	
	    	Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	    	Matcher m_style = p_style.matcher(htmlStr);
	    	htmlStr = m_style.replaceAll(""); // 过滤style标签
	    	
	    	htmlStr = htmlStr.replaceAll("<font[^>]*>(?i)","");  //去掉字体 
	    	htmlStr = htmlStr.replaceAll("</font>(?i)","");
	    	
	    	htmlStr = htmlStr.replaceAll("<a[^>]*>","");   //去掉<a>标签
	    	htmlStr = htmlStr.replaceAll("</a.*>",""); 
	    	return htmlStr.trim(); // 返回文本字符串
	    }
}
