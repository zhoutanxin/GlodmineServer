/**
 * Created by Administrator on 2015-09-25.
 */
Config={};
Config.domain="http://localhost:8080";
Config.ctx="GlodmineSever";
Config.root=Config.domain+"/"+Config.ctx+"/";
Config.login="login.html";
Config.ifLogin=function(){
    var flg=false;
    $.ajax({
        type: 'GET',
        url:Config.root+ "chklogin" ,
        dataType: "json",
        async:false,
        crossDomain: true,
        success:function(data){
            if(data==true){
                flg= true;
            }
        }
    });
    return flg;
};
Config.ifLogin();
Config.COOKIE_VALID_CODE ="valid_code";
$(document).bind("mobileinit", function() {
    $.mobile.ajaxEnabled=true;
});
Date.prototype.format =function(format)
{
	var o = {
	"M+" : this.getMonth()+1, //month
	"d+" : this.getDate(), //day
	"h+" : this.getHours(), //hour
	"m+" : this.getMinutes(), //minute
	"s+" : this.getSeconds(), //second
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter
	"S" : this.getMilliseconds() //millisecond
	}
	if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
	(this.getFullYear()+"").substr(4- RegExp.$1.length));
	for(var k in o)if(new RegExp("("+ k +")").test(format))
	format = format.replace(RegExp.$1,
	RegExp.$1.length==1? o[k] :
	("00"+ o[k]).substr((""+ o[k]).length));
	return format;
}