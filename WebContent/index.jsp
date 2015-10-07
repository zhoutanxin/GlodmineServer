<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业信息管理系统_用户登录</title>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js">
</script>
<script type="text/javascript">
	function login(){
		$.ajax({
		    type: 'POST',
		    url: "login" ,
		    data:$("#loginForm").serialize(),
		    dataType: "json",
		    success:function(data){
		    	if(data.flag){
		    		var json=eval(data.msg)
		    		alert("登录者:"+json.niceName);
		    	}else{
		    		alert("登录失败:"+data.msg);
		    	}
		    }
		});
	}
	function loginout(){
		$.ajax({
		    type: 'POST',
		    url: "loginout" ,
		    dataType: "json",
		    success:function(data){
		    	var json=eval(data)
		    	alert("退出消息:"+json.msg);
		    } 
		});
	}
</script>
</head>
<body style="text-align: center;">
<form  id="loginForm"  method="post" action="javascript:login()">
	<input type="text" name="mobilePhone" value="" /><br/><br/>
	<input type="password" name="password" value="" /><br/><br/>
	<input type="submit"  value="登录"/>
	<input type="button" onclick="loginout();"  value="退出"/>
</form>
</body>
</html>
