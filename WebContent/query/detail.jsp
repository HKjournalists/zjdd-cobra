<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="../js/jquery.js" type="text/javascript"></script>
<title>在家点点</title>
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
		
		var action = "${pageContext.request.contextPath}/query/msgQuery?";
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
						//alert(result);	
						var otab = document.getElementById("dtl");
						//alert("行数:"+otab.rows.length);
						while(otab.rows.length>1){
							otab.deleteRow(1);
						}
						for(var i=0;i<result.length;i++){
							var r = result[i];
							//alert(r);
							var otr = otab.insertRow(otab.rows.length);
							var otd = otr.insertCell(0);
							otd.innerHTML="<a href='javascript:dispatch1(\""+chgData(r["id"])+"\");' id='dispatch"+chgData(r["id"])+"'>分配到人</a>";
							var otd = otr.insertCell(0);
							otd.innerHTML="<a href='javascript:dispatch2(\""+chgData(r["id"])+"\");' id='dispatch"+chgData(r["id"])+"'>分配到组</a>";
							var otd = otr.insertCell(0);
							otd.innerHTML=chgData(r["workcount"]);
							var otd = otr.insertCell(0);
							otd.innerHTML=chgData(r["usercount"]);
							var otd = otr.insertCell(0);
							otd.innerHTML=chgData(r["name"]);
						}
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
	function refresh(){
		alert("1");
	}
</script>
</head>

<body>

	<div id="top" style="background:#000000;width:100%;height:30%;margin:0;">
		<br/>
		<br/>
		<form id="query" action="query/dataList" style="display:block;" method="post">
		  <input type="text"  id="wid" value="${wid}"/><!--记录id-->
		  <input type="text"  id="uid" value="${uid}"/><!--用户id-->
		  <input type="text"  id="orgid" value="${orgid}"/><!--组id-->
		  <input type="text"  id="urole" value="${urole}"/><!--用户角色-->
		  <input type="button" value="  查询  " id="chaxun" onClick="query();"/>
		</form>
		<br/>
		<br/>
	</div>
	<div id="main">
		<div id="right" style="float:center;" >
			<div id="nav">
				<br/>
				<br/>
				<div id="content">
				  <div id="baseInfo">
				  	<h3>个人信息</h3>
				  	<form action="" >
				  		姓名<input type="text" id="name" name="name" readonly="readonly"/><br/>
				  		城市<input type="text" id="city" name="city" readonly="readonly"/><br/>
				  		电话<input type="text" id="phone" name="phone" readonly="readonly"/><br/>
				  		微信<input type="text" id="wx" name="wx" readonly="readonly"/><br/>
				  		Q&nbsp;&nbsp;Q<input type="text" id="qq" name="qq" readonly="readonly"/><br/>
				  	</form>
				  	<h3>处理信息</h3>
				  	<form action="" >
				  		分&nbsp;&nbsp;&nbsp;&nbsp;类<input type="text" id="name" name="name" readonly="readonly"/><br/>
				  		组&nbsp;&nbsp;&nbsp;&nbsp;别<input type="text" id="city" name="city" readonly="readonly"/><br/>
				  		销售代表<input type="text" id="phone" name="phone" readonly="readonly"/><br/>
				  		任务状态<input type="text" id="wx" name="wx" readonly="readonly"/><br/>
				  		备&nbsp;&nbsp;&nbsp;&nbsp;注<textarea id="qq" name="qq" readonly="readonly" style="vertical-align:middle"></textarea>
				  	</form>
				  	<h3>历史记录</h3>
				  	<table id="dtl" align="left"  border="1px;" bordercolor="#000000" cellpadding="3px;" 
					  	cellspacing="3px" style="border-collapse:collapse">
				  		<tr>
				  			<th>通话时间</th>
				  			<th>处理部门</th>
				  			<th>处理人员</th>
				  			<th>通话次数</th>
				  			<th>处理状态</th>
				  			<th>处理备注</th>
				  		</tr>
				  	</table>
				  </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
