<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="../js/jquery.js" type="text/javascript"></script>
<title>在家点点登录</title>
<style>
    body{margin:0; padding:0;}
	h3{margin:10px;}
	h5{margin:8px;}
</style>
<script type="text/javascript">
	function query(){
		var reqParam = document.location.search.substring(1);
		//alert(reqParam);
		var params = new Array();
		params = reqParam.split("&");
		for(var i=0;i<params.length;i++){
			//alert(params[i]);
			var param = params[i];
			var kv = param.split("=");
			document.getElementById(kv[0]).value=kv[1];
		}
		
		var action = "${pageContext.request.contextPath}/query/groupList?";
		var dataarr = new Array("wid","uid","urole");
		var data = action+formData(dataarr)+"iSec"+Math.random();
		//alert(data);
		var result = $.ajax({
					type:"GET",
					url:data,
					dataType:"json",
					error:function(){
						alert("操作失败!");
					},
					success:function(etf){
						//alert(etf["msg"]);
						var result = etf["data"]["result"];
					}
		});
	}
	function formData(fieldIds){
		var data='';
		if(!fieldIds||fieldIds==null){
			return "";
		}
		//alert("1");
		var len = fieldIds.length;
		//alert(len);
		for(var i=0;i<len;i++){
			//alert("["+document.getElementById(fieldIds[i]).value+"]");
			if(document.getElementById(fieldIds[i]).value!=""){
				//alert(document.getElementById(fieldIds[i]).value);
				data=data+fieldIds[i]+'='+encodeURIComponent(encodeURIComponent(document.getElementById(fieldIds[i]).value))+'&&';
			}
		}
		return data;
	}
	function chgData(d){
		if(typeof d=="undefined"){
			return "";
		}else{
			return d;
		}
	}
	function confirm(){
		var uid = document.getElementById("uid").value;
		var passwd = document.getElementById("passwd").value;
		//alert(uid);
		if(uid==""||passwd==""){
			alert("用户名或密码不能为空!");
			return false;
		}
		return true;
	}
</script>
</head>

<body>

	<div id="top"  align="center"  style="width:100%;height:100%;margin:0;">
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<form id="login" action="query/login" style="display:block;" method="post" onsubmit="return confirm();">
		  用户名<input type="text" name="uid" id="uid"/><br/>
		  密&nbsp;&nbsp;码<input type="password" name="passwd" id="passwd"/><br/>
		  <br/>
		  <br/>
		  <input type="submit" value="  查询  " id="submit"/>
		  <input type="reset"   value="  重置  " id="reset"/>
		  <p id="errormsg" style="color:red">
		  	<script type="text/javascript">
		  		var reqParam = document.location.search.substring(1);
		  		var kv = reqParam.split("=");
		  		//alert(kv[0]);
				document.getElementById(kv[0]).innerHTML=kv[1];
		  	</script>
		  </p>
		</form>
		<br/>
		<br/>
	</div>
</body>
</html>
