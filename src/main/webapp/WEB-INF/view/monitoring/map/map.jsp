<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String urlGloble = (String) request.getAttribute("urlGloble");
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8" />
<title>车联网平台监控系统</title>
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<!-- ================== BEGIN BASE CSS STYLE ================== -->
<link
	href="${pageContext.request.contextPath}/js/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/plugi/js/pluginsawesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/animate.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/css/style-responsive.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/theme/default.css"
	rel="stylesheet" id="theme" />
<link href="<%=basePath%>css/font-awesome.css" rel="stylesheet" />
<!-- ================== END BASE CSS STYLE ================== -->

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link
	href="${pageContext.request.contextPath}/js/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/plugins/gritter/css/jquery.gritter.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/css/datatable/jquery.dataTables.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- ================== BEGIN BASE JS ================== -->
<script
	src="${pageContext.request.contextPath}/js/plugins/pace/pace.min.js"></script>


<!-- ================== END BASE JS ================== -->
</head>
<body style="overflow: hidden;">
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-sidebar-fixed page-header-fixed">
		<!-- begin #header -->
		<div id="header" class="header navbar navbar-inverse navbar-fixed-top">
			<!-- begin container-fluid -->
			<div class="container-fluid">
				<!-- begin mobile sidebar expand / collapse button -->
				<div class="navbar-header">
					<img src="<%=basePath%>images/logo.png"
						style="float:left;margin-top: 10px;" />
					<h4 class="nav_mh3" style="letter-spacing:3px;">车联网平台监控系统</h4>
					<button type="button" class="navbar-toggle"
						data-click="sidebar-toggled">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<!-- end mobile sidebar expand / collapse button -->

				<!-- begin header navigation right -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="javascript:;"
						data-toggle="dropdown"
						class="dropdown-toggle f-s-14 nav_micon_right"> <i
							class="fa fa-bell-o"></i> <span class="label nav_micon_num">7</span>
					</a>
						<ul
							class="dropdown-menu media-list pull-right animated fadeInDown">
							<li class="dropdown-header">提示信息（预留）</li>
							<li class="media"><a href="javascript:;">
									<div class="media-left">
										<i class="fa fa-envelope media-object bg-blue"></i>
									</div>
									<div class="media-body">
										<h6 class="media-heading">提示信息</h6>
										<div class="text-muted f-s-11">1小时</div>
									</div>
							</a></li>
							<li class="media"><a href="javascript:;">
									<div class="media-left">
										<i class="fa fa-envelope media-object bg-blue"></i>
									</div>
									<div class="media-body">
										<h6 class="media-heading">提示信息</h6>
										<div class="text-muted f-s-11">1小时</div>
									</div>
							</a></li>
							<li class="media"><a href="javascript:;">
									<div class="media-left">
										<i class="fa fa-envelope media-object bg-blue"></i>
									</div>
									<div class="media-body">
										<h6 class="media-heading">提示信息</h6>
										<div class="text-muted f-s-11">1小时</div>
									</div>
							</a></li>
							<li class="dropdown-footer text-center"><a
								href="javascript:;">查看更多</a></li>
						</ul></li>
					<li class="dropdown navbar-user" style="margin-top:18px"><img
						src="<%=basePath%>images/user-13.jpg" alt="" /> <span
						class="hidden-xs">用户名</span></li>
					<li><a href="#" style="padding: 14px 15px;"> <i
							class="fa fa-2x fa-power-off"></i>
					</a></li>
				</ul>
				<!-- end header navigation right -->
			</div>
			<!-- end container-fluid -->
		</div>
		<!-- end #header -->
		<div class="">
			<div class="col-md-12 mfloat">
				<div class="pull-left">
					<div class="right_top_mcion">
						<div style="position: relative;">
							<span id="left1" class="label mlabel_color1 mlabel"
								style="letter-spacing:2px;float:left" onclick="find('2')">严重告警<Span
								id="stationAlarmNumButton" style="letter-spacing:1px;"></span></span> <span
								class="label mlabel_color2 mlabel" id="left2"
								style="letter-spacing:2px;" onclick="find('1')">一般告警<Span
								id="pileAlarmNumButton" style="letter-spacing:1px;"></span></span> <span
								id="left3" class="fa-stack fa-2x text-m4"> <i
								class="fa fa-square fa-stack-2x"></i> <i
								class="glyphicon glyphicon-search fa-stack-1x fa-inverse"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="pull-right mpull-right" style="height:40px">
					<div class="right_top_mcion mbcion" style="width:60px;height:60px">
						<div style="position: relative;">
							<a
								href="${pageContext.request.contextPath}/monitoring/alarm/initAlarmPage"><span
								class="label mlabel_color1 mlabel"
								style="cursor:pointer;right:0px"><i
									class="glyphicon glyphicon-warning-sign"></i></span> </a>
						</div>
					</div>
				</div>


			</div>


			<div id="cityTab" class="map-float-table width-sm hidden-xs"
				style="display:none;">
				<h4 class="m-t-0">
					城市切换<a onclick="hideCity()"><i class="fa fa-forward pull-right"
						style="cursor:pointer;padding:0 10px"></i></a>
				</h4>
				<ul class="nav" style="letter-spacing:2px;">
					<li class="nav-profile left_mnav left_mnav1">
						<div class="info">
							<div class="col-md-6" style="color:#fff">
								当前位置： <span id="cityName">${city} </span>
							</div>
							<div class="col-md-6 chargem">
								<i class="fa fa-circle"></i> <b>总数</b>：<span id="allNum">加载中..
								</span>
							</div>
							<div class="col-md-6 offline">
								<i class="fa fa-circle"></i> <b>故障</b>：<span id="faultNum">加载中..
								</span>
							</div>
							<div class="col-md-6 maintain">
								<i class="fa fa-circle"></i> <b>离线</b>：<span id="offLineNum">加载中..
								</span>
							</div>
						</div>
					</li>
				</ul>
				<div data-scrollbar="true" class="height-md">
					<div class="panel-group" id="accordion">
						<div class="panel panel-inverse overflow-hidden">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a class="accordion-toggle accordion-toggle-styled collapsed"
										data-toggle="collapse" data-parent="#accordion"
										href="#collapseOne"> <span class="m33"><i
											class="fa fa-circle"></i>全国</span> <span class="m34"><i
											class="fa fa-circle"></i>39404</span> <span class="m35"><i
											class="fa fa-circle"></i>260</span>

									</a>
								</h3>
							</div>
							<div id="collapseOne" class="panel-collapse collapse">
								<div class="panel-body m_panel-body">
									<span class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span> <span
										class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span> <span
										class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span> <span
										class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span>
								</div>
							</div>
						</div>
						<div class="panel panel-inverse overflow-hidden">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a class="accordion-toggle accordion-toggle-styled collapsed"
										data-toggle="collapse" data-parent="#accordion"
										href="#collapseTwo"> <span class="m33"><i
											class="fa fa-circle"></i>全国</span> <span class="m34"><i
											class="fa fa-circle"></i>39404</span> <span class="m35"><i
											class="fa fa-circle"></i>260</span>

									</a>
								</h3>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse">
								<div class="panel-body m_panel-body">
									<span class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span> <span
										class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span> <span
										class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span> <span
										class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span>
								</div>
							</div>
						</div>
						<div class="panel panel-inverse overflow-hidden">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a class="accordion-toggle accordion-toggle-styled collapsed"
										data-toggle="collapse" data-parent="#accordion"
										href="#collapseThree"> <span class="m33"><i
											class="fa fa-circle"></i>全国</span> <span class="m34"><i
											class="fa fa-circle"></i>39404</span> <span class="m35"><i
											class="fa fa-circle"></i>260</span>

									</a>
								</h3>
							</div>
							<div id="collapseThree" class="panel-collapse collapse">
								<div class="panel-body m_panel-body">
									<span class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span> <span
										class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span> <span
										class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span> <span
										class="m33"><i class="fa fa-circle"></i>全国</span> <span
										class="m34"><i class="fa fa-circle"></i>39404</span> <span
										class="m35"><i class="fa fa-circle"></i>260</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="mfloat_bottom_right">
				<div id='cityButton' onclick="showCity()" class="mbt"
					style="cursor:pointer">
					<i class="glyphicon glyphicon-map-marker"></i> ${city} <i
						class="glyphicon glyphicon-chevron-down"></i>
				</div>
			</div>

			<div class="mmap">
				<!-- 高的需要大一些，隐藏百度logo标志 -->
				<iframe id="mapIndex" width="100%" frameborder="0"></iframe>
			</div>

		</div>
		<!-- begin #content -->
		<!-- end #content -->

		<!-- begin theme-panel -->
	</div>
	<!-- end page container -->

	<!-- ================== BEGIN BASE JS ================== -->
	<script
		src="${pageContext.request.contextPath}/js/plugins/jquery/jquery-2.1.3.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/jquery/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf8"
		src="${pageContext.request.contextPath}/js/datatables/jquery.dataTables.js"></script>
	<!--[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script
		src="${pageContext.request.contextPath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script
		src="${pageContext.request.contextPath}/js/plugins/gritter/js/jquery.gritter.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.time.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.resize.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/flot/jquery.flot.pie.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/sparkline/jquery.sparkline.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/plugins/jquery-jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/dashboard.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/apps.min.js"></script>

	<!-- ================== END PAGE LEVEL JS ================== -->
 
	<script type="text/javascript">
		function send() {

        }

        var showCityName = "${city}";

        $(document)
                .ready(
                        function() {

	                        $("#mapIndex").height($(document).height())
	                        App.init();
	                        Dashboard.init();
	                        $("cityTab").hide();
	                        document.getElementById("mapIndex").src = "${pageContext.request.contextPath}/monitoring/map/map?city=" + encodeURIComponent(encodeURIComponent("${city}"));
							getData();		
                        });

        window.setInterval(getData, "${refreshTime}");

    /**
    *获取数据
    */
        function getData() { 
     		 $.ajax({
					cache : false,//每次都会向服务器请求
					async : false,//同步
					type : 'POST',//post请求
					dataType : "json",
					url : '${pageContext.request.contextPath}/monitoring/map/searchMap?orgIds=${jurisdiction}',
	            success : function(result) {

		        getMassage(result)

	            } });
 
        }

        /*更改查询条件
         */
        function find(type) {
	        document.getElementById("mapIndex").contentWindow.showDot(type);

        }

        /**切换城市
         */
        function pickCity(city, allNum, faultNum, offLineNum, ga, ca, tsn, isCutCity) {
	        showCityName = city;

	        $("#cityButton").attr('value', city);

	        $('#cityName').html(city);
	        $('#allNum').html(allNum);
	        $('#faultNum').html(faultNum);
	        $('#offLineNum').html(offLineNum);

	        $('#stationAlarmNumButton').html(formatting(ca));
	        $('#pileAlarmNumButton').html(formatting(ga));
	        $('#AllNumButton').html(formatting(tsn));

	        if (isCutCity) {
		        document.getElementById("mapIndex").contentWindow.pickCity(city);//切换城市

	        }

        }
        /**
         *判空
         */
        function formatting(val) {

	        if (val == "null") {
		        return 0;
	        }

	        if (val == null) {
		        return 0;
	        }
	        return val;
        }
        /**
         *刷新地图
         */
        function getMap(result) {
	        try {
		        document.getElementById("mapIndex").contentWindow.loaderData(result);//刷新地图页

	        } catch (e) {
		        setTimeout(function() {
			        getMap(result);
		        }, 500)

	        }

        }

        /**获取数据
         *根据地图修改数据
         */

        function getMassage(data) { 
        
	        var result = data.citys;

	        getMap(result);

	        var tbBody = "";
	        
	         	var ids= new Array();;// 全部桩数量
	          
	            $(".panel-collapse.collapse.in").each(function(){
	            	ids.push($(this).attr("id"));	
	            });
	      
	        

  for (var i = 0; i < result.length; i++) {
	        
	        
	        "<tr onclick=pickCity('"
		                + result[i].local
		                + "','"
		                + result[i].allNum
		                + "','"
		                + result[i].faultNum
		                + "','"
		                + result[i].offNum
		                + "','"
		                + result[i].ga
		                + "','"
		                + result[i].ca
		                + "','"
		                + result[i].tsn
		                + "','true') >"
	         
	        
	     tbBody += "   <div class='panel panel-inverse overflow-hidden'>"
						+"		<div class='panel-heading' onclick=pickCity('"    
						 + result[i].local
		                + "','"
		                + result[i].allNum
		                + "','"
		                + result[i].faultNum
		                + "','"
		                + result[i].offNum
		                + "','"
		                + result[i].ga
		                + "','"
		                + result[i].ca
		                + "','"
		                + result[i].tsn
						+ "','true') >" 
						+"			<h3 class='panel-title'>"
						+"				<a class='accordion-toggle accordion-toggle-styled collapsed' data-toggle='collapse' data-parent='#accordion' href='#collapse"+i+"'>"
						+"				    <span class='m33'><i class='fa fa-circle'></i>"+result[i].local+"</span> "
						+"				    <span class='m34'><i class='fa fa-circle'></i>"+result[i].allNum+"</span> "
						+"				    <span class='m35'><i class='fa fa-circle'></i>"+result[i].faultNum+"</span> " 
						+"				</a>"
						+"			</h3>"
						+"		</div>";
						try{
						var num=result[i].citys.length;
						
						tbBody += "	<div id='collapse"+i+"' class='panel-collapse collapse'>"
								+"<div class='panel-body m_panel-body'>";
						
							 for (var j = 0; j < result[i].citys.length; j++) {
									  tbBody += "<span class='m33'><i class='fa fa-circle'></i>"+result[i].citys[j].local+"</span>  "
									+"<span class='m34'><i class='fa fa-circle'></i>"+result[i].citys[j].allNum+"</span> "
									+"<span class='m35'><i class='fa fa-circle'></i>"+result[i].citys[j].faultNum+"</span> ";
						    
						}
						
						   tbBody +=	
						   "		</div>"
							+"	</div>"
						
						
						}catch(e){
						
						}
				 
			    tbBody +="</div>";
	         

	        }

 
	        $("#accordion").empty();
	        $("#accordion").append(tbBody);
	        
	        
				    for (var i = 0; i < ids.length; i++) {
				     $("#"+ids[i]).addClass("in");
				     $("[href='#"+ids[i]+"']").removeClass("collapsed");
				      
				    
				    }

	        for (var i = 0; i < result.length; i++) {
	        
	       
	        
		        if (showCityName == result[i].local) {
			        pickCity(result[i].city, result[i].allNum, result[i].faultNum, result[i].offNum, result[i].ga, result[i].ca,
			                result[i].tsn, false);

		        }
	        }

        }

        /**
         *隐藏城市列表
         */
        function hideCity() {
	        document.getElementById("cityTab").style.display = "none";
	        document.getElementById("cityButton").style.display = "";

        }
        /**
         *显示城市列表
         */
        function showCity() {
	        document.getElementById("cityTab").style.display = "";
	        document.getElementById("cityButton").style.display = "none";

        }

        $(".pull-left .right_top_mcion .mlabel").click(function() {
	        $(".right_top_mcion span").removeClass("mclick");
	        $(this).addClass("mclick");
        })
        $(".mbcion span").click(function() {
	        $(".mbcion span").removeClass("mclick1");
	        $(this).addClass("mclick1");
        })

        window.onload = function() {
	        function ti() {
		        var explorer = navigator.userAgent;
		        //ie 
		        if (explorer.indexOf("MSIE") >= 0) {}
		        //firefox 
		        else if (explorer.indexOf("Firefox") >= 0) {
			        $("#left2").css("left", $("#left1").width() + 40);
			        $("#left3").css("margin-left", $("#left1").width() + $("#left2").width() + 75)
		        }
		        //Chrome
		        else if (explorer.indexOf("Chrome") >= 0) {
			        $("#left2").css("left", $("#left1").width() + 40);
			        $("#left3").css("margin-left", $("#left1").width() + $("#left2").width() + 75)
		        }
	        }
	        self.setInterval(function() {
		        ti();
	        }, 100)
        }

        /**
         *查看详情跳转
         */
        function tz(id, localCode) {
	        location.href = ("${pageContext.request.contextPath}/monitoring/station/initStationPage?id=" + id + "&localCode=" + localCode);
        }
	</script>
</body>
</html>
