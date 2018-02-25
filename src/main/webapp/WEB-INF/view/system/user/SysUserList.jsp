<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	
	// 删除用户
	function deleteUser(){
		// 判断是否选择记录
	    var flag = isSelectCheckBox("sys_user_list");
	    if(flag) {
	    	var value = getSelectCheckBoxValue();// 获取Id
	    	alertMsg.confirm(I18N.sysUser.confirmDelete, {
				okCall: function(){
					$.ajax({
				 		type: 'POST',
				 		url:"deleteSysUser?value=" + value,
				 		data:"",
				 		dataType:"json",
				 		cache: false,
				 		success: navTabAjaxDone,
				 		error: DWZ.ajaxError
				 	});
				}
			});
	    }
	    return;
	}
	
	// 修改用户
	function updateSysUserInit(){
		// 判断是否选择记录
	    var flag = isSelectCheckBox("sys_user_list","only");
	    if(flag) {
	    	var value = getSelectCheckBoxValue();// 获取Id
	    	var url = "updateSysUserInit?sysUserId=" + value;	
	    	$.pdialog.open(
				url, 'updateSysUserInit', "修改用户", {
					width : 720,
					height : 300,
					mask : true,
					maxable : false,
					minable : true,
					resizable : false,
					drawable : false
			});
	    }
	}
	
</script>
<div id = "mainContainer">
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="querySysUserList" method="post" >

	<input type="hidden" id = "pageNum" name="pageNum" value="${page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${page.orderField}" />
	<input type="hidden" name="totalCount" value="${page.totalCount}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户名：<input type="text" name="username" maxlength="32" value = "${vo.username }"/>
				</td>
				<td>
					真实姓名：<input type="text" name="name" maxlength="32" value = "${vo.name }"/>
				</td>
				<td>
					创建时间：
					<input type="text" name="startDate" value = "${fn:substring(vo.startDate, 0, 10)}" class="date" size="10" readonly="readonly"/>-
					<input type="text" name="endDate" value = "${fn:substring(vo.endDate, 0, 10)}" class="date"  size="10" readonly="readonly" />
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button onclick="clearData()">重置</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent" >
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="addSysUserInit" target="dialog" resizable="false" drawable="false" maxable="false" mask="true" title="添加" width="720" height="500"><span>添加</span></a></li>
			<li><a class="delete" onclick="deleteUser()" title="删除"><span>删除</span></a></li>
			<li><a class="edit" onclick="updateSysUserInit()" ><span>修改</span></a></li>
		</ul>
	</div>
	<div layoutH="120" id="sys_user_list">
	<table class="list" width="100%" rel="jbsxBox">
		<thead align="center">
			<tr>
				<th width="2%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%">序号</th>
				<th width="17%">用户名</th>
				<th width="10%">真实姓名</th>
				<th width="5%">年龄</th>
				<th width="10%">创建时间</th>
			</tr>
		</thead>
		<tbody align="left">
			<c:forEach items="${list }" var="vo" varStatus="status">
			<tr target="sysUserId" rel="${vo.id }">
				<td align=center><input name="ids" value="${vo.id }" type="checkbox"></td>

				<td align=center width="40px"><div align="center">${status.count }</div></td>
				<td>${vo.username }</td>
				<td>${vo.name }</td>
				<td>${vo.age }</td>
				<td><fmt:formatDate value="${vo.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" id="listSelect" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value});">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
			<span>页，共${page.totalCount }页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount }" numPerPage="${page.numPerPage }" pageNumShown="${page.pageNumShown }" currentPage="${page.page }"></div>
	</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
		var pageSize = '${page.numPerPage }';
		$("#listSelect").attr("value",pageSize);
	});

</script>