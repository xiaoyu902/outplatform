function C1_gotoUrl(url){
	if(url==-1){
		window.history.back(-1);
	}else{
		window.location.href=url;
	}
}


function C1_ages(str){   
	var r  = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);  
if(r==null) return 0;     
var  d = new Date(r[1],r[3]-1,r[4]);     
if(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]){   
	var   Y   =   new   Date().getFullYear();   
  return Y -r[1];   
}   
} 

/*展示*/
function C1_showdiv() {            
    document.getElementById("bg").style.display = "block";
    document.getElementById("show").style.display = "block";
}
/*隐藏*/
function C1_hidediv() {
    document.getElementById("bg").style.display = 'none';
    document.getElementById("show").style.display = 'none';
}

function C1_showCry(str){
	$("#show").addClass("Divcss_waiting"); 
	$("#show").html("<span style=‘font-size:20px;font-weight:bold'>"+str+"</span><br><img style='float:width:100px;height:100px' src='../../images/cry.jpg'>");
	C1_showdiv();
}

function C1_ShowLength(str){
	var size=50;//展示内容
	if(str.length>size){
		str=str.substring(0,size)+"...";
	}
	return str;
}

function C1_showMessageInfo(info){
	/*var height=document.body.clientHeight;
		height=(height/2)-50;
		alert(height)
		layer.msg(info,{offset: [height+'px']});
		*/
	layer.alert(info);
	//	layer.msg(info);
}

function C1_showFuncWaiting(func){
	C1_showWaiting();
	window.setTimeout(func, 100);
}

function C1_showWaiting(){
	layer.ready(function(){
		layer.load(2,{
			  shade: [0.5,'#fff'] //0.1透明度的白色背景
		});
	})
	//  window.setTimeout(funcname, 100);
}

function C1_closeWaiting(){
   layer.closeAll('loading');
}


function C1_getDate(str){//str:日期之间的分隔号比如 -  /
	 var date = new Date();
     var seperator1 = str;
     var year = date.getFullYear();
     var month = date.getMonth() + 1;
     var strDate = date.getDate();
     if (month >= 1 && month <= 9) {
         month = "0" + month;
     }
     if (strDate >= 0 && strDate <= 9) {
         strDate = "0" + strDate;
     }
     var currentdate = year + seperator1 + month + seperator1 + strDate;
     return currentdate;
}

function C1_getTime(){
	 var now = new Date();  
	    var year = now.getFullYear();       //年  
	    var month = now.getMonth() + 1;     //月  
	    var day = now.getDate();            //日    
	    var hh = now.getHours();            //时  
	    var mm = now.getMinutes();          //分  
	    var ss=now.getSeconds();            //秒  
	         
	    var clock ="";
	    /*
	    clock += year + "-";  
	         
	    if(month < 10) clock += "0";         
	    clock += month + "-";  
	         
	    if(day < 10) clock += "0";   
	    clock += day + " ";  */
	         
	    if(hh < 10) clock += "0";  
	    clock += hh + "";  
	  
	    if (mm < 10) clock += '0';   
	    clock += mm+ "";  
	          
	    if (ss < 10) clock += '0';   
	    clock += ss;  
	  
	    return(clock);   
}

function C1_getDateTime(){
	 var now = new Date();  
	    var year = now.getFullYear();       //年  
	    var month = now.getMonth() + 1;     //月  
	    var day = now.getDate();            //日    
	    var hh = now.getHours();            //时  
	    var mm = now.getMinutes();          //分  
	    var ss=now.getSeconds();            //秒  
	         
	    var clock ="";
	    
	    clock += year + "-";  
	         
	    if(month < 10) clock += "0";         
	    clock += month + "-";  
	         
	    if(day < 10) clock += "0";   
	    clock += day + " ";  
	         
	    if(hh < 10) clock += "0";  
	    clock += hh + "";  
	  
	    if (mm < 10) clock += '0';   
	    clock += mm+ "";  
	          
	    if (ss < 10) clock += '0';   
	    clock += ss;  
	  
	    return(clock);   
}

function C1_GetAfterDateTime(AddDayCount) { 
	   var dd = new Date();
	   dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
	   var y = dd.getFullYear(); 
	   var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0
	   var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate();//获取当前几号，不足10补0
	   var h = dd.getHours()<10?"0"+dd.getHours():dd.getHours();            //时  
	   var m = dd.getMinutes()<10?"0"+dd.getMinutes():dd.getMinutes();          //分  
	   var s = dd.getSeconds()<10?"0"+dd.getSeconds():dd.getSeconds();            //秒  
	   return y+"-"+m+"-"+d+" "+ h +":"+m+":"+s; 
	}

function C1_GetAfterDate(AddDayCount) { 
   var dd = new Date();
   dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
   var y = dd.getFullYear(); 
   var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0
   var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate();//获取当前几号，不足10补0
   return y+"-"+m+"-"+d; 
}

