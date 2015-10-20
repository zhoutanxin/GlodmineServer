/**
 * Created by Administrator on 2015-09-25.
 */
//是否可滚动
stop=true;
//浏览器的高度加上滚动条的高度 
//totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());
//$(function(){
//		$(window).scroll(function () {
//            if ($("body").height() <= totalheight) {
//            	if(stop==true){ 
//					stop=false; 
//					quryList4Income();
//				}            	
//            }
//        });		
//});
var totalheight = 0; 

$(window).scroll( function() { 
//    console.log("滚动条到顶部的垂直高度: "+$(document).scrollTop()); 
//    console.log("页面的文档高度 ："+$(document).height());
//    console.log('浏览器的高度：'+$(window).height());
    totalheight =$(window).height() + $(window).scrollTop();     //浏览器的高度加上滚动条的高度 
    if ($(document).height() <= totalheight&&stop)     //当文档的高度小于或者等于总的高度的时候，开始动态加载数据
    { 
        //加载数据
    	quryList4Income();

    } 
}); 
$('body').perfectScrollbar({suppressScrollX:true});
$("#incomeSearch").on("pageshow",function(e){
	buildIncomeType();
	quryList4Income();
    $("#searchForm").validate({
        rules:{
            startTime:{
                required:true
            },
            endTime:{
                required:true
            }
        },
        //自定义验证信息
        messages:{
            startTime:{
                required:"请输入开始时间"
            },
            endTime:{
                required:"请输入结束时间"
            }
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
        	$("table tr").first().siblings().remove();
        	$("#currentPage").val(1);
        	quryList4Income();
        }
    });

});
function buildIncomeType(){
	doNotLogin();
	 var htmlc= $.ajax({
	        type: 'POST',
	        url:Config.root+ "category/getall4incometyp" ,
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
	            	$(".js-nodata").click(function(){$("#isource").selectmenu("close");location.href=location.href='type.html';});
	            }

	        }
	    });
}
function quryList4Income(){
	doNotLogin();
	showLoading('加载中...','',false);
	 var htmlc= $.ajax({
	        type: 'POST',
	        url:Config.root+ "income/querylist" ,
	        dataType: "json",
	        data:$("#searchForm").serialize(),
	        async : false,
	        success:function(data){
	        	var noData="<tr><td align='center' colspan=\"3\">暂无数据</td></tr>";
	            if(data.flag){
	                var json=eval(data.result);
	                if(json.length>0){
	                	for(var i=0;i<json.length;i++){
	                		$("table").append("<tr onclick=\"location.href='incomeItemDetail.html?id="+json[i].id+"';\"><td>"+new Date(json[i].idate.time).format("yyyy-MM-dd hh:mm:ss")+"</td><td>"+json[i].icategory+"</td><td>￥"+json[i].imoney.toFixed(2)+"</td></tr>");
	                	}
	                }
	                var page=eval(data.page);
	            	if($("#currentPage").val()==page.totalPage){
	            		stop=false;
	            	}else{
	            		stop=true;
	            	}
	            	$("#currentPage").val(page.currentPage+1);
	            }else{
	            	$("table").append(noData);
                }
	            setTimeout(function(){hideLoading();}, 1000);
	        }
	    });
}