/**
 * Created by Administrator on 2015-09-25.
 */
Config={};
Config.domain="http://localhost:8080";
Config.ctx="GlodmineSever";
Config.root=Config.domain+"/"+Config.ctx+"/";
Config.login="login.html";
Config.ifLogin=	false;
function doNotLogin(){
	if(!Config.ifLogin){
		$.mobile.changePage(Config.login,{transition: 'flip'});
	}
}
Config.COOKIE_VALID_CODE ="valid_code";
$(document).bind("mobileinit", function() {
    $.mobile.ajaxEnabled=false;
});
$(function(){
	Config.ifLogin=getLoginFlg();	
	(function ($) {
	    $.getUrlParam = function (name) {
	        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	        var r = window.location.search.substr(1).match(reg);
	        if (r != null) return unescape(r[2]); return null;
	    }
	})(jQuery);
});
function getLoginFlg(){
    var flg=false;
    $.ajax({
        type: 'GET',
        url:Config.root+ "chklogin" ,
        dataType: "json",
        async:false,
        success:function(data){
            if(data==true){
                flg= true;
            }
        }
    });
    return flg;
}
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
function showLoading(txt,htm,txtonly){
    $.mobile.loading('show', {
        text: txt, //加载器中显示的文字
        textVisible: true, //是否显示文字
        theme: 'b',        //加载器主题样式a-e
        textonly: txtonly,   //是否只显示文字
        html: htm          //要显示的html内容，如图片等
    });
}
function hideLoading(){
	$.mobile.loading('hide');
}