function C1_getAfterFormatDate(MM) {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    //前十分钟时间
     var minutes=parseInt(MM);  

  var   interTimes=minutes*60*1000;

  var interTimes=parseInt(interTimes);  
     date=new   Date(Date.parse(date)-interTimes);
    
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hour = date.getHours();
    var minutes = date.getMinutes();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hour >= 0 && hour <= 9) {
            hour = "0" + hour;
    }
    if (minutes >= 0 && minutes <= 9) {
            minutes = "0" + minutes;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + hour + seperator2 + minutes
            + seperator2 + date.getSeconds();
    return currentdate;
}

function C1_checkTime(time){//校验时间,小于10前面加0
    if(time<10)return "0"+time;
    return time;
    }

function C1_getTradeNum(){//交易流水
	var myDate = new Date();
	var currentTime=myDate.getYear().toString()
	+((myDate.getMonth()+1).toString().length<2?("0"+(myDate.getMonth()+1).toString()):(myDate.getMonth()+1).toString())
	+(myDate.getDate().toString().length<2?("0"+myDate.getDate().toString()):myDate.getDate().toString())
	+myDate.getHours().toString()
	+myDate.getMinutes().toString()
	+myDate.getSeconds().toString()
	+myDate.getMilliseconds().toString(); 
	return currentTime;
}

function C1_getManageTradeNum(manage){//交易流水
	var myDate = new Date();
	var currentTime=myDate.getYear().toString()
	+((myDate.getMonth()+1).toString().length<2?("0"+(myDate.getMonth()+1).toString()):(myDate.getMonth()+1).toString())
	+(myDate.getDate().toString().length<2?("0"+myDate.getDate().toString()):myDate.getDate().toString())
	+myDate.getHours().toString()
	+myDate.getMinutes().toString()
	+myDate.getSeconds().toString()
	+myDate.getMilliseconds().toString(); 
	return manage+currentTime;
}

//产生随机数函数
function C1_RndNum(n){
    var rnd="";
    for(var i=0;i<n;i++)
        rnd+=Math.floor(Math.random()*10);
    return rnd;
}

function C1_getInfoByIdCard(UUserCard,num){
	if(num==1){//获取出生日期
	var birth=UUserCard.substring(6, 10) + "" + UUserCard.substring(10, 12) + "" + UUserCard.substring(12, 14);
	return birth;
	}
	if(num==2){//获取性别
		if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {//男
			return "男";
		} else {//女
			return "女";
		}
	}if(num==3){//获取年龄
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate();
		var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
		if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
			age++;
			}
		//age++;//虚岁
		return age;
		}		
}



function C1_getResultType(value){
	switch(value){  //#27f4f2 长   #f4d027短 
		case "l": return "<span style='background-color:#27f4f2;color:black;padding:8px;'>长款</span>";break;
		case "s": return "<span style='background-color:#f4e227;color:black;padding:8px;'>短款</span>";break;
		case "e": return "<span style='background-color:#f42727;color:white;padding:8px;'>异常款</span>";break;
		case "n": return "<span style='background-color:#98FB98;color:white;padding:8px;'>平账</span>";break;
		case "a": return "<span style='background-color:#CD9B1D;color:white;padding:8px;'>跨天账</span>";break;
		default :return value ;break;
	}
}

function C1_getAcross(value){
	console.log(value);
	if(value!=""){
		return "<span class='glyphicon glyphicon-ok' style='color:#01AAED'></span>";
		//return "<span style='background-color:#CD9B1D;color:black;padding:8px;'>跨天账</span>";
	}else{
		return value;
	}
}


function C1_getMark(type){
	switch(type){  //#27f4f2 长   #f4d027短 
		case 0: return "未读";break;
		case 1: return "已读";break;
		default :return "其他" ;break;
	}
}

function C1_getFeedBackScene(type){
	switch(type){  //#27f4f2 长   #f4d027短 
		case "0": return "下载对账单场景";break;
		case "1": return "执行对账场景";break;
		default :return "其他场景" ;break;
	}
}


function C1_getProcess(type){
	switch(type){  //#27f4f2 长   #f4d027短 
		case "0": return "原路退费";break;
		case "2": return "现金退费";break;
		case "1": return "已上报";break;
		default :return "待处理" ;break;
	}
}

//验证身份证的合法性
function C1_IdentityCodeValid(code) { 
			var regIdNo = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
			if(!regIdNo.test(code)){  
			    return false;  
			} else{
				return true;
			} 
        }

function C1_isNumber(val){

    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) || regNeg.test(val)){
        return true;
    }else{
        return false;
    }

}

function C1_isJson(str) {
    try {
        if (typeof JSON.parse(str) == "object") {
            return true;
        }
    } catch(e) {
    	console.log(e);
        return false;
    }
    return false;
}

