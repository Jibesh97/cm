<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>车联网平台监控系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">


<!-- jQuery -->
<script src="<%=basePath %>js/jquery-1.7.2.min.js"></script>


<style>
/*分别设置字体和图标的颜色    congrp  */
.alert-info{
color:#34a6e7;
}
.red{
color:#3595d5;
}
</style>

</head>

<body>
	<div class="ch-container">

			<div class="row">
				<div class="col-md-12 center login-header">
				<!-- 登录页图片 congrp -->
				</div>
				<!--/span-->
			</div>
			<!--/row-->

			<div class="row">
				<div class="well col-md-5 center login-box">
					<div class="alert alert-info" >
					<span class="change" >请输入账号和密码</span>
					
					</div>
					
					<form class="form-horizontal" action="" method="post"
									role="form" id="example-form" enctype="multipart/form-data">
						<fieldset>
							<div class="input-group input-group-lg">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user red"></i></span> 
								<input type="text" class="form-control" placeholder="用户名"
									name="USER_NAME" id="userNameId">
								
							</div>
							
							<div class="clearfix"></div>
							<br>

							<div class="input-group input-group-lg">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock red"></i></span> 
									<input type="password" class="form-control" placeholder="密码" name="USER_PASSWORD" id="passWordId">
									
							</div>
							<span id="passwordHeader" style="margin-top:5px;display:none"> </span>
							<div class="clearfix"></div>

							<div class="clearfix"></div>

							<p class="center col-md-5">
								<input style="outline:none" type = "button"  class="btn btn-primary" id = "denglu"
									onclick="complete()" value = "登录"></input>
							</p>
						</fieldset>
					</form>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
		<!--/fluid-row-->

	</div>
	<!--/.fluid-container-->

	<!-- external javascript -->

	
	<script>
     //提交
     function complete(){
    	 //判断用户名
    	 //获取username值
    	 
    		var userNameVal = $("#userNameId").val();
    		var passWordVal = $("#passWordId").val();
    			//验证用户名
    			$.ajax({
    				type : "post",
    				url : "<%=basePath%>admin/tokenlogin/initLogin",
    				data: {userName:userNameVal,passWord:passWordVal},
    				success : function(result) {
    					if(result == "success"){
    						window.location.href="<%=basePath%>admin/tokenlogin/initPage";
    					}
    				} 
    			});
    		
}
</script>

</body>
</html>
