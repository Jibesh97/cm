<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String urlGloble = (String)request.getAttribute("urlGloble");
String pileName = (String)request.getAttribute("name");
String content = (String)request.getAttribute("content");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta charset="utf-8" />
	<title>车联网平台监控系统</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	
	<!-- ================== BEGIN BASE CSS STYLE ================== -->
	<link href="<%=basePath%>js/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"  rel="stylesheet" />
	<link href="<%=basePath%>js/plugins/bootstrap/css/bootstrap.min.css"  rel="stylesheet" />
	<link href="<%=basePath%>css/animate.min.css"  rel="stylesheet" />
	<link href="<%=basePath%>css/style.min.css"  rel="stylesheet" />
	<link href="<%=basePath%>css/style-responsive.min.css"  rel="stylesheet" />
	<link href="<%=basePath%>css/theme/default.css"  rel="stylesheet" id="theme" />
	<!-- ================== END BASE CSS STYLE ================== -->
	<link href="<%=basePath%>css/font-awesome.css" rel="stylesheet"/>
	<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
	<link href="<%=basePath%>js/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.css"  rel="stylesheet" />
	<link href="<%=basePath%>js/plugins/bootstrap-datepicker/css/datepicker.css"  rel="stylesheet" />
	<link href="<%=basePath%>js/plugins/bootstrap-datepicker/css/datepicker3.css"  rel="stylesheet" />
    <link href="<%=basePath%>js/plugins/gritter/css/jquery.gritter.css"  rel="stylesheet" />
	<!-- ================== END PAGE LEVEL STYLE ================== -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="<%=basePath%>js/plugins/pace/pace.min.js" ></script>
	<!-- ================== END BASE JS ================== -->