function C1_showpopover(selector,content){//bootstrap控件
	$(selector).attr({
		 "title" : "温馨提示",
		 "data-container" : "body",
		 "data-trigger" : "manual",
		 "data-toggle" : "popover",
		 "data-placement" : "bottom",
		 "data-content" : content
	});
	$(selector).popover("show");
	 window.setTimeout( "$('"+selector+"').popover('hide')", 2000);
}

function C1_closepopover(selector){
	$(selector).popover("hide");
}

function C1_setTimeout(func,timeout){
	 window.setTimeout( func, timeout);
}

function C1_checkEmail(mail){
	var szReg=/^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/; 
	var bChk=szReg.test(mail); 
	return bChk; 
}

function C1_showWarning(){
	$(".alert").show();
}

function C1_hideWarning(){
	$(".alert").alert();
}

function C1_checkDataIsNull(value){
	if(value==""||value==null||value==undefined){
		return true;
	}else{
		return false
	}
}

function C1_checkNumber(theObj) {
	  var reg = /^[0-9]+.?[0-9]*$/;
	  if (reg.test(theObj)) {
	    return true;
	  }
	  return false;
	}

 //减法函数 
function C1_sub(arg1, arg2) {
    var r1, r2, m, n;
      try {
         r1 = arg1.toString().split(".")[1].length;
      }
      catch (e) {
         r1 = 0;
      }
      try {
         r2 = arg2.toString().split(".")[1].length;
      }
      catch (e) {
        r2 = 0;
      }
      m = Math.pow(10, Math.max(r1, r2));
      //last modify by deeka
      //动态控制精度长度
      n = (r1 >= r2) ? r1 : r2;
     return ((arg1 * m - arg2 * m) / m).toFixed(n);
 }


//加法函数
function C1_add(arg1, arg2) {
     var r1, r2, m;
     try {
         r1 = arg1.toString().split(".")[1].length;
     }
     catch (e) {
         r1 = 0;
     }
     try {
         r2 = arg2.toString().split(".")[1].length;
     }
     catch (e) {
         r2 = 0;
     }
     m = Math.pow(10, Math.max(r1, r2));
     return (arg1 * m + arg2 * m) / m;
 } 


//把一个日期对象转换成YYYYMMDDHH的字符串
function C1_getYYYYMMDDHH(arg1) {
	var year=arg1.getFullYear();
	var month=(arg1.getMonth()+1)<10?"0"+""+(arg1.getMonth()+1):(arg1.getMonth()+1);
	var date=arg1.getDate()<10?"0"+""+arg1.getDate():arg1.getDate();
    var hours=arg1.getHours()<10?"0"+""+arg1.getHours():arg1.getHours();
    return year+""+month+""+date+""+hours;
}

function C1_JsonFormat(json) {
    if (typeof json != 'string') {
        json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
        var cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}

function C1_trim(str){
	if(str==null||str==""){
		return str;
	}
	str = str.replace(/\s*/g,"");
	return str;
}

function C1_eval(value){
	  var type;
      if(value == null) { // 等同于 value === undefined || value === null
          return null;
      }
      type = Object.prototype.toString.call(value).slice(8, -1);
      switch(type) {
      case 'String':
          return JSON.parse(value);
      case 'Object':
          return value; // 普通对象使用 for...in 判断，有 key 即为 false
      default:
          return value; // 其他对象均视作非空
      }
}

function C1_formatDateTime(date) {
	        var time = new Date(Date.parse(date));
	        time.setTime(time.setHours(time.getHours() + 8));
	        var Y = time.getFullYear() + '-';
	        var  M = C1_addZero(time.getMonth() + 1) + '-';
	        var D = C1_addZero(time.getDate()) + ' ';
	        var h = C1_addZero(time.getHours()) + ':';
	        var m = C1_addZero(time.getMinutes()) + ':';
	        var  s = C1_addZero(time.getSeconds());
	        return Y + M + D + h+ m+ s;
	        // }
	   }
// 数字补0操作
function  C1_addZero(num) {
    return num < 10 ? '0' + num : num;
} 

function C1_showProcessdesc(processstatus,processdesc){
	var color="#c0c020";
	if(processstatus=="adopt"){
		 color="#20B2AA";
	}else if(processstatus=="refuse"){
		color="#ff4040"
	}
	return "<span style='padding:10px 10px 10px 10px;background-color:"+color+";color:white;'>"+processdesc+"</span>";
}

function C1_showApplyTypedesc(applytype,applytypedesc){
	var color="#778899";
	if(applytype=="refund"){
		 color="red";
	}else if(applytype=="refuse"){
		color="#DAA520"
	}
	return "<span style='padding:10px 10px 10px 10px;background-color:"+color+";color:white;'>"+applytypedesc+"</span>";
}

function C1_renderTime(date) {
	if(C1_checkDataIsNull(date))
		return "";
	  var dateee = new Date(date).toJSON();
	  return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '') 
	}