<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String urlGloble = (String)request.getAttribute("urlGloble");
String orgId = (String)request.getAttribute("orgId");
%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
	<meta charset="utf-8" />
	<title>Color Admin | Dashboard</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	
	<!-- ================== BEGIN BASE CSS STYLE ================== -->
	<link href="<%=basePath %>js/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"  rel="stylesheet" />
	<link href="<%=basePath %>js/plugins/bootstrap/css/bootstrap.min.css"  rel="stylesheet" />
	<link href="<%=basePath %>js/plugins/font-awesome/css/font-awesome.min.css"  rel="stylesheet" />
	<link href="<%=basePath %>css/animate.min.css"  rel="stylesheet" />
	<link href="<%=basePath %>css/style.min.css"  rel="stylesheet" />
	<link href="<%=basePath %>css/style-responsive.min.css"  rel="stylesheet" />
	<link href="<%=basePath %>css/theme/default.css"  rel="stylesheet" id="theme" />
	<!-- ================== END BASE CSS STYLE ================== -->
	
	<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
	<link href="<%=basePath %>js/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.css"  rel="stylesheet" />
	<link href="<%=basePath %>js/plugins/bootstrap-datepicker/css/datepicker.css"  rel="stylesheet" />
	<link href="<%=basePath %>js/plugins/bootstrap-datepicker/css/datepicker3.css"  rel="stylesheet" />
    <link href="<%=basePath %>js/plugins/gritter/css/jquery.gritter.css"  rel="stylesheet" />
    <link href="<%=basePath %>css/datatable/jquery.dataTables.css"  rel="stylesheet" />
	<!-- ================== END PAGE LEVEL STYLE ================== -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="<%=basePath %>js/plugins/pace/pace.min.js" ></script>
	<!-- ================== END BASE JS ================== -->
