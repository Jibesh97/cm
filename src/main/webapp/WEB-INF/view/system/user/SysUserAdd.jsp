<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../taglib.jsp"%>
<script src="${pageContext.request.contextPath}/js/md5/md5.min.js" type="text/javascript"></script>
<script type="text/javascript">
function validateCallbackAdd() {
	var form = $("#userForm");
	if (!form.valid()) {return false;}

	// 密码加密
	var userPass = $("#w_validation_pwd").attr("value");
	if(userPass != ""){
		userPass = hex_md5(userPass);
		$("#realPass").attr("value", userPass);
	}
	// ========end=========	

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
	<form method="post" id="userForm" action="addSysUser" class="pageForm required-validate" >
		<div class="pageFormContent nowrap" layoutH="59" style="overflow: hidden;">

			<dl>
				<dt>用户名：</dt>
				<dd>
					<input type="text" name="username" maxlength="20" class="required specialCharValidate" style="width: 146px" />
				</dd>
			</dl>
			<dl>
				<dt>密码：</dt>
				<dd>
					<input id="w_validation_pwd" type="password" class="required specialCharValidate" minlength="6" maxlength="20" style="width: 146px"/>
					<input id="realPass" type="hidden" name="password"/>
				</dd>
			</dl>
			<dl>
				<dt>确认密码：</dt>
				<dd>
					<input id="password2" type="password" name="password2" class="required" equalto="#w_validation_pwd" minlength="6" maxlength="20" style="width: 146px"/>
				</dd>
			</dl>
			<dl>
				<dt>真实姓名：</dt>
				<dd>
					<input type="text" name="name" maxlength="20" class="required specialCharValidate" style="width: 146px"/>
				</dd>
			</dl>
			<dl>
				<dt>年龄：</dt>
				<dd>
					<!-- age 赋值 -->
					<input name="age" id="age" type="text" class="digits" min="18" max="99" style="width: 146px" ></input>
				</dd>
			</dl>	
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="submit" onclick="return validateCallbackAdd();">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
			</ul>
		</div>
	</form>
</div>	
</div>