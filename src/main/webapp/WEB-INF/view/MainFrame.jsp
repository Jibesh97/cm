<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="cache-control" content="no-cache" />
<title>管理系统</title>

<!-- 系统加载的css js文件统一写到HtmlContext.jsp中进行管理 -->
<%@ include file="HtmlContext.jsp"%>

	</head>
		
		<body scroll="no">
		
			<div id="layout">
				<div id="header">
					<div class="headerNav">
						<a class="logo" href="#">标志</a>
						<ul class="nav">
							<li><a id="usersHomePage" title="欢迎">欢迎：${userInfo.userName}</a>
							</li>
							<li><a href="loginBackstageAction.do?method=changePwdInit"
								target="dialog" rel="repassword" title="变更密码" mask="true">变更密码</a>
							</li>
							<li><a
								href="<%=basePath%>loginBackstageAction.do?method=backstageLogout">退出系统</a>
							</li>
						</ul>
					</div>
				</div>
		
				<div id="leftside">
					<div id="sidebar_s">
						<div class="collapse">
							<div class="toggleCollapse">
								<div></div>
							</div>
						</div>
					</div>
					<div id="sidebar">
						<div class="toggleCollapse">
							
							<div>收缩</div>
						</div>
						<div class="accordion" fillSpace="sidebar">
							<div class="accordionHeader">
								<h2>
									<span>Folder</span>系统管理
								</h2>
							</div>
							<div class="accordionContent">
								<ul class="tree treeFolder">
									<li>
										<a href="<%=basePath%>system/sysuser/querySysUserList"
										target="navTab" rel="page1">用户管理</a>
									</li>
									<li>
										<a href="#"
										target="navTab" rel="page2">角色管理</a>
									</li>
								</ul>
							</div>
							
							<div class="accordionHeader">
								<h2>
									<span>Folder</span>XXX管理
								</h2>
							</div>
							<div class="accordionContent">
								<ul class="tree treeFolder">
									<li>
										<a href="productAction.do?method=showGoodsType"
										target="navTab" rel="page1">XXX管理</a>
									</li>
								</ul>
							</div>
							
						</div>
					</div>
				</div>
				<div id="container">
					<div id="navTab" class="tabsPage">
						<div class="tabsPageHeader">
							<div class="tabsPageHeaderContent">
								<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
								<ul class="navTab-tab">
									<li tabid="main" class="main">
										<a href="javascript:;">
											<%-- <span>
												<span class="home_icon">
													<bean:message key="base.title.userHomePage" />
												</span>
											</span> --%>
										</a>
									</li>
								</ul>
							</div>
							<div class="tabsLeft">left</div>
							<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
							<div class="tabsRight">right</div>
							<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
						</div>
						<div class="navTab-panel tabsPageContent layoutBox">
							<div class="page unitBox">
								<div class="accountInfo">
									<div class="alertInfo">
										<h2>
											<input type="hidden" />
										</h2>
									</div>
								</div>
								<img src = "images/Simple_atmospheric_connotation.jpg"/>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<script type="text/javascript">
			
			var dwzContext = '<%=basePath%>js/dwz/dwz.frag.xml';
				$(function() {
					DWZ.init(dwzContext, {
						loginUrl : "login_dialog.html",
						loginTitle : "登录", // 弹出登录对话框
						//		loginUrl:"login.html",	// 跳到登录页面
						statusCode : {
							ok : 200,
							error : 300,
							timeout : 301
						}, //【可选】
						pageInfo : {
							pageNum : "pageNum",
							numPerPage : "numPerPage",
							orderField : "orderField",
							orderDirection : "orderDirection"
						}, //【可选】
						debug : false, // 调试模式 【true|false】
						callback : function() {
							initEnv();
							$("#themeList").theme({
								themeBase : "themes"
							}); // themeBase 相对于index页面的主题base路径
						}
					});
			
				});
			
				document.oncontextmenu = function(e) {
					return false;
				};
			</script>
			<script language="JavaScript">
				$(document).ready(function() {
					//监听点击回车
					$(this).keydown(function(event) {
						if (event.keyCode == DWZ.keyCode.ENTER)
							return false; //屏蔽回车提交
					});
				});
			</script>
			<script language="JavaScript">
			<!--
				javascript: window.history.forward(1);
			//-->
			</script>
			<script language="JavaScript">
			function  getMassage(data) {
			     console.info(data);
			
			}
				</script>
		
	</body>
</html>