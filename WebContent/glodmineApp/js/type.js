/**
 * Created by Administrator on 2015-09-25.
 */
$("#type").on("pageshow",function(e){
	loadlist4IncomeType();
    $("#typeForm").validate({
        rules:{
        	icategory:{
                required:true,
                rangelength:[2,8]
            },
            says:{
                rangelength:[0,30]
            }
        },
        //自定义验证信息
        messages:{
        	icategory:{
                required:"请输入类型名称",
                rangelength:$.validator.format("类型长度必须在{0}-{1} 字符以内")
            },
            says:{
                rangelength:$.validator.format("备注必须在{0}-{1} 字符以内")
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
        	savecategory();
        }
    });

});
function savecategory(){
    showLoading('信息保存中...','',false);
    $.ajax({
        type: 'POST',
        url:Config.root+ "category/save" ,
        data:$("#typeForm").serialize(),
        dataType: "json",
        success:function(data){
        	showLoading(data.msg,'',true);
            if(data.flag){
            	$("#typeForm")[0].reset();
            }
            setTimeout(hideLoading(), 1500);

        }
    });
}
function loadlist4IncomeType(){
	 var htmlc= $.ajax({
	        type: 'POST',
	        url:Config.root+ "category/getall4incometyp" ,
	        dataType: "json",
           async:"false",
	        success:function(data){
	            if(data.flag){
	                var json=eval(data.result);
	                var trData="<tr><td>工资</td><td>记录每月的工资收入情况</td></tr>";
	                var noDate="<tr><td colspan='2'>暂无数据</td></tr>";
	                $("table").remove("tr");
	                alert(json.length);
	            }

	        }
	    });
}