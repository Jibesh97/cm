<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String urlGloble = (String)request.getAttribute("urlGloble");
%>

<link href="<%=basePath%>themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=basePath%>uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>front/css/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath%>css/general/detail.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="<%=basePath%>js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=basePath%>xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="<%=basePath%>uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>


<script src="<%=basePath%>js/dwz/dwz.core.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.util.date.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.validate.method.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.regional.zh.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.barDrag.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.drag.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.tree.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.accordion.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.ui.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.theme.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.switchEnv.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.alertMsg.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.contextmenu.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.navTab.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.tab.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.resize.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.dialog.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.sortDrag.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.cssTable.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.stable.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.taskBar.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.ajax.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.pagination.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.database.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.datepicker.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.effects.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.panel.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.checkbox.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.history.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.combox.js" type="text/javascript"></script>
<script src="<%=basePath%>js/dwz/dwz.print.js" type="text/javascript"></script>

<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="<%=basePath%>js/dwz/dwz.regional.zh.js" type="text/javascript"></script>

<script src="<%=basePath%>js/json/json2.js" type="text/javascript"></script>
<script src="<%=basePath%>js/md5/md5.min.js" type="text/javascript"></script>

<script src="<%=basePath%>js/common/i18n/MessageResource.zh.js"
	type="text/javascript"></script>
	
<!-- 自定义js工具类 -->
<script type="text/javascript" src="<%=basePath%>js/common/global.util.listForm.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common/global.util.date.js"></script>
<script type="text/javascript" >
var urlGloble = "<%=urlGloble%>";
//console.info(urlGloble);
var basePath = "<%=basePath%>";
</script>
<!-- websocket -->
<script type="text/javascript" src="<%=basePath%>js/websocket/websocket.js"></script>
<!-- websocket -->

<%--
<!-- 处理国际化js -->
<script src="<%=basePath%>js/common/global.util.i18n.js" type="text/javascript"></script>
<!-- js国际化处理 -->
<%
	java.util.Locale lang = (java.util.Locale) session
			.getAttribute("org.apache.struts.action.LOCALE");
	if (null != lang && lang.toString().startsWith("zh")) {
%>
<!-- 中文 -->
<script src="<%=basePath%>js/dwz/dwz.regional.zh.js" type="text/javascript"></script>

<script src="<%=basePath%>js/common/i18n/MessageResource.zh.js"
	type="text/javascript"></script>
<%
	} else {
%>
<!-- 英文 -->
<script src="<%=basePath%>js/common/i18n/MessageResource.en.js"
	type="text/javascript"></script>
<%
	}
%> --%>
