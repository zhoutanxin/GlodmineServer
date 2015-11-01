/**
 * Created by Administrator on 2015-09-25.
 */
$("#speedDetail").on("pageshow",function(e){
	loadSpeed();
});
function loadSpeed(){
	doNotLogin();
	 var htmlc= $.ajax({
	        type: 'POST',
	        url:Config.root+ "speed/get" ,
	        dataType: "json",
	        async:"false",
	        data:{'id':$.getUrlParam("id")},
	        success:function(data){
	            if(data.flag){
	            	var json=eval(data.result);
	            	$("#idate").text(new Date(json.idate.time).format("yyyy-MM-dd hh:mm:ss"));
	            	$("#imoney").text(json.imoney.toFixed(2));
	            	$("#icategory").text(json.icategory);
	            	$("#imemo").text(json.imemo);
	            }
	        }
	    });
}

function delSpeed(){
	doNotLogin();
    showLoading('信息删除中...','',false);
    var htmlc=$.ajax({
        type: 'POST',
        url:Config.root+ "speed/delete" ,
        data:{"id":$.getUrlParam("id"),"categoryflag":$.getUrlParam("id")},
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