</head>
<style type="text/css">
/* 防止两个div换行  lzd*/
  #maintainNum{
  display:inline-block;
}
#offNum{
  display:inline-block;
}
#chargeNum{
  display:inline-block;
}
#freeNum{
  display:inline-block;
}
.slimScrollDiv {
    margin-top:0px;
}
.panel-inverse{
	border: 1px solid #384449;
}
.panel-title{
	font-size:16px
}
.circles-text,.mcircle{
	color:#b8d6e6
}
.maintain{
	color:#768479
}
.right_centent1{
	margin:0 -10px
}
.right_centent1 p{
	display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;
}
</style>
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
					<img src="<%=basePath%>images/logo.png"  style="float:left;margin-top: 10px;"/>
					<h4 class="nav_mh3" style="letter-spacing:3px;">专用场站监控系统</h4>
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
					<li class="dropdown navbar-user" style="margin-top:18px"><img
							src="<%=basePath%>images/user-13.jpg" alt="" /> <span
							class="hidden-xs">用户名</span>
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
		
		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar sidebar-grid">
			<!-- begin sidebar scrollbar -->
			<div data-scrollbar="true" data-height="100%">
				<!-- begin sidebar user -->
				<ul class="nav">
					<li class="nav-profile left_mnav">
						<div class="info">
							<div class="col-md-6 unwanted"><i class="fa fa-circle"></i>
								<b>空闲</b> : <div id = "freeNum"></div>
							</div>
							<div class="col-md-6 offline"><i class="fa fa-circle"></i>
								<b>离线</b> : <div id = "offNum"></div>
							</div>
							<div class="col-md-6 chargem"><i class="fa fa-circle"></i>
								<b>充电</b> : <div id = "chargeNum"></div>
							</div>
							<div class="col-md-6 maintain"><i class="fa fa-circle"></i>
								<b>维护</b> : <div id = "maintainNum"></div>
							</div>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">
					<li class="has-sub"><a href="<%=basePath%>monitoring/map/initMapPage"><i class="glyphicon glyphicon-eye-open"></i> <span>监控画面</span></a></li>
					<li class="has-sub"><a href="<%=basePath%>monitoring/station/initStationPage"><i class="fa fa-fax"></i> <span>充电站详情</span></a></li>
					<!-- <li class="has-sub"><a href="#"><i class="fa fa-fax"></i> <span>充电站详情</span></a></li>
					<li class="has-sub"><a href="#"><i class="fa fa-home"></i> <span>信息管理</span></a></li>
					<li class="has-sub"><a href="#"><i class="fa fa-user-md"></i> <span>GPS对时</span></a></li>
					<li class="has-sub"><a href="#"><i class="fa fa-user"></i> <span>系统设置</span></a></li>-->
			        <!-- begin sidebar minify button -->
			        <!-- end sidebar minify button -->
				</ul>
				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		<div class="sidebar-bg"></div>
		<!-- end #sidebar -->
		<div class="content_mtop">
			<ol class="breadcrumb_m">
				<li><a class="mbtn_left"><i class="fa fa-angle-left"></i></a></li>
				<li><a href="<%=basePath%>monitoring/map/initMapPage">监控画面</a><b>></b></li>
				<li><a href="<%=basePath%>monitoring/station/initStationPage?id=${stationCode}"><%=pileName%></a><b>></b></li>
				<li class="active"><%=content%></li>
			</ol>
			
		</div>
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<!-- begin row -->
			<div class="row" style="height: 60px;">
				<!-- begin col-3 -->
				
			</div>
			<!-- end row -->
			<!-- begin row -->
			<div class="row">
				<!-- begin col-8 -->
				<div class="col-md-12">
				    
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<h4 class="panel-title"><i class="fa fa-exclamation"></i>　故障信息</h4>
						</div>
						<div class="panel-body mpanel-body">
							<div class="col-md-12">
								<h4 style="font-size: 16px;">
									充电站：沈阳xxx公司
									<b>运行状态：<i id = "operatingStatus"></i></b>
									<b>车辆SOC：<i id = "vehicleSOC"></i></b>
								</h4>
							</div>
							<div class="col-md-9">
								<div class="right_centent" style="height:224px">
									<div class="pie-chart-tiny" data-percent="86">
                                       <div id="canvas">
											<div class="circle" id="circles-1"></div>
											
											<div class="circle" id="circles-2"></div>
											
											<div class="circle" id="circles-3"></div>
											
											<div class="circle" id="circles-4"></div>
											
										</div>
										<div class="mcircle" ><b>故障率</b></div>
										<div class="mcircle"><b>故障修复率</b></div>
										<div class="mcircle"><b>离线率</b></div>
										<div class="mcircle"><b>充电成功率</b></div>	
                                    </div>
                                    
								</div>
								
							</div>
							<div class="col-md-3">
								<div class="right_centent1">
									<p><i class="fa fa-stop text-m3"></i>本月故障次数：<i class="text-m3">2次</i></p>
									<p><i class="fa fa-stop text-m3"></i>本月离线次数：<i class="text-m3">1次</i></p>
									<p><i class="fa fa-stop text-m4"></i>本月充电次数：<i class="text-m4">100次</i></p>
									<p><i class="fa fa-stop text-m1"></i>本月充电度数：<i class="text-m1">5000度</i></p>
									<p><i class="fa fa-stop text-m6" style="color:#f5cc0c"></i>本月售电总额：<i style="color:#f5cc0c">66666666.6元</i></p>
								</div>
							</div>
							
						</div>
					</div>
					
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<h4 class="panel-title"><i class="fa fa-clipboard"></i>　设备信息</h4>
						</div>
						<div class="panel-body mpanel-body mpanel-body1">
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									资产编号：<b>333</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									注册时间：<b>2017-05-06  11:35</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									经度：<b>10.2</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									纬度：<b>22.3</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									海拔：<b>11.1</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									电桩名称：<b><%=content%></b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									电桩地址：<b>沈阳xxx公司</b>
								</h4>
							</div>
						</div>
					</div>
					 
                   
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<h4 class="panel-title"><i class="fa fa-file-text"></i>　基本信息</h4>
						</div>
						<div class="panel-body mpanel-body mpanel-body1">
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									资产类型：<b>充电桩</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									生产厂家：<b>沈阳xxx技术有限公司</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									资产型号：<b>1111111</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									使用年限：<b>6年</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									所在单位：<b>嘀嘀部</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									产权单位：<b>xx供电公司</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									建档日期：<b>2017-05-06 00:00</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									建档人：<b>xxx</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									出厂日期：<b>2017-05-06 00:00</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									单价：<b>0.8-1.9元/度</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									生产批次：<b>0000111111</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									出厂编号：<b>AA256224</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									是否第三方：<b>否</b>
								</h4>
							</div>
						</div>
					</div>
					
					 
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<h4 class="panel-title"><i class="fa fa-align-left"></i>　参数信息</h4>
						</div>
						<div class="panel-body mpanel-body mpanel-body1">
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									电桩种类：<b><%=pileName%></b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									主用端口：<b>8888</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									信号编号：<b>123456</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									充电电机容量：<b>987654</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									备用端口：<b>8887</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									集中器区位码：<b>123456</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									规约类型：<b>x级违规</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									充电桩IC卡槽类型：<b>abs</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									适用车型：<b>aaa</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									终端通信地址码信息：<b>B0D3CASDAC</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									最大输出电压：<b>240</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									最小输出电压：<b>120</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									最大输出电流：<b>120</b>
								</h4>
							</div>
							<div class="col-md-3">
								<h4 style="font-size: 16px;">
									设备型号：<b>SDLN360VDE3</b>
								</h4>
							</div>
						</div>
					</div>
					
				</div>
				<!-- end col-8 -->
				<!-- begin col-4 -->
				<!-- end col-4 -->
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->
		
        <!-- begin theme-panel -->
	</div>
	<!-- end page container -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="<%=basePath%>js/plugins/jquery/jquery-2.1.3.js"></script>
	<script src="<%=basePath%>js/plugins/jquery/jquery-migrate-1.2.1.min.js" ></script>
	<script src="<%=basePath%>js/plugins/jquery-ui/ui/minified/jquery-ui.min.js" ></script>
	<script src="<%=basePath%>js/plugins/bootstrap/js/bootstrap.min.js" ></script>
	<!--[if lt IE 9]>
		<script src="crossbrowserjs/html5shiv.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/crossbrowserjs/html5shiv.js"></script>
		<script src="crossbrowserjs/respond.min.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/crossbrowserjs/respond.min.js"></script>
		<script src="crossbrowserjs/excanvas.min.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js" ></script>
	<script src="<%=basePath%>js/plugins/jquery-cookie/jquery.cookie.js" ></script>
	<!-- ================== END BASE JS ================== -->
	
	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="<%=basePath%>js/plugins/gritter/js/jquery.gritter.js" ></script>
	<script src="<%=basePath%>js/plugins/flot/jquery.flot.min.js" ></script>
	<script src="<%=basePath%>js/plugins/flot/jquery.flot.time.min.js" ></script>
	<script src="<%=basePath%>js/plugins/flot/jquery.flot.resize.min.js" ></script>
	<script src="<%=basePath%>js/plugins/flot/jquery.flot.pie.min.js" ></script>
	<script src="<%=basePath%>js/plugins/sparkline/jquery.sparkline.js" ></script>
	<script src="<%=basePath%>js/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js" ></script>
	<script src="<%=basePath%>js/plugins/jquery-jvectormap/jquery-jvectormap-world-mill-en.js" ></script>
	<script src="<%=basePath%>js/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" ></script>
	<script src="<%=basePath%>js/dashboard.min.js" ></script>
	<script src="<%=basePath%>js/new/circles.js" ></script>
	<script src="<%=basePath%>js/apps.min.js" ></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


