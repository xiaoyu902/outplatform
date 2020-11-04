function call_AjaxRetObj_rl(url){
	 var retStatus;
     var retObj;
   	$.post(url, function(data, textStatus) {
   			retStatus = textStatus;
   			//alert(data)
   			retObj = eval("("+data+")");
   		});
   	if(retStatus=="success"){
   		 // alert(retObj.retcode);
   	    return retObj;
   	}else{
   	    return "error";  
   	}
}


function call_AjaxRetObj(url,indata){
	console.log("url:"+url);
	var retObj=null;
	  $.ajax({
			type: "post",
			url:url,
			cache: false,
			async : false,
			data:indata,
			beforeSend: function (request) {
		  		C1_showWaiting();
			},
			//���ݸ�ʽ��json������Ʒ��Ϣ
			success:function(data){//����json���
				retObj = eval("("+data+")");
		    },
		   error: function (XMLHttpRequest) {
		    			C1_closeWaiting();
	           return "error";    
		    },
		    complete:function(){
		    	C1_closeWaiting();
		    }
		});
	  C1_closeWaiting();
	  return retObj;
}


/*
 * function fileload(){
	 $.when(call_AjaxFileaSyncRetObj("/ExcelUpload","uploadFile")).done(function(data){
	 	 	var obj=eval(data);
     });
	}
 * */
function call_AjaxFileaSyncRetObj(url,ElementId){//ajax�ϴ��ļ�����,����������̷�ʹ��
		 var defer = $.Deferred();
			$.ajaxFileUpload({
					type:"post",
					url : url,
					secureuri : false,
					fileElementId : ElementId,
					dataType : "json",
					beforeSend: function (request) {
				  		C1_showWaiting();
					},
					async : true,
					data : "",
					success : function(data, status) {
						 defer.resolve(data)
						
					},
					error : function(data, status, e) {
						alert("error");
					}
				})
			C1_closeWaiting();
			return defer.promise();

}

function call_AjaxSyncRetObj(url){
	var defer = $.Deferred();
	  $.ajax({
			type: "post",
			url:url,
			cache: false,
			beforeSend: function (request) {
		  		C1_showWaiting();
			},
			async : true,
			success:function(data){
				 defer.resolve(data)
				 C1_closeWaiting();
		    },error: function (XMLHttpRequest) {
		    	C1_closeWaiting();
		    	layer.msg("交易异常");
	        },complete:function(){
	        	 window.setTimeout("C1_closeWaiting()",100);
		    }
		});
	  return defer.promise();
}

function call_AjaxJsonSyncRetObj(url,json){
	var defer = $.Deferred();
	  $.ajax({
			type:"post",
			url:url,
			cache: false,
			data: JSON.stringify(json),
			contentType: "application/json;charset=UTF-8",
            dataType:"json",
			beforeSend: function (request) {
		  		C1_showWaiting();
			},
			async : true,
			success:function(data){
				 defer.resolve(data)
				 C1_closeWaiting();
		    },error: function (XMLHttpRequest) {
		    	C1_closeWaiting();
		    	layer.msg("交易异常");
	        },complete:function(){
	        	 window.setTimeout("C1_closeWaiting()",100);
		    }
		});
	  return defer.promise();
}


function call_AjaxRetJson(url,indata){
	console.log("url:"+url);
	var retObj=null;
	  $.ajax({
			type:"post",
			url:url,
			cache: false,
			async : false,
			contentType:'application/json',
			data:indata,
			beforeSend: function (request) {
		  		C1_showWaiting();
			},
			//���ݸ�ʽ��json������Ʒ��Ϣ
			success:function(data){//����json���
				retObj = eval("("+data+")");
		    },
		   error: function (XMLHttpRequest) {
		    			C1_closeWaiting();
	           return "error";    
		    },
		    complete:function(){
		    	C1_closeWaiting();
		    }
		});
	  C1_closeWaiting();
	  return retObj;
}




//��Ҫ�첽(Ĭ���첽���������ó�ͬ��)������ò���Ajax���õķ���ֵ��������ñ���Ҫ
$.ajaxSetup({   
   async : false  
});
