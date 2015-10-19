/**
 * Created by Administrator on 2015-09-25.
 */
//是否可滚动
stop=true;
//浏览器的高度加上滚动条的高度 
totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());
$(function(){
		$(window).scroll(function () {
            if ($("body").height() <= totalheight) {
            	if(stop==true){ 
					stop=false; 
					quryList4Income();
				}            	
            }
        });		
});
$("#incomeSearch").on("pageshow",function(e){
	buildIncomeType();
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
        	quryList4Income();
        }
    });

});
function buildIncomeType(){
	doNotLogin();
	showLoading('加载中...','',false);
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
	            showLoading(data.msg,'',false);
	            setTimeout(function(){hideLoading();}, 1500);

	        }
	    });
}
function quryList4Income(){
	doNotLogin();
	 var htmlc= $.ajax({
	        type: 'POST',
	        url:Config.root+ "income/querylist" ,
	        dataType: "json",
	        data:$("#searchForm").serialize(),
	        success:function(data){
	        	var noData="<tr><td align='center' colspan=\"3\">暂无数据</td></tr>";
	        	$("table tr").first().siblings().remove();
	            if(data.flag){
	                var json=eval(data.result);
	                if(json.length>0){
	                	for(var i=0;i<json.length;i++){
	                		$("table tr").first().after("<tr onclick=\"location.href='incomeItemDetail.html?id="+json[i].id+"';\"><td>"+new Date(json[i].idate.time).format("yyyy-MM-dd")+"</td><td>"+json[i].icategory+"</td><td>"+json[i].imoney+"</td></tr>");
	                	}
	                }
	                stop=true;
	            }else{
                	$("table tr").first().after(noData);
                }

	        }
	    });
}