<script>
		$(document).ready(function() {
		   //初始化
			App.init();
			Dashboard.init();
			//颜色标记
			pointWhoTopBar();
			webController();
		    self.setInterval("webController()",20000);
		});
		function webController(){
		     $.ajax({
             type: "POST",
             url: "<%=basePath%>monitoring/pile/throughIdQuery",
             data: {stationCode:'${stationCode}',mechanismCode:'${mechanismCode}',pileid:'${pileid}'},
             dataType: "json",
             success: function(data){
			       drawpile(data);
             }
         });
        }
</script>
<script>
	//序列化对象填充到指定div
	function setValue(result){  
	    // 开始遍历     
	    for(var p in result){   
	       // 方法    
	       if(typeof(result[p])=="function"){        
	           // obj[p]();  
	        }else{ 
	            $("#"+p).html(result[p]);  
	            // p 为属性名称，obj[p]为对应属性的值       
	        } 
	    } 
	} 
</script>
<script>
//绘制环形进度条方法
function drawing(faultRate,restoreRate,offRate,successRate){
    var colors = [
		['#3c484f', '#e2565c'], ['#3c484f', '#49c269'], ['#3c484f', '#e2a15c'], ['#3c484f', '#5090ba']
	], circles = [];
	var percentage =[
	   faultRate,restoreRate,offRate,successRate
	   ];
		for (var i = 1; i <= 4; i++) {
			var child = document.getElementById('circles-' + i);
			circles.push(Circles.create({
				id:         child.id,
				value:		percentage[i-1],
				radius:     80,
				width:      8,
				colors:     colors[i-1],
			}));
		}

}
	
</script>
<script>
//左侧颜色标记
  function pointWhoTopBar(){
			var ind = '${mainMenuTag}';
    		$(".nav > .has-sub").each(function(index,element){
    			var tag =$(this).context.innerHTML;
    				var para =tag.split("/");
    				tag = para[5];
    				if(tag == ind ){
    					$(element).removeClass("has-sub");
    					$(element).addClass("active");
    				}
    			});
	}
</script>
<script>
function drawpile(data){
           //转换json
		  var dataJson = eval('('+data+')');
		  console.info(dataJson);
		  //故障率
		  faultRate = dataJson.pileDetail.faultRate;
		  //故障修复率
		  restoreRate = dataJson.pileDetail.restoreRate;
		  //离线率
		  offRate = dataJson.pileDetail.offRate;
		  //成功率
		  successRate = dataJson.pileDetail.successRate;
		  //运行状态
		  runSta = dataJson.pileDetail.runSta;
		  switch (runSta){ 
			//故障
			case "0001" :
				runSta = "故障";
				$("#operatingStatus").text(runSta);
			break; 
			case "0002" :
				runSta = "待机";
				$("#operatingStatus").text(runSta);
			break; 
			case "0003" :
				runSta = "工作";
				$("#operatingStatus").text(runSta);
			break; 
			case "0004" :
				runSta = "离线";
				$("#operatingStatus").text(runSta);
			break; 
			case "0005" :
				runSta = "充满";
				$("#operatingStatus").text(runSta);
			break; 
			default : 
				break; 
		  } 
		  //车辆soc
		  soc = dataJson.pileDetail.soc;
		  $("#vehicleSOC").text(soc + "%");
		  //传递故障率，故障修复率，离线率，充电成功率的数据，在页面显示
		  drawing(faultRate,restoreRate,offRate,successRate);
		  //序列化
		  setValue(dataJson.stationBrief);
}
</script>
</body>
</html>
