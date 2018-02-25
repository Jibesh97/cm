<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String urlGloble = (String)request.getAttribute("urlGloble");
String mechanismCode = (String)request.getAttribute("mechanismCode");
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
	
	<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
	<link href="<%=basePath%>js/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.css"  rel="stylesheet" />
	<link href="<%=basePath%>js/plugins/bootstrap-datepicker/css/datepicker.css"  rel="stylesheet" />
	<link href="<%=basePath%>js/plugins/bootstrap-datepicker/css/datepicker3.css"  rel="stylesheet" />
    <link href="<%=basePath%>js/plugins/gritter/css/jquery.gritter.css"  rel="stylesheet" />
	<!-- ================== END PAGE LEVEL STYLE ================== -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="<%=basePath%>js/plugins/pace/pace.min.js" ></script>
	<link href="<%=basePath%>css/font-awesome.css" rel="stylesheet"/>
	<!-- ================== END BASE JS ================== -->
</head>
<style type="text/css">
/* 防止两个div换行  lzd*/
  #freeNum{
  display:inline-block;
}
#offNum{
  display:inline-block;
}
#chargeNum{
  display:inline-block;
}
#maintainNum{
  display:inline-block;
}
.slimScrollDiv {
    margin-top:0px;
}
.fa-dot-circle-o{
	font-size:20px;
	transform:translate(0px,2px);
	margin-left:15px
}
.mdc_pile2 .fa-dot-circle-o{
	color:#17cc3d
}
.mdc_pile1 .fa-dot-circle-o{
	color:#17cc3d
}
.mprogress-bar b{
	padding-left:15px
}
.mpanel-body{
	padding-top:30px !important
}
.fa-dot-circle-o{
    position: absolute;
    left: 260;
    top: 3;
}
.content_mtop {
	padding:0
}
.maintain{
	color:#768479
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
					<li class="has-sub"><a href="<%=basePath%>monitoring/alarm/initAlarmPage"><i class="fa fa-home"></i> <span>处理列表</span></a></li>
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
			</ol>
			<div class="pull-right">
			</div>
		</div>
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<!-- begin row -->
			<div class="row" >
				<!-- begin col-3   style="height: 60px;" -->
				
			</div>
			<!-- end row -->
			<!-- begin row -->
			<div class="row">
				<!-- begin col-8 -->
				<div class="col-md-12">
				    
					<div class="panel panel-inverse mpanel-inverse" data-sortable-id="index-1">
						<div class="panel-body mpanel-body"  id = "stationInformation" style = "padding-top:10px">
						  <!-- 拼接直流桩信息  -->
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
	<script src="<%=basePath%>js/apps.min.js" ></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
<script>
		$(document).ready(function() {
		   //初始化
			App.init();
			Dashboard.init();
			pointWhoTopBar();
			webController();
			self.setInterval("webController()",50000);
		});
		function webController(){
		     $.ajax({
             type: "POST",
             url: "<%=basePath%>monitoring/station/throughIdQuery",
             data: {stationCode:'${stationCode}',mechanismCode:'${mechanismCode}'},
             dataType: "json",
             success: function(data){
                   //转换json
			       var dataJson = eval('('+data+')');
			       console.info(dataJson);
			        //序列化
			       setValue(dataJson);
			       //画桩
			       drawStation(dataJson);
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
	
	//动态获取屏幕高度 网页可见区域高
	$(function(){  
               $("#stationInformation").css("height",document.body.clientHeight - 90);
            });  
            
          
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
function drawStation(dataJson){
             $("#stationInformation").html("");
            //判断直流桩个数
			var dcPileLen = dataJson.pileInfos.length;
			var name = [];
			for(var k = 0; k < dcPileLen; k++){
		      //直流桩名字
		      name.push(dataJson.pileInfos[k].groupName) ;
		    }
		    //数组去重
		    var arrUnique =  arrUnique(name);
		    console.info(arrUnique);
		     //数组去重
		    function arrUnique(name){
				  name.sort(); //先排序
				  var res = [name[0]];
				  for(var i = 0; i < name.length; i++){
				  if(name[i] !== res[res.length - 1]){
				   res.push(name[i]);
				  }
				 }
				 return res;
			};  
		    //数组长度
			var arrLen = arrUnique.length;
		    for(var i = 0; i < arrLen; i++){
			       var newName = arrUnique[i];
	              //有多少个直流桩
	         	  $("#stationInformation").append("<div class=\"col-md-4 mcol_4\" id = \"allId_" + i + "\">"+
				  "<h4 class=\"mcol_h4\">" + newName + "</h4>"+
				  "<a id = \"append_" + i + "\"></a>"+"</div>");
			      var all = "";
			     // groupLen = dataJson.pileGroups[i].piles.length;
			      for(var j = 0; j < dcPileLen; j++){
				        //指定站 A1
					    var content = dataJson.pileInfos[i].pileName;
					    //运行状态
					    var operatingStatus = dataJson.pileInfos[i].runSta;
					    //车辆SOC
					    var vehicleSOC = dataJson.pileInfos[i].soc;
				        var par = "";
				        //I标签中添加的样式
			            var parI = "";
				        //B标签中车辆SOC添加的样式
				        var rateI = "";
				        var parDiv = "";
				        if(newName.indexOf(dataJson.pileInfos[j].groupName) != -1){
					    switch (operatingStatus){ 
							//故障
							case "0001" :
							   operatingStatus = "故障";
							   parDiv = "<div class=\"progress mprogress mdc_pile_4 pile\" style = \"cursor:pointer\" id = " + dataJson.pileInfos[i].id + ">";
							   par = "<div class=\"progress-bar mprogress-bar  mdc_pile4\">";
							   rateI = "<b>车辆SOC：<i>--</i></b>";
							break; 
							//待机
							case "0002" : 
								operatingStatus = "待机";
							   parDiv = "<div class=\"progress mprogress pile\" style = \"cursor:pointer\" id = " + dataJson.pileInfos[i].id + ">";
							   par = "<div class=\"progress-bar mprogress-bar  mdc_pile1\">";
							   parI = "<i class=\"mdc_pile5\">";
							   rateI = "<b>车辆SOC：<i>--</i></b>";
							break; 
							//工作
							case "0003" :
								operatingStatus = "工作";
							   parDiv = "<div class=\"progress mprogress pile\" style = \"cursor:pointer\" id = " + dataJson.pileInfos[i].id + ">";
							   par = "<div class=\"progress-bar mprogress-bar  mdc_pile2\" style=\"width:"+vehicleSOC+"%\">";
							   rateI = "<b>车辆SOC：<i>"+vehicleSOC+"%</i></b>";
							break; 
							 //离线
							case "0004" : 
								operatingStatus = "离线";
							   parDiv = "<div class=\"progress mprogress mdc_pile_3 pile\" style = \"cursor:pointer\" id = " + dataJson.pileInfos[i].id + ">";
							   par = "<div class=\"progress-bar mprogress-bar  mdc_pile3\">";
							   rateI = "<b>车辆SOC：<i>--</i></b>";
							break; 
							//充满
							case "0005" : 
								operatingStatus = "充满";
								   parDiv = "<div class=\"progress mprogress pile\" style = \"cursor:pointer\" id = " + dataJson.pileInfos[i].id + ">";
								   par = "<div class=\"progress-bar mprogress-bar  mdc_pile1\">";
								   parI = "<i class=\"mdc_pile5\">";
								   rateI = "<b>车辆SOC：<i>--</i></b>";
								break; 
							default : 
							break; 
						} 
				    //拼接一个直流桩下的所有组
			           all +=  parDiv + par +
							"<div class=\"mabsolute1\" id = \"pile2_" + j + "\">"+
							"<input type = \"hidden\" value = \"" + newName + "\">"+
								"<b>" + content + "</b>"+
								"<b>运行状态："+ parI +"" + operatingStatus + "</i></b>"+ rateI +
								"<i class=\"fa fa-dot-circle-o\"></i>"+
							"</div>	"+
						""+
						"</div>"+
					"</div>";
		     //清空
		     $("#append_"+i).html("");
		     //拼接一个桩下的所有站
		     $("#append_"+i).append(all);
				        }
				     }
			}
			//点击事件，跳转站页面
			$(".pile").click(function(){
				//桩ID
				var pileid = $(this).attr("id");
			    //直流桩名字 	A组直流桩
				var pileName = $(this).find("input").val();
				//直流桩下的指定站 A1
				var content = $(this).find("b:eq(0)").text();
			    var map = {};
			    // 转码
				map["name"] = encodeURI(encodeURI(pileName));//赋值
				map["content"] = content;//赋值
				map["pileid"] = pileid;//赋值
				map["stationCode"] = '${stationCode}';//赋值
				map["mechanismCode"] = '${mechanismCode}';//赋值
				//转换json传
				map = JSON.stringify(map);
				//跳转到子页面
	            window.location.href="<%=basePath%>monitoring/pile/initPilePage?map="+map;
			});


}
</script>
</body>
</html>
