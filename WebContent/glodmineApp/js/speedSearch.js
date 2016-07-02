/**
 * Created by Administrator on 2015-09-25.
 */
//是否可滚动
stop=true;
var totalheight = 0; 

//$(window).scroll( function() { 
//    console.log("滚动条到顶部的垂直高度: "+$(document).scrollTop()); 
//    console.log("页面的文档高度 ："+$(document).height());
//    console.log('浏览器的高度：'+$(window).height());
//    totalheight =$(window).height() + $(window).scrollTop();     //浏览器的高度加上滚动条的高度 
//    if ($(document).height() <= totalheight&&stop)     //当文档的高度小于或者等于总的高度的时候，开始动态加载数据
//    { 
//        //加载数据
//    	quryList4Speed();
//
//    } 
//}); 
function buildDate(){
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.init={
		theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式 
        mode: 'mixed', //日期选择模式
		lang:'zh',
        startYear:currYear - 10, //开始年份
        endYear:currYear + 10 //结束年份
	};
	$("#startTime").val('').scroller('destroy').scroller($.extend(opt['date'], opt['init']));
	$("#endTime").val('').scroller('destroy').scroller($.extend(opt['date'], opt['init']));
}


$(document).on("pageinit","#speedSearch",function(){
	  quryList4Speed();
/*	  $("#speedSearch").on("swipe",function(){
		  var tmpPage=parseInt($("#currentPage").val());
		  tmpPage+=1;
		  $("#currentPage").val(tmpPage);
		  quryList4Speed();
	  });                       
*/	  $("#footer").on("click",function(){
		  var tmpPage=parseInt($("#currentPage").val());
		  tmpPage+=1;
		  $("#currentPage").val(tmpPage);
		  quryList4Speed();
	  });                       
});
$("#speedSearch").on("pageshow",function(e){
    jQuery.validator.addMethod("notEqualZero", function(value, element) {
    	return this.optional(element) || value!=0;
    }, "非法输入值");	
    $('body').perfectScrollbar({suppressScrollX:true});
    buildDate();
	buildSpeedType();
    $("#speedForm").validate({
        rules:{
            startTime:{
                required:true
            },
            endTime:{
                required:true
            },
        },
        //自定义验证信息
        messages:{
            startTime:{
                required:"请输入开始时间"
            },
            endTime:{
                required:"请输入结束时间"
            },
        },
        showErrors: function(errorMap, errorList) {
            this.defaultShowErrors();
            for(var i = 0; i < errorList.length; i++) {
                $(errorList[i].element).one("blur", function() {
                    $("label.error[for='" + (this.id ? this.id : this.name) + "']").remove();
                });
            }
        },
        submitHandler:function(form){
        	$("#colllist").empty();
        	$("table tr").first().siblings().remove();
        	$("#currentPage").val(1);
        	quryList4Speed();
        }
    });

});
function buildSpeedType(){
	doNotLogin();
	 var htmlc= $.ajax({
	        type: 'POST',
	        url:Config.root+ "category/getall4speedtyp" ,
	        dataType: "json",
           async:"false",
	        success:function(data){
	            if(data.flag){
	                var json=eval(data.result);
	                $("#categoryId").empty();
	                $("#categoryId").append("<option value='0'>不限</option>");
	                if(json.length>0){
	                	for(var i=0;i<json.length;i++){
	                		$("#categoryId").append("<option value='"+json[i].id+"'>"+json[i].icategory+"</option>");
	                	}
	                	$("#categoryId").selectmenu("refresh", true);
	                }
	            }else{
	            	$(".js-nodata").click(function(){$("#categoryId").selectmenu("close");location.href=location.href='type.html';});
	            }

	        }
	    });
}
function quryList4Speed(){
	doNotLogin();
	showLoading('加载中...','',false);
	 var htmlc= $.ajax({
	        type: 'POST',
	        url:Config.root+ "speed/querylist" ,
	        dataType: "json",
	        data:$("#speedForm").serialize(),
	        async : false,
	        success:function(data){
	        	var noData="<tr><td align='center' colspan=\"2\">暂无数据</td></tr>";
	            if(data.flag){
	                var json=eval(data.result);
	                var page=eval(data.page);
	            	if(parseInt($("#currentPage").val()-1)==page.totalPage){
	            		$("#currentPage").val(page.totalPage);
	            		showLoading('已经到最后一页','',false);
	            		setTimeout(function(){hideLoading();}, 1000);
	            		return;
	            	}
	                if(json.length>0){
	                	for(var i=0;i<json.length;i++){
	                		//$("table").append("<tr onclick=\"location.href='speedItemDetail.html?id="+json[i].id+"';\"><td>"+new Date(json[i].idate.time).format("yyyy-MM-dd hh:mm:ss")+"</td><td>"+json[i].icategory+"</td><td>￥"+json[i].imoney.toFixed(2)+"</td></tr>");
	                		
	                		var collspan="<div class=\"collspan"+json[i].id+"\" data-role=\"collapsible\">"+
			                "<h1><div class=\"ui-grid-a\"><div class=\"ui-block-a\">"+new Date(json[i].idate.time).format("yyyy-MM-dd")+"</div><div class=\"ui-block-b green\">- "+json[i].imoney.toFixed(2)+"</div></div></h1>"+
			                "<p>"+
			                "<table class=\"bordered\" width=\"100%\" cellspacing=\"0\">"+
			                "<tr><td class=\"ui-block-2\">日期:</td><td class=\"ui-block-4\"><span id=\"idate\">"+new Date(json[i].idate.time).format("yyyy-MM-dd hh:mm:ss")+"</span></td><td class=\"ui-block-2\">金额:</td><td class=\"ui-block-2 green\"><span id=\"imoney\">"+json[i].imoney.toFixed(2)+"</span></td></tr>"+        
			                "<tr><td  class=\"ui-block-2\">来源:</td><td colspan=\"3\"><span id=\"icategory\">"+json[i].icategory+"</span></td></tr>"+        
			                "<tr><td  class=\"ui-block-2\">描述:</td><td colspan=\"3\"><span id=\"imemo\">"+json[i].imemo+"</span></td></tr>"+        
			                "</table>"+    
			                "<a type=\"button\" href=\"#alertInfo\" class=\"delBtn"+json[i].id+"\" data-rel=\"dialog\" onclick=\"setDelId('"+json[i].id+"')\">清除</a>"+     
			                "</p>"+
			                "</div>";
	                		$("#colllist").append(collspan).trigger("create");
	                		//$(".collspan").collapsibleset("refresh");
	                		$(".delBtn"+json[i].id+"").button();
	                	}
	                }
	            }else{
	            	$("table tr").first().siblings().remove();
	            	$("table").append(noData);
	            	$("#colllist").empty();
                }
	            setTimeout(function(){hideLoading();}, 1000);
	        }
	    });
}
function setDelId(speedId){
	$("#delId").val(speedId);
}
function delSpeed(){
	var delId=$("#delId").val();
	if(delId==''){
		return;
	}
	doNotLogin();
    showLoading('信息删除中...','',false);
    var htmlc=$.ajax({
        type: 'POST',
        url:Config.root+ "speed/delete" ,
        data:{"id":delId},
        dataType: "json",
        success:function(data){
        	showLoading(data.msg,'',true);
            if(data.flag){
            	setTimeout(function(){hideLoading()}, 1500);
            	location.href='speedSearch.html';
            }

        },
        error:function(XMLHttpRequest){
        	if(XMLHttpRequest.status==404){
        		showLoading('404错误','',true);
        		setTimeout(function(){hideLoading()}, 2000);
        		
        	};
        	
        }
    });
}