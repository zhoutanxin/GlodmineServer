/**
 * Created by Administrator on 2015-09-25.
 */
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
        startYear:currYear - 5, //开始年份
        endYear:currYear + 0 //结束年份
	};
	strDate = new Date().format("yyyy-MM-dd");
	now = new Date(strDate.replace(/\-/g,"/"));
	perMonth =new Date( now.setMonth(now.getMonth() - 1));
	$("#startTime").val(perMonth.format("yyyy-MM-dd")).scroller('destroy').scroller($.extend(opt['date'], opt['init']));
	$("#endTime").val(new Date().format("yyyy-MM-dd")).scroller('destroy').scroller($.extend(opt['date'], opt['init']));
}
$("#count").on("pageshow",function(e){
	countIncomeByDate();
	countSpeedByDate();
	buildDate();
	loadlist4IncomeType();
	loadlist4SpeedType();
	$("body").perfectScrollbar({suppressScrollX:true});
//	$("input[name='categoryflag']").change(function(){
//		$("table tr").first().siblings().remove();
//		if($(this).val()==1){
//			countIncomeByDate();
//		}else{
//			countSpeedByDate();
//		}
//	});	
	
//	$("#income").click(function(){
//		$("table tr").first().siblings().remove();
//		countIncomeByDate();
//		$("input[name='categoryflag']").val("1");
//		$(this).prop("checked", true).checkboxradio("refresh");		
//	});
	
//	$("#speed").click(function(){
//		$("table tr").first().siblings().remove();
//		countSpeedByDate();
//		$("input[name='categoryflag']").val("2");
//		$(this).prop("checked", true).checkboxradio("refresh");		
//	});
    $("#countForm").validate({
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
    		if($("input[name='categoryflag']").val()==1){
    			countIncomeByDate();
    		}else{
    			countSpeedByDate();
    		}
        }
    });
});
function countIncomeByDate(){
	doNotLogin();
	showLoading('加载中...','',false);
	 var htmlc= $.ajax({
	        type: 'POST',
	        url:Config.root+ "income/countbydate" ,
	        dataType: "json",
	        data:$("#countForm").serialize(),
	        async : false,
	        success:function(data){
	        	var noData="<tr><td align='center' colspan=\"2\">暂无数据</td></tr>";
	            if(data.flag){
	                var json=eval(data.result);
	                if(json.length>0){
	                	for(var i=0;i<json.length;i++){
	                		$("tr4Income").after("<tr><td>"+json[i].icategory+"</td><td>￥"+json[i].imoney.toFixed(2)+"</td></tr>");
	                	}
	                }
	            }else{
	            	stop=false;
	            	$("tr4Income").after(noData);
                }
	            setTimeout(function(){hideLoading();}, 1000);
	        }
	    });
}
function countSpeedByDate(){
	doNotLogin();
	showLoading('加载中...','',false);
	var htmlc= $.ajax({
		type: 'POST',
		url:Config.root+ "speed/countbydate" ,
		dataType: "json",
		data:$("#countForm").serialize(),
		async : false,
		success:function(data){
			var noData="<tr><td align='center' colspan=\"2\">暂无数据</td></tr>";
			if(data.flag){
				var json=eval(data.result);
				if(json.length>0){
					for(var i=0;i<json.length;i++){
						$("tr4Speed").after("<tr><td>"+json[i].icategory+"</td><td>￥"+json[i].imoney.toFixed(2)+"</td></tr>");
					}
				}
			}else{
				stop=false;
				$("tr4Speed").after(noData);
			}
			setTimeout(function(){hideLoading();}, 1000);
		}
	});
}
function loadlist4IncomeType(){
	doNotLogin();
	 var htmlc= $.ajax({
	        type: 'POST',
	        url:Config.root+ "category/getall4incometyp" ,
	        dataType: "json",
           async:"false",
	        success:function(data){
	        	var noData="<div style='padding-left: 50px;'>暂无数据</div>"
	        	$("#incomeFieldset").empty();
	            if(data.flag){
	                var json=eval(data.result);
	                if(json.length>0){
	                	for(i=0;i<json.length;i++){
	                		//$("#incomeFieldset").append("<tr onclick=\"location.href='typeItem.html?categoryId="+json[i].id+"&categoryflag=1';\"><td>"+json[i].icategory+"</td><td>"+json[i].says+"</td></tr>");
	                		$("#incomeFieldset").append("<input type=\"checkbox\" checked='true' name=\"checkboxIncome\" id=\"checkbox-v-"+json[i].id+"\">"+
                            "<label for=\"checkbox-v-"+json[i].id+"\">"+json[i].icategory+"</label>").trigger("create");
	                	}
	                	$("#incomeFieldset label").first().addClass("ui-first-child");
	                	$("#incomeFieldset label").last().addClass("ui-last-child");
	                	
	                }
	            }else{
                	$("#incomeFieldset").append(noData);
                }

	        }
	    });
}
function loadlist4SpeedType(){
	doNotLogin();
	var htmlc= $.ajax({
		type: 'POST',
		url:Config.root+ "category/getall4speedtyp" ,
		dataType: "json",
		async:"false",
		success:function(data){
			var noData="<div style='padding-left: 50px;'>暂无数据</div>";
			$("#speedFieldset").empty();
			if(data.flag){
				var json=eval(data.result);
				if(json.length>0){
					for(i=0;i<json.length;i++){
						//$("table tr").first().after("<tr onclick=\"location.href='typeItem.html?categoryId="+json[i].id+"&categoryflag=2';\"><td>"+json[i].icategory+"</td><td>"+json[i].says+"</td></tr>");
						$("#speedFieldset").append("<input type=\"checkbox\" checked='true' name=\"checkboxSpeed\" id=\"checkbox-v-"+json[i].id+"\">"+
	                            "<label for=\"checkbox-v-"+json[i].id+"\">"+json[i].icategory+"</label>").trigger("create");
					}
                	$("#speedFieldset label").first().addClass("ui-first-child");
                	$("#speedFieldset label").last().addClass("ui-last-child");

				}
			}else{
				$("#speedFieldset").append(noData);
			}
			
		}
	});
}