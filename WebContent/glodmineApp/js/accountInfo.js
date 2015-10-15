/**
 * Created by Administrator on 2015-09-25.
 */

function loadMemberInfo(){
	 var htmlc= $.ajax({
	        type: 'GET',
	        url:Config.root+ "memberInfo" ,
	        dataType: "json",
            async:"false",
	        success:function(data){
	            if(data.flag){
	                var json=eval(data.result);
                    $("#niceName").val(json.niceName);
                    $("#mobilePhone").val(json.mobilePhone);
                    $("#imail").val(json.imail);
                    $("#realName").val(json.realName);
                    if(json.brithday!=null){
                    	$("#brithday").val(new Date(json.brithday.time).format("yyyy-MM-dd"));
                    }
                    if(json.gender==1){
                        $("#male").trigger("click");
                    }else{
                        $("#female").trigger("click");
                    }
	            }

	        }
	    });
}
$("#accountInfo").on("pageshow",function(e){
	loadMemberInfo();
    $("#accountInfoForm").validate({
        rules:{
            niceName:{
                required:true,
                rangelength:[0,10]
            },
            realName:{
                rangelength:[0,8]
            }
        },
        //自定义验证信息
        messages:{
            niceName:{
                required:"请输入昵称",
                rangelength:$.validator.format("昵称必须在{0}-{1} 字符以内")
            },
            realName:{
                rangelength:$.validator.format("真实姓名必须在{0}-{1} 字符以内")
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
        	updInfo();
        }
    });

});
function updInfo(){
    showLoading('信息修改中...','',false);
    $.ajax({
        type: 'POST',
        url:Config.root+ "updInfo" ,
        data:$("#accountInfoForm").serialize(),
        dataType: "json",
        success:function(data){
            if(data.flag){
            	loadMemberInfo();
            }
            showLoading(data.msg,'',true);
            setTimeout(hideLoading(), 1500);

        }
    });
}