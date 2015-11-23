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
						//alert(result);	
						var otab = document.getElementById("dtl2");
						//alert("行数:"+otab.rows.length);
						while(otab.rows.length>1){
							otab.deleteRow(1);
						}
						for(var i=0;i<result.length;i++){
							var r = result[i];
							//alert(r);
							var otr = otab.insertRow(otab.rows.length);
							var otd = otr.insertCell(0);
							otd.innerHTML="<a href='javascript:dispatch1(\""+chgData(r["orgid"])+"\");' id='dispatch"+chgData(r["orgid"])+"'>分配到人</a>";
							var otd = otr.insertCell(0);
							otd.innerHTML="<a href='javascript:dispatch2(\""+chgData(r["orgid"])+"\");' id='dispatch"+chgData(r["orgid"])+"'>分配到组</a>";
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
	function dispatch1(orgid){
		var action = "${pageContext.request.contextPath}/query/userList?";
		//var dataarr = new Array("wid","uid","urole");
		var data = action+"orgid="+orgid+"&iSec"+Math.random();
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
						var otab = document.getElementById("dtl3");
						//alert("行数:"+otab.rows.length);
						while(otab.rows.length>1){
							otab.deleteRow(1);
						}
						for(var i=0;i<result.length;i++){
							var r = result[i];
							//alert(r);
							var otr = otab.insertRow(otab.rows.length);
							var otd = otr.insertCell(0);
							otd.innerHTML="<a href='javascript:dispatch3(\""+chgData(r["userid"])+"\");' id='dispatch"+chgData(r["userid"])+"'>分配</a>";
							var otd = otr.insertCell(0);
							otd.innerHTML=chgData(r["workcount"]);
							var otd = otr.insertCell(0);
							otd.innerHTML=chgData(r["username"]);
						}
					}
		});
	}
	function dispatch2(orgid){
		document.getElementById("orgid").value=orgid;
		
		var action = "${pageContext.request.contextPath}/query/dispatchGroupWork?";
		var dataarr = new Array("wid","orgid");
		var data = action+formData(dataarr)+"&iSec"+Math.random();
		//alert(data);
		var result = $.ajax({
					type:"GET",
					url:data,
					dataType:"json",
					error:function(){
						alert("操作失败!");
					},
					success:function(etf){
						alert("操作成功!");
					}
		});
	}
	function dispatch3(uid){
		document.getElementById("uid").value=uid;
		
		var action = "${pageContext.request.contextPath}/query/dispatchPersonWork?";
		var dataarr = new Array("wid","uid");
		var data = action+formData(dataarr)+"&iSec"+Math.random();
		//alert(data);
		var result = $.ajax({
					type:"GET",
					url:data,
					dataType:"json",
					error:function(){
						alert("操作失败!");
					},
					success:function(etf){
						alert("操作成功!");
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
					<div>
					  <table id="dtl2" align="center" border="1px;" bordercolor="#000000" cellpadding="3px;" 
					  	cellspacing="3px" style="border-collapse:collapse">
					    <tr id="title" style="font-size:12px;">
					        <th>组名</th>
						    <th>客服数</th>
						    <th>当前任务</th>
						    <th>分配到组</th>
						    <th>分配到人</th>
						  </tr>
					    </table>
					     <table id="dtl3" align="center" border="1px;" bordercolor="#000000" cellpadding="3px;" 
					  	cellspacing="3px" style="border-collapse:collapse">
					    <tr id="title" style="font-size:12px;">
					        <th>组员名</th>
						    <th>当前任务</th>
						    <th>分配</th>
						  </tr>
					    </table>
				  </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
