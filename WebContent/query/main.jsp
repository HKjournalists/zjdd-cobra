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
	table { table-layout:fixed; word-break: break-all; word-wrap: break-word; }
</style>
<script type="text/javascript">
	function khly(id){
		document.getElementById("khly").value=id;
	}
	function hzlx(id){
		var urole = document.getElementById("roleid").value;
		if(urole=="1"||urole=="2")
			document.getElementById("hzlx").value=id;
	}
	function cs(id){
		var urole = document.getElementById("roleid").value;
		if(urole=="1")
			document.getElementById("cs").value=id;
	}
	function zt(id){
		document.getElementById("zt").value=id;
	}
	function query(page){
		//document.getElementById("query").submit();
		//$.("page").value=page;
		document.getElementById("page").value=page;
		var action = "${pageContext.request.contextPath}/query/dataList?";
		var dataarr = new Array("khly","hzlx","cs","zt","uid","page");
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
							otd.innerHTML="<a href='javascript:dispatch(\""+chgData(r["msgid"])+"\");' id='dispatch"+chgData(r["msgid"])+"'>分配</a>";
							var otd = otr.insertCell(0);
							otd.innerHTML="<a href='javascript:detail(\""+chgData(r["msgid"])+"\");' id='detail"+chgData(r["msgid"])+"'>查看</a>";
							var otd = otr.insertCell(0);
							otd.innerHTML="<a href='javascript:process(\""+chgData(r["msgid"])+"\");' id='modify"+chgData(r["msgid"])+"'>修改</a>";
							var otd = otr.insertCell(0);
							otd.innerHTML=chgData(r["salman"]);//销售代表
							var otd = otr.insertCell(0);
							otd.innerHTML="<select id='cgroup"+chgData(r["msgid"])+"' disabled='true'><option value='A'>A组</option><option value='B'>B组</option> </select>";//组别
							//alert(document.getElementById("sel1"));
							document.getElementById("cgroup"+chgData(r["msgid"])).value=chgData(r["cgroup"]);
							var otd = otr.insertCell(0);
							otd.innerHTML=" <textarea id='remark"+chgData(r["msgid"])+"' readonly='true'>"+chgData(r["remark"])+"</textarea>";//备注
							var otd = otr.insertCell(0);
							otd.innerHTML="<select id='dtlsts"+chgData(r["msgid"])+"' disabled='true'>"+
														"<option value='01'>无组未分配</option>"+
														"<option value='02'>组内未分配</option>"+
														"<option value='11'>未处理</option>"+
														"<option value='12'>跟进中</option>"+
														"<option value='13'>有效</option>"+
														"<option value='14'>无效</option>"+
													"</select>";//状态
							document.getElementById("dtlsts"+chgData(r["msgid"])).value=chgData(r["dtlsts"]);
							var otd = otr.insertCell(0);
							otd.innerHTML="<select id='lb"+chgData(r["msgid"])+"' disabled='true'><option value='A'>A</option><option value='B'>B</option></select>";
							if(chgData(r["lb"])!="")
								document.getElementById("lb"+chgData(r["msgid"])).value=chgData(r["lb"]);//分类
							var otd = otr.insertCell(0);
							otd.innerHTML="<select id='custype"+chgData(r["msgid"])+"' disabled='true'><option value='1'>经销商</option><option value='2'>小店</option></select>";
							document.getElementById("custype"+chgData(r["msgid"])).value=chgData(r["custype"]);//合作类型
							var otd = otr.insertCell(0);
							otd.innerHTML="<select id='ccity"+chgData(r["msgid"])+"' disabled='true'>"+
														"<option value='11'>大客户</option>"+
														"<option value='12'>北京</option>"+
														"<option value='13'>宁波</option>"+
														"<option value='14'>南京</option>"+
														"<option value='15'>苏州</option>"+
														"<option value='16'>广州</option>"+
														"<option value='17'>杭州</option>"+
														"<option value='18'>深圳</option>"+
														"<option value='19'>武汉</option>"+
														"<option value='20'>天津</option>"+
														"<option value='21'>上海</option>"+
													"</select>";
							document.getElementById("ccity"+chgData(r["msgid"])).value=chgData(r["ccity"]);//城市
							var otd = otr.insertCell(0);
							otd.innerHTML="<input type='text'  id='qq"+chgData(r["msgid"])+"' disabled='true' value='"+chgData(r["qq"])+"' size='10'/>";//QQ
							var otd = otr.insertCell(0);
							otd.innerHTML="<input type='text'  id='wx"+chgData(r["msgid"])+"' disabled='true' value='"+chgData(r["wx"])+"' size='10'/>";//微信
							var otd = otr.insertCell(0);
							otd.innerHTML="<input type='text'  id='phone"+chgData(r["msgid"])+"' disabled='true' value='"+chgData(r["phone"])+"' size='12'/>";//电话
							var otd = otr.insertCell(0);
							otd.innerHTML="<input type='text'  id='name"+chgData(r["msgid"])+"' disabled='true' value='"+chgData(r["name"])+"' size='10'/>";//姓名
							var otd = otr.insertCell(0);
							otd.innerHTML=chgData(r["phncnt"]);//通话次数
							var otd = otr.insertCell(0);
							otd.innerHTML=chgData(r["lastphntim"]);//上次通话时间
							var otd = otr.insertCell(0);
							otd.innerHTML=chgData(r["protim"]);//分配时间
							var otd = otr.insertCell(0);
							otd.innerHTML=chgData(r["srcdtl"]);//来源详情
							var otd = otr.insertCell(0);
							otd.innerHTML=chgData(r["cussrc"]);//客户来源
						}
						//for(var i in etf){
						//	alert("p:"+i);
						//}
						
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
	function process(id){
		if(document.getElementById("modify"+id).innerHTML=="保存"){
			save(id);
		}else{
			modify(id);
		}
	}
	function modify(id){
		//alert(id);
		document.getElementById("cgroup"+id).disabled=false;
		document.getElementById("remark"+id).readOnly=false;
		document.getElementById("dtlsts"+id).disabled=false;
		document.getElementById("lb"+id).disabled=false;
		document.getElementById("custype"+id).disabled=false;
		document.getElementById("ccity"+id).disabled=false;
		document.getElementById("qq"+id).disabled=false;
		document.getElementById("wx"+id).disabled=false;
		document.getElementById("phone"+id).disabled=false;
		document.getElementById("name"+id).disabled=false;
		//alert(document.getElementById("modify"+id));
		document.getElementById("modify"+id).innerHTML="保存";
	}
	function save(id){
		document.getElementById("page").value=page;
		var action = "${pageContext.request.contextPath}/query/updateData?id="+id+"&&";
		var dataarr = new Array("name"+id,"phone"+id,"wx"+id,"qq"+id,"ccity"+id,"custype"+id,"lb"+id,"dtlsts"+id,"remark"+id,"cgroup"+id);
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
				//alert(id);
				document.getElementById("cgroup"+id).disabled=true;
				document.getElementById("remark"+id).readOnly=true;
				document.getElementById("dtlsts"+id).disabled=true;
				document.getElementById("lb"+id).disabled=true;
				document.getElementById("custype"+id).disabled=true;
				document.getElementById("ccity"+id).disabled=true;
				document.getElementById("qq"+id).disabled=true;
				document.getElementById("wx"+id).disabled=true;
				document.getElementById("phone"+id).disabled=true;
				document.getElementById("name"+id).disabled=true;
				//alert(document.getElementById("modify"+id));
				document.getElementById("modify"+id).innerHTML="修改";
			}
		});
	}
	function dispatch(id){
		var uid = document.getElementById("uid").value;
		var urole = document.getElementById("roleid").value;
		if(urole=="4"){
			alert("不可再分配!");
			return;
		}
		//var dtlsts = document.getElementById("dtlsts"+id).value;
		//if(dtlsts=="1"){
		//	alert("该记录已分配");
		//	return;
		//}
		//uid = "beijingceo";
		//urole = "1";
		window.open('${pageContext.request.contextPath}/query/dispatch.jsp?uid='+uid+'&urole='+urole+'&wid='+id,'newwindow');
	}
	function detail(id){
		var uid = document.getElementById("uid").value;
		var urole = document.getElementById("roleid").value;
		//var dtlsts = document.getElementById("dtlsts"+id).value;
		//if(dtlsts=="1"){
		//	alert("该记录已分配");
		//	return;
		//}
		//uid = "beijingceo";
		//urole = "1";
		window.open('${pageContext.request.contextPath}/query/detail.jsp?uid='+uid+'&urole='+urole+'&wid='+id,'newwindow');
	}
	function refresh(){
		query("1");
		//var otab = document.getElementById("dtl2");
		//alert("行数:"+otab.rows.length);
		//otab.deleteRow(otab.rows.length-1);
	}
