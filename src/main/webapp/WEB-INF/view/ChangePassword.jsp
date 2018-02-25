<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script language="javascript"> 
 function validateChangePwd(form, callback) {
	var $form = $(form);
	if (!$form.valid()) {
		return false;
	}
	var oldPassword = $("#oldPassword").attr("value");
		$("#oldPassword").attr("value", oldPassword);
	var cp_newPassword = $("#cp_newPassword").attr("value");
		$("#cp_newPassword").attr("value", cp_newPassword);
	var rnewPassword = $("#rnewPassword").attr("value");
		$("#rnewPassword").attr("value", rnewPassword);
	$.ajax({
		type: form.method || 'POST',
		url:$form.attr("action"),
		data:$form.serializeArray(),
		dataType:"json",
		cache: false,
		success: callback || DWZ.ajaxDone,
		error: DWZ.ajaxError
	});
	return false; 
}

// 修改密码ajax回调函数
function changePwdCallback(json) {
	//如果修改失败，则清空密码
	if(json.statusCode != DWZ.statusCode.ok){
	
		$("#oldPassword").attr("value", "");
		$("#cp_newPassword").attr("value", "");
		$("#rnewPassword").attr("value", "");
	}
	dialogAjaxDone(json);
}

//清空密码
function clrPwd() {
		$("#oldPassword").attr("value", "");
		$("#cp_newPassword").attr("value", "");
		$("#rnewPassword").attr("value", "");
}
</script>
<div class="pageContent">

	<form method="post" action="loginAction.do?method=changePassword"
		class="pageForm required-validate"
		onsubmit="return validateChangePwd(this, changePwdCallback)">
		<div class="pageFormContent" layoutH="58">
			<div style="margin:20px 50px 30px 50px; padding:10px;">
				<div class="unit">
					<label>账号</label>
					<input type="text" id="userName" name="userName"
						size="20" class="required" value="${userInfo.userName }" readonly="readonly"/>
				</div>
				<div class="unit">
					<label>旧密码
					</label> <input type="password" id="oldPassword" name="oldPassword"
						size="20" class="required" />
				</div>
				<div class="unit">
					<label>新密码
					</label> <input type="password" id="cp_newPassword" name="newPassword"
						size="20" minlength="6" class="required" />
				</div>
				<div class="unit">
					<label>确认新密码
					</label> <input type="password" id="rnewPassword" name="rnewPassword"
						size="20" equalTo="#cp_newPassword" class="required" />
				</div>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
								提交
							</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" onclick="clrPwd();">
								重置
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>