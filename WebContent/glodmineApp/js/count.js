/**
 * Created by Administrator on 2015-09-25.
 */
$("#count").on("pageshow",function(e){
	countIncomeByDate();
	$("input[name='categoryflag']").change(function(){
		$("table tr").first().siblings().remove();
		if($(this).val()==1){
			countIncomeByDate();
		}else{
			countSpeedByDate();
			
		}
	});	
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
	                		$("table").append("<tr><td>"+json[i].icategory+"</td><td>￥"+json[i].imoney.toFixed(2)+"</td></tr>");
	                	}
	                }
	            }else{
	            	stop=false;
	            	$("table").append(noData);
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
						$("table").append("<tr><td>"+json[i].icategory+"</td><td>￥"+json[i].imoney.toFixed(2)+"</td></tr>");
					}
				}
			}else{
				stop=false;
				$("table").append(noData);
			}
			setTimeout(function(){hideLoading();}, 1000);
		}
	});
}