</script>
</head>

<body>

	<div id="top" style="background:#313FFF;width:100%;height:30%;margin:0;">
		<br/>
		<br/>
		<form id="query" action="query/dataList" style="display:block;" method="post">
		  客户来源<input type="text"  id="khly" value="${khly}"/><!--客户来源-->
		  合作类型<input type="text"  id="hzlx" value="${hzlx}"/><!--合作类型-->
		  城市<input type="text"  id="cs" value="${cs}"/><!--城市-->
		  状态<input type="text"  id="zt" value="${zt}"/><!--状态-->
		  用户id<input type="text"  id="uid" value="${uid}"/><!--用户id-->
		  部门id<input type="text"  id="orgid" value="${orgid}"/><!--部门id-->
		  用户角色<input type="text"  id="roleid" value="${roleid}"/><!--用户角色-->
		  <input type="text"  id="page" value="${page}"/><!--当前页-->
		  <input type="text"  id="totpage" value="${totpage}"/><!--总页数-->
		  <input type="button" value="  查询  " id="chaxun" onClick="refresh();"/>
		</form>
		<br/>
		<br/>
	</div>
	<div id="main">
		<div id="left" style="float:left;width:5%;background:#80FF80;">
			<div id="cussrc" align="center" style="border-color:#000;border-width:1px;border-style:solid;">
				<h3>客户来源</h3>
				<h5><a href="javascript:khly('');" id="khlyall"style="text-decoration: none;">全部</a></h5>
				<h5><a href="javascript:khly('khlygdt');" id="khlygdt"style="text-decoration: none;">广点通</a></h5>
				<h5><a href="javascript:khly('khlytalk99');" id="khlytalk99"style="text-decoration: none;">talk99</a></h5>
				<h5><a href="javascript:khly('khltxxw');" id="khltxxw"style="text-decoration: none;">腾讯新闻</a></h5>
			</div>
			<div id="custyp" align="center" style="border-color:#000;border-width:1px;border-style:solid;">
				<h3>合作类型</h3>
				<h5><a href="javascript:hzlx('');" id="hzlxall"style="text-decoration: none;">全部</a></h5>
				<h5><a href="javascript:hzlx('1');" id="hzlxjxs"style="text-decoration: none;">经销商</a></h5>
				<h5><a href="javascript:hzlx('2');" id="hzlxxd"style="text-decoration: none;">小店</a></h5>
			</div>
			<div id="cusccity" align="center" style="border-color:#000;border-width:1px;border-style:solid;">
				<h3>城市</h3>
				<h5><a href="javascript:cs('');" id="csall"style="text-decoration: none;">全部</a></h5>
				<h5><a href="javascript:cs('11');" id="csshanghai"style="text-decoration: none;">大客户</a></h5>
				<h5><a href="javascript:cs('12');" id="csbeijing"style="text-decoration: none;">北京</a></h5>
				<h5><a href="javascript:cs('13');" id="csguangzhou"style="text-decoration: none;">宁波</a></h5>
				<h5><a href="javascript:cs('14');" id="csshanghai"style="text-decoration: none;">南京</a></h5>
				<h5><a href="javascript:cs('15');" id="csbeijing"style="text-decoration: none;">苏州</a></h5>
				<h5><a href="javascript:cs('16');" id="csguangzhou"style="text-decoration: none;">广州</a></h5>
				<h5><a href="javascript:cs('17');" id="csshanghai"style="text-decoration: none;">杭州</a></h5>
				<h5><a href="javascript:cs('18');" id="csbeijing"style="text-decoration: none;">深圳</a></h5>
				<h5><a href="javascript:cs('19');" id="csguangzhou"style="text-decoration: none;">武汉</a></h5>
				<h5><a href="javascript:cs('20');" id="csshanghai"style="text-decoration: none;">天津</a></h5>
				<h5><a href="javascript:cs('21');" id="csbeijing"style="text-decoration: none;">上海</a></h5>
			</div>
			<div id="cussts" align="center" style="border-color:#000;border-width:1px;border-style:solid;">
				<h3>状态</h3>
				<h5><a href="javascript:zt('');" id="ztall"style="text-decoration: none;">全部</a></h5>
				<h5><a href="javascript:zt('01');" id="ztyfp"style="text-decoration: none;">无组未分配</a></h5>
				<h5><a href="javascript:zt('02');" id="ztwfp"style="text-decoration: none;">组内未分配</a></h5>
				<h5><a href="javascript:zt('03');" id="ztgjz"style="text-decoration: none;">未处理</a></h5>
				<h5><a href="javascript:zt('04');" id="ztyx"style="text-decoration: none;">跟进中</a></h5>
				<h5><a href="javascript:zt('05');" id="ztwx"style="text-decoration: none;">有效</a></h5>
				<h5><a href="javascript:zt('06');" id="ztwx"style="text-decoration: none;">无效</a></h5>
			</div>
		</div>
		<div id="right" style="float:center;" >
			<div id="nav">
				<div id="fp" style="margin:0; padding:0;background:#00FF00;border-color:#000;border-width:1px;border-style:solid;">
					<h3 align="justify"> <a href="#" style="text-decoration: none;">批量分配</a>&nbsp;&nbsp;&nbsp;
					<a href="#" style="text-decoration: none;"> 智能分配</a></h3>
				</div>
				<br/>
				<br/>
				<div id="detail" style="display:none">
					<input  value="返回" type="button"/>
					<table id="dtl1" align="center"  border="1px;" bordercolor="#000000" cellpadding="3px;" 
					  	cellspacing="3px" style="border-collapse:collapse">
					    <tr style="font-size:12px;">
							<th>组别</th>
						    <th>在线客服</th>
						    <th>忙碌任务</th>
						    <th>等待任务</th>
							<th>操作</th>
						</tr>
						<tr style="font-size:12px;">
							<td>A组</td>
						    <td>5</td>
						    <td>10</td>
						    <td>20</td>
							<td><input  value="分配" type="button"/></td>
						</tr>
					</table>
				</div>
				<div id="content">
					<div align="justify">
					  <table id="dtl2" align="center" border="1px;" bordercolor="#000000" cellpadding="3px;" 
					  	cellspacing="3px" style="border-collapse:collapse">
					    <tr id="title" style="font-size:12px;" align="center">
					        <th width="105">客户来源</th>
						    <th width="105">来源详情</th>
						    <th width="120">分配时间</th>
						    <th width="160">上次通话时间</th>
						    <th width="105">通话次数</th>
						    <th width="160">姓名</th>
						    <th width="160">电话</th>
						    <th width="160">微信</th>
						    <th width="160">QQ</th>
						    <th width="105">城市</th>
						    <th width="105">合作类型</th>
						    <th width="105">分类</th>
						    <th width="105">状态</th>
						    <th width="105">备注</th>
						    <th width="105">组别</th>
						    <th width="105">销售代表</th>
						    <th>修改</th>
						    <th>详情</th>
						    <th>分配</th>
						  </tr>
					    <!-- <tr id="dd" style="font-size:12px" align="center">
					      <td>腾讯新闻</td>
						    <td>A组</td>
						    <td>2015-11-16 12:00:00</td>
						    <td>2015-11-16 12:00:00</td>
						    <td>1</td>
						    <td>张三</td>
						    <td>13088888888</td>
						    <td></td>
						    <td></td>
						    <td>
							    <select>
							      <option value="1">上海</option>
							      <option value="2">北京</option>
						        </select>					        </td>
						    <td>
							    <select>
							      <option value="1">小店</option>
							      <option value="2">经销商</option>
						        </select>					        </td>
						    <td>
							    <select>
							      <option value="1">A</option>
							      <option value="2">B</option>
						        </select>					        </td>
						    <td>
							    <select>
							      <option value="1">已分配</option>
							      <option value="2">未分配</option>
						        </select>					        </td>
						    <td>
							    <textarea></textarea>					        </td>
						    <td>
							    <select>
							      <option value="1">A组</option>
							      <option value="2">B组</option>
						        </select>					        
						    </td>
						    <td></td>
						    <td><input  value="修改" type="button"/></td>
						    <td><input  value="分配" type="button"/></td>
						  </tr> -->
					    </table>
				  </div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
