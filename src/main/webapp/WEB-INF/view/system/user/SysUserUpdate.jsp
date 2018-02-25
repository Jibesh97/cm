<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../taglib.jsp"%>
<script src="${pageContext.request.contextPath}/js/md5/md5.min.js" type="text/javascript"></script>
<script type="text/javascript">
function validateCallbackUpdate() {
	var form = $("#userForm");
	if (!form.valid()) {return false;}

	$.ajax({
		type: form.method || 'POST',
		url:form.attr("action"),
		data:form.serializeArray(),
		dataType:"json",
		cache: false,
		success: dialogAjaxDone,
		error: DWZ.ajaxError
	});
	return false;
	
}

</script>
<div class="pageContent" style="overflow: hidden;">
<div style="overflow:hidden;">
	<form method="post" id="userForm" action="updateSysUser" class="pageForm required-validate" >
		<input type="hidden" name="id" value="${vo.id }"/>
		<div class="pageFormContent nowrap" layoutH="59" style="overflow: hidden;">
			<dl>
				<dt>用&nbsp;&nbsp;户&nbsp;&nbsp;名：</dt>
				<dd>
					<input type="text" name="username" value="${vo.username }" maxlength="20" class="required specialCharValidate" style="width: 146px" />
				</dd>
			</dl>
			<dl>
				<dt>真实姓名：</dt>
				<dd>
					<input type="text" name="name"  value="${vo.name }" maxlength="20" class="required specialCharValidate" style="width: 146px"/>
				</dd>
			</dl>
			<dl>
				<dt>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</dt>
				<dd>
					<input name="age" value="${vo.age }" type="text" class="digits" min="18" max="99" style="width: 146px" ></input>
				</dd>
			</dl>	
			<dl>
				<dt>创建时间：</dt>
				<dd>
					<fmt:formatDate value="${vo.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="submit" onclick="return validateCallbackUpdate();">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
			</ul>
		</div>
	</form>
</div>	
</div>