</head>
<body>
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<div id="page-container" class="fade page-sidebar-fixed page-header-fixed">
		<!-- begin #header -->
		<div id="header" class="header navbar navbar-inverse navbar-fixed-top">
			<!-- begin container-fluid -->
			<div class="container-fluid">
				<!-- begin mobile sidebar expand / collapse button -->
				<div class="navbar-header">
					<h4 class="nav_mh3">专用场站监控系统</h4>
					<a class="nav_micon" href="javascript:;" data-click="sidebar-minify"></a>
					<button type="button" class="navbar-toggle" data-click="sidebar-toggled">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<!-- end mobile sidebar expand / collapse button -->
				
				<!-- begin header navigation right -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="javascript:;" data-toggle="dropdown" class="dropdown-toggle f-s-14 nav_micon_right">
							<i class="fa fa-bell-o"></i>
							<span class="label nav_micon_num">7</span>
						</a>
						<ul class="dropdown-menu media-list pull-right animated fadeInDown">
                            <li class="dropdown-header">提示信息（预留）</li>
                            <li class="media">
                                <a href="javascript:;">
                                    <div class="media-left"><i class="fa fa-envelope media-object bg-blue"></i></div>
                                    <div class="media-body">
                                        <h6 class="media-heading"> 提示信息</h6>
                                        <div class="text-muted f-s-11">1小时</div>
                                    </div>
                                </a>
                            </li>
                            <li class="media">
                                <a href="javascript:;">
                                    <div class="media-left"><i class="fa fa-envelope media-object bg-blue"></i></div>
                                    <div class="media-body">
                                        <h6 class="media-heading"> 提示信息</h6>
                                        <div class="text-muted f-s-11">1小时</div>
                                    </div>
                                </a>
                            </li>
                            <li class="media">
                                <a href="javascript:;">
                                    <div class="media-left"><i class="fa fa-envelope media-object bg-blue"></i></div>
                                    <div class="media-body">
                                        <h6 class="media-heading"> 提示信息</h6>
                                        <div class="text-muted f-s-11">1小时</div>
                                    </div>
                                </a>
                            </li>
                            <li class="dropdown-footer text-center">
                                <a href="javascript:;">查看更多</a>
                            </li>
						</ul>
					</li>
					<li class="dropdown navbar-user">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
							<img src="<%=basePath %>images/user-13.jpg"  alt="" /> 
							<span class="hidden-xs">用户名</span>
						</a>
					</li>
					<li>
						<a href="#" style="padding: 14px 15px;">
							<i class="fa fa-2x fa-power-off"></i>
						</a>
					</li>
				</ul>
				<!-- end header navigation right -->
			</div>
			<!-- end container-fluid -->
		</div>
		<!-- end #header -->
		<div class="content_mtop">
			<div class="col-md-12">
			<div class="pull-left">
				<div class="right_top_mcion">
					<span class="label mlabel_color1 mlabel">故障260</span>
					<span class="label mlabel_color2 mlabel">派发120</span>
					<span class="label mlabel_color3 mlabel">处理106</span>
					<span class="label mlabel_color4 mlabel">办结34</span>
					<span class="fa-stack fa-2x">
						<i class="fa fa-square fa-stack-2x"></i>
						<i class="fa fa-tablet fa-stack-1x fa-inverse"></i>
					</span>
				</div>
			</div>
			<div class="pull-right">
				<div class="right_top_mcion">
				<span class="fa-stack fa-2x">
					<i class="fa fa-square fa-stack-2x"></i>
					<i class="fa fa-tablet fa-stack-1x fa-inverse"></i>
				</span>
				<span class="fa-stack fa-2x text-m2">
					<i class="fa fa-square fa-stack-2x"></i>
					<i class="fa  fa-tablet fa-stack-1x fa-inverse"></i>
				</span>
				</div>
				<div class="right_top_mcion">
				<span class="fa-stack fa-2x text-m4">
					<i class="fa fa-square fa-stack-2x"></i>
					<i class="fa  fa-tablet fa-stack-1x fa-inverse"></i>
				</span>
				<span class="fa-stack fa-2x text-m5">
					<i class="fa fa-square fa-stack-2x"></i>
					<i class="fa  fa-tablet fa-stack-1x fa-inverse"></i>
				</span>
				</div>
			</div>
			</div>
			<div class="col-md-12 mtable">
				<table id="example" class="display" cellspacing="0" width="100%">
			        <thead>
			            <tr>
			                <th><input type="checkbox"/></th>
			                <th>序号</th>
			                <th>工单编号</th>
			                <th>工单状态</th>
			                <th>充电站名称</th>
			                <th>充电桩编号</th>
			                <th>故障类型</th>
			                <th>故障时间</th>
			                <th>处理计时</th>
			                <th>处理数</th>
			                <th>故障明细</th>
			            </tr>
			        </thead>
			 
			        <tbody>
			        <!--  
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            <tr>
			                <td><input type="checkbox"/></td>
			                <td>001</td>
			                <td>20170422124731</td>
			                <td>处理中</td>
			                <td>北京市门头沟区影剧院专用充电站(内部)</td>
			                <td>11402900000000296</td>
			                <td>充电桩故障</td>
			                <td>2017-04-22 12:47:31</td>
			                <td>00:14:31</td>
			                <td>急停按钮动作故障；电度表异常故障</td>
			            </tr>
			            -->
			        </tbody>
			    </table>
			</div>
		</div>
		<!-- begin #content -->
		<!-- end #content -->
		
        <!-- begin theme-panel -->
	</div>
	<!-- end page container -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="<%=basePath %>js/plugins/jquery/jquery-2.1.3.js"></script>
	<script src="<%=basePath %>js/plugins/jquery/jquery-migrate-1.2.1.min.js" ></script>
	<script src="<%=basePath %>js/plugins/jquery-ui/ui/minified/jquery-ui.min.js" ></script>
	<script src="<%=basePath %>js/plugins/bootstrap/js/bootstrap.min.js" ></script>
	<script type="text/javascript" charset="utf8" src="<%=basePath %>js/datatables/jquery.dataTables.js"></script>
	<!--[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script src="<%=basePath %>js/plugins/slimscroll/jquery.slimscroll.min.js" ></script>
	<script src="<%=basePath %>js/plugins/jquery-cookie/jquery.cookie.js" ></script>
	<!-- ================== END BASE JS ================== -->
	
	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="<%=basePath %>js/plugins/gritter/js/jquery.gritter.js" ></script>
	<script src="<%=basePath %>js/plugins/flot/jquery.flot.min.js" ></script>
	<script src="<%=basePath %>js/plugins/flot/jquery.flot.time.min.js" ></script>
	<script src="<%=basePath %>js/plugins/flot/jquery.flot.resize.min.js" ></script>
	<script src="<%=basePath %>js/plugins/flot/jquery.flot.pie.min.js" ></script>
	<script src="<%=basePath %>js/plugins/sparkline/jquery.sparkline.js" ></script>
	<script src="<%=basePath %>js/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js" ></script>
	<script src="<%=basePath %>js/plugins/jquery-jvectormap/jquery-jvectormap-world-mill-en.js" ></script>
	<script src="<%=basePath %>js/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" ></script>
	<script src="<%=basePath %>js/dashboard.min.js" ></script>
	<script src="<%=basePath %>js/apps.min.js" ></script>
	
	<!-- ================== END PAGE LEVEL JS ================== -->
	
	<script>
		$(document).ready(function() {
			App.init();
			Dashboard.init();
		});
		$(document).ready(function() {
		     $('#example').dataTable({
			     	//"bAutoWidth" : true,
			     	"bInfo" : false,
			     	"bFilter" : false,
			     	"bSort" : false,
			     	//"order": [[ 7, "desc" ]],
			     	"iDisplayLength":5,
			     	"bLengthChange": false,
			     	"bStateSave":true,
			     	"aLengthMenu": 20,
				        "columns" : [{  
			               "bSortable": false,
			                "data" : "",  
			                "width":"100px",
			                "render": function(obj) {
			                	//_'+obj+' 
	                	    	return '<input type="checkbox" id="datasetOriginalId"  name="originalCheck"  value="'+obj+'" >';
	                	    } 
			            }, {  
			                "data" : "orderId",  
			                "sClass" : "center",
			                
			            }, {  
			            	"data":"orderId",
			                "sClass" : "center"
			            }
			            , {  
			                "data" : "orderState",  
			                "sClass" : "center",
			            },
			            {  
			                "data" : "station",  
			                "sClass" : "center",
			            },
			            {  
			                "data" : "pile",  
			                "sClass" : "center",
			            },{  
			                "data" : "faultType",  
			                "sClass" : "center",
			            }
			           ,{  
			                "data" : "faultTime",  
			                "sClass" : "center",
			            },{  
			                "data" : "faultTimer",  
			                "sClass" : "center",
			            },
			            {  
			                "data" : "counter",  
			                "sClass" : "center",
			            },
			            {  
			                "data" : "faultDetail",  
			                "sClass" : "center",
			            }
			            ],
			             //数据源名称可点击，查看数据集中的数据
			            "fnCreatedRow": function( nRow, aData, iDataIndex ) {
			            var api = this.api();
			                api.column(1).nodes().each(function(cell, i) {
			                  cell.innerHTML =  i + 1;
			                });
			              /*
			               //var url="<%=basePath%>ml/original/getOriginalDetailList?filePath="+aData.filePath+"&originalType="+aData.originalType;
			                //var url="<%=basePath%>ml/original/getOriginalDetailList?filePath="+aData.filePath+"";
			               // $('td:eq(1)', nRow).html( '<a href='+url+'  id="dataSetName"  name="name">'+aData.name+'</a>' );
			              var passUrl = "<%=basePath%>ml/original/getHiveDetailPageOri?id="+aData.id+"&originalType="+aData.fileTypeName;
			                	$('td:eq(1)', nRow).html( '<a href='+passUrl+'  id="dataSetName"  name="name">'+aData.name+'</a>' );
			              } 
			          */
			           } 
			            
			             
		         });
		});
	</script>
	<script type="text/javascript" >
var urlGloble = "<%=urlGloble%>";
//console.info(urlGloble);
var basePath = "<%=basePath%>";
var url = "ws://"+basePath.substring(6,basePath.length)+urlGloble+"?orgId=<%=orgId%>";
</script>
<script type="text/javascript" src="<%=basePath%>js/websocket/jsUtils.js"></script>
<!-- websocket -->
<script type="text/javascript" src="<%=basePath%>js/websocket/websocket.js"></script>
<!-- websocket -->

<script type="text/javascript">  

			function send(){
				
			}
            //记录上一次数据的信息 
            var oldJson;
            //桩id为key 下标为value的json
            var oldZid = {};
            //记录更新了几条数据
            //记录剔除的数据数
            var deleteRow = 0;
            
            console.info(url);
            
			function  getMassage(data) {
				var dataJson = eval('('+data+')');
				console.info(dataJson);
				}
		
			
</script>
</body>
</html>

