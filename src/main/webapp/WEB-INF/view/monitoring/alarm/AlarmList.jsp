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
	href="<%=basePath%>js/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
	rel="stylesheet" />
<link href="<%=basePath%>js/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="<%=basePath%>css/animate.min.css" rel="stylesheet" />
<link href="<%=basePath%>css/style.min.css" rel="stylesheet" />
<link href="<%=basePath%>css/style-responsive.min.css" rel="stylesheet" />
<link href="<%=basePath%>css/theme/default.css" rel="stylesheet"
	id="theme" />
<link href="<%=basePath%>css/font-awesome.css" rel="stylesheet" />
<!-- ================== END BASE CSS STYLE ================== -->

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link
	href="<%=basePath%>js/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.css"
	rel="stylesheet" />
<link
	href="<%=basePath%>js/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link
	href="<%=basePath%>js/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />
<link href="<%=basePath%>js/plugins/gritter/css/jquery.gritter.css"
	rel="stylesheet" />
<link href="<%=basePath%>css/datatable/jquery.dataTables.css"
	rel="stylesheet" />

<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- ================== BEGIN BASE JS ================== -->
<script src="<%=basePath%>js/plugins/pace/pace.min.js"></script>
<script src="<%=basePath%>js/new/tipso.min.css"></script>
<!-- ================== END BASE JS ================== -->
</head>
<body>
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
		<div class="content_mtop">
			<div class="col-md-12">
				<div class="pull-left">
					<div class="right_top_mcion">
						<div style="position: relative;">
							<span id="left1" class="label mlabel_color1 mlabel"
								style="cursor:pointer;left:10px"><input type="hidden"
								value="1" />故障<span id="faultNumber">0</span></span> <span id="left2"
								class="label mlabel_color2 mlabel" style="cursor:pointer;"><input
								type="hidden" value="2" />派发<span id="payoutNumber">0</span></span> <span
								id="left3" class="label mlabel_color3 mlabel"
								style="cursor:pointer;"><input type="hidden" value="3" />处理<span
								id="disposeNumber">0</span></span> <span id="left4"
								class="label mlabel_color4 mlabel" style="cursor:pointer;"><input
								type="hidden" value="4" />办结<span id="concludeNumber">0</span></span>

							<input id="left5" type="text" class="minput"
								placeholder="输入站点名称、电桩编号" /> <span id="left6"
								style="cursor: pointer" onclick="searchData()"
								class="fa-stack fa-2x text-m4"> <i
								class="fa fa-square fa-stack-2x"></i> <i
								class="glyphicon glyphicon-search toolbar_word fa-stack-1x fa-inverse"></i>
							</span> <span id="left7" style="display:none; cursor:pointer"
								class="fa-stack fa-2x text-m4"> <i
								class="fa fa-square fa-stack-2x"></i> <i
								class="glyphicon glyphicon-search toolbar_word fa-stack-1x fa-inverse"></i>
							</span> <span id="put_away" class="fa-stack fa-2x"> <i
								class="fa fa-square fa-stack-2x"></i> <i
								class="glyphicon glyphicon-menu-left fa-stack-1x fa-inverse"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="pull-right">
					<div class="pull-right mpull-right" style="height:40px">
						<div class="right_top_mcion mbcion"
							style="width:220px;height:60px">
							<div style="position: relative;">
								<span class="label mlabel_color3 mlabel" onclick="partAffirm()"
									style="cursor:pointer;right:105px" data-toggle="tooltip"
									data-placement="bottom" title="确认"><i
									class="fa fa-check"></i></span> <span onclick="allAffirm()"
									class="label mlabel_color3 mlabel"
									style="cursor:pointer;right:55px" data-toggle="tooltip"
									data-placement="bottom" title="全部确认"><i
									class="glyphicon glyphicon-ok"></i></span> <span
									class="label mlabel_color3 mlabel" id="fa-spin"
									style="cursor:pointer;right:155px" data-toggle="tooltip"
									data-placement="bottom" title="停止刷新"><i
									class="fa fa-refresh fa-spin"></i></span> <a
									href="${pageContext.request.contextPath}/monitoring/map/initMapPage"><span
									class="label mlabel_color3 mlabel"
									style="cursor:pointer;right:5px"><i
										class="glyphicon glyphicon-facetime-video"></i></span></a>
							</div>
						</div>
					</div>


					<div class="pull-right mpull-right" style="height:40px">
						<div class="right_top_mcion mbcion"
							style="width:120px;height:60px">
							<div style="position: relative;">

								<span id="Distributed" class="label mlabel_color1 mlabel"
									style="cursor:pointer;right:55px" data-toggle="tooltip"
									data-placement="bottom" title="派发"><i
									class="fa fa-paper-plane"></i></span> <span
									class="label mlabel_color3 mlabel"
									style="cursor:pointer;right:5px" data-toggle="tooltip"
									data-placement="bottom" title="导出"><i
									class="fa fa-upload"></i></span>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="col-md-12 mtable">
				<div class="dataTables_wrapper no-footer">
					<table id="mexample" class="display dataTable" width="100%"
						style="table-layout: fixed;">
						<thead>
							<tr>
								<th style="width:4%"><input type="checkbox" /></th>
								<th style="width:4%">序号</th>
								<th style="width:6%">省份</th>
								<th style="width:6%">地市</th>

								<th style="width:7%">充电站名称</th>
								<th style="width:9%">充电桩编号</th>

								<th style="width:7%">故障类型</th>
								<th style="width:8%">故障开始时间</th>
								<th style="width:8%">故障持续时长</th>
								<th style="width:7%">故障原因</th>

								<th style="width:7%">工单编号</th>
								<th style="width:7%">工单状态</th>
								<th style="width:7%">派发时间</th>
								<th style="width:7%">工单耗时</th>
								<th style="width:6%">是否确认</th>
							</tr>
						</thead>
						<tbody id="mexampleTbody">
						</tbody>

					</table>

					<div class="dataTables_paginate paging_simple_numbers"
						id="example_paginate">
						<a class="paginate_button " onclick="turnPage(-1)"
							aria-controls="example" data-dt-idx="0" tabindex="0"
							id="example_previous">上一页</a> <span id="paging"> </span> <a
							class="paginate_button " onclick="turnPage(-2)"
							aria-controls="example" data-dt-idx="4" tabindex="0"
							id="example_next">下一页</a>
					</div>
				</div>
			</div>
		</div>
		<!-- begin #content -->
		<!-- end #content -->

		<!-- begin theme-panel -->
	</div>
	<!-- end page container -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">派发提示</h4>
				</div>
				<div class="modal-body" id="Distributed_cen">是否派发选中的信息</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="Distributed_btn"
						onclick="assign()">提交派发</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="<%=basePath%>js/plugins/jquery/jquery-2.1.3.js"></script>
	<script
		src="<%=basePath%>js/plugins/jquery/jquery-migrate-1.2.1.min.js"></script>
	<script
		src="<%=basePath%>js/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="<%=basePath%>js/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js" tppabs="http://seantheme.com/color-admin-v1.7/admin/html/assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script
		src="<%=basePath%>js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="<%=basePath%>js/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="<%=basePath%>js/plugins/gritter/js/jquery.gritter.js"></script>
	<script src="<%=basePath%>js/plugins/flot/jquery.flot.min.js"></script>
	<script src="<%=basePath%>js/plugins/flot/jquery.flot.time.min.js"></script>
	<script src="<%=basePath%>js/plugins/flot/jquery.flot.resize.min.js"></script>
	<script src="<%=basePath%>js/plugins/flot/jquery.flot.pie.min.js"></script>
	<script src="<%=basePath%>js/plugins/sparkline/jquery.sparkline.js"></script>

	<script src="<%=basePath%>js/new/tipso.min.js"></script>

	<script
		src="<%=basePath%>js/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="<%=basePath%>js/plugins/jquery-jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="<%=basePath%>js/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="<%=basePath%>js/dashboard.min.js"></script>
	<script src="<%=basePath%>js/apps.min.js"></script>

	<!-- ================== END PAGE LEVEL JS ================== -->

	<script>
		$(document).ready(function() {
			App.init();
			Dashboard.init();
			searchData();
	         $(".pull-left .right_top_mcion .mlabel").click(function(){
	         	$(".right_top_mcion span").removeClass("mclick");
	         	$(this).addClass("mclick"); 
	         	searchData();
	         })
	          $(".mbcion span").click(function(){
	         	$(".mbcion span").removeClass("mclick1");
	         	$(this).addClass("mclick1");
	   
	         })
		});
	</script>
	<script type="text/javascript">
var urlGloble = "<%=urlGloble%>"; 
var basePath = "<%=basePath%>";
        var url = "ws://" + basePath.substring(6, basePath.length) + urlGloble + "?orgId=${jurisdiction}";
	</script>
	<script type="text/javascript"
		src="<%=basePath%>js/websocket/jsUtils.js"></script>
	<!-- websocket -->
	<script type="text/javascript"
		src="<%=basePath%>js/websocket/websocket.js"></script>



	<!-- websocket -->

	<script type="text/javascript">
		var updateTime = "";

        var type = "";//状态
        var name = "";//查询关键字
        var isRealTime = true;//是否实时刷新
        var page = "1";//页数
        var allPage = "1";//总页数

        var pageAllNumber = 0;//一共多少条数据

        var allData = new Array();//所有数据

        function send() {

        }
        /*
         *查找50条数据
         */
        function searchData() {

	        type = $(".mclick input").val();
	        name = $("#left5").val();

	        //获取搜索类型 

	        $.ajax({
	                    cache : false,//每次都会向服务器请求
	                    async : false,//同步
	                    type : 'POST',
	                    dataType : "json",
	                    url : '${pageContext.request.contextPath}/monitoring/alarm/search?page=' + page + '&name=' + name + '&type=' + type + '&isRealTime=' + isRealTime,
	                    success : function(result) {
		                    allData = result.val;

		                    pageAllNumber = result.pageAllNumber;

		                    creactTable(result.val);
		                    refreshTotal(result);
		                    changeColour(result.val);

	                    } });

        }

        /*实时接收数据
         */
        function getMassage(data) {

	        var dataJson = eval('(' + data + ')');

	        if (true) {//判断是否暂停
		        if (isConform(dataJson.val)) {
			        creactTable(allData);
			        changeColour(allData);

		        };

	        }
	        refreshTotal(data);

        }

        /**
         *判断allDate是否更改过
         *dataList
         */
        function isConform(dataList) {
	        var isUpdate = false;

	        for (var i = 0; i < dataList.length; i++) {

		        if (updataAllData(dataList[i])) {

			        //对allData排序如果新数据是最后一条就删除
			        oredrAllData(dataList[i]);

			        isUpdate = true;
		        }
	        }
	        return isUpdate;

        }

        /**
         *对allData排序如果新数据是最后一条就删除
         */
        function oredrAllData(data) {

	        allData.sort(function(a, b) {
	        
	     
	        
		        //   return > 0 b前a后；reutrn < 0 a前b后
 		 
		        if (a.isTop - b.isTop != 0) {

			        return b.isTop - a.isTop;
		        }

		        //按工单状态排序
		        if (a.orderState - b.orderState != 0) {

			        return a.orderState - b.orderState;
		        }
		        
		          //工单持续时间排序
		           var orderEndTimeA=a.orderEndTime||new Date();
		           var orderElapsedTimeA=""
		    	 if(a.orderStartTime==""||a.orderStartTime==null){
		    		 orderElapsedTime=""
		    	 }else{
		    		orderElapsedTime=parseInt((orderEndTimeA-a.orderStartTime)/(1000*60));
		    	 } 
		    	 
		    	        var orderEndTimeB=b.orderEndTime||new Date();
		    	      var orderElapsedTimeB=""
		    	 if(b.orderStartTime==""||b.orderStartTime==null){
		    		 orderElapsedTime=""
		    	 }else{
		    		orderElapsedTime=parseInt((orderEndTimeB-b.orderStartTime)/(1000*60));
		    	 } 
		           
		        if (orderElapsedTimeA - orderElapsedTimeB > 0) {

			        return -1;
		        } else {
			        return 1;
		        }
		        
		        
		        //故障时间排序
		        if (a.faultStateTime - b.faultStateTime > 0) {

			        return -1;
		        } else {
			        return 1;
		        }

	        });

	        if (allData[allData.length - 1].id == data.id) {
		        allData.remove(allData.length - 1);
	        }

        }

        /**
         *判断这1条数据是否还符合条件
         */
        function isMeet(data) {
	        if (type == "1" || type == "2" || type == "3" || type == "4") {
		        if (data.orderState = "") {
			        data.orderState = "1";
		        }

		        if (data.orderState != type) {
			        return false;
		        }
	        }

	        if (name != "") {
		        if (!(data.orderState == name || data.orderState == name)) {
			        return false;
		        }
	        } 
	        if(data.orderState == "1" && data.faultState=="0"){
	        
	            return false;
	        }
	     

	        return true;
        }

        /**
         *判断这1条数据是否对allData进行过更新
         */
        function updataAllData(data) {

	        if (data.isTop != 0) {

		        for (var i = 0; i < allData.length; i++) {
			        if (allData[i].id == data.id) {
				        allData.remove(i);

				        allData.push(data);
				        return true;
			        }else{
			          allData.push(data);
				        return true;
			        }
		        }

	        } else if (isNull(data.id,"") != "") {

		        pageAllNumber += 1;
		        allData.push(data);
		        if (!isMeet(data)) {
			        pageAllNumber -= 1;
			        allData.remove(allData.length - 1);

		        }
		        return true;

	        } else if (isNull(data.orderId,"") != "") {

		        for (var i = 0; i < allData.length; i++) {

			        if (allData[i].orderId == data.orderId) {

				        allData[i].orderState = data.orderState;
				        allData[i].orderEndTime = data.orderEndTime;
				        allData[i].isTop = data.isTop;

				        if (!isMeet(allData[i])) {
					        allData.remove(i);
				        }

				        return true;
			        }
		        }

	        } else {

		        for (var i = 0; i < allData.length; i++) {
		       
		        
		        
			        if (allData[i].pile == data.pile && allData[i].faultMarking == data.faultMarking && allData[i].faultState == "1") {
				        allData[i].faultEndTime = data.faultEndTime;
				        allData[i].faultState = data.faultState;

				        if (!isMeet(allData[i])) {
				        
					        allData.remove(i);
				        }
				        return true;
			        }
		        }
	        }

	        return false;

        }

        /**
         *全部确认（）
         */
        function allAffirm() {

	        $.ajax({ cache : false,//每次都会向服务器请求
	        async : false,//同步
	        type : 'POST', dataType : "json", url : '${pageContext.request.contextPath}/monitoring/alarm/affirm',
	            success : function(result) {

		            if (result.success) {
			            alert("确认成功");
			            searchData();
		            }

	            } });

        }

        /**
         *部分确认（确认勾选的）
         */
        function partAffirm() {
	        var ids = new Array();;//桩 离线数量
	        $("input[name='checkbox']:checked").each(function() {

		        ids.push($(this).val());

	        });

	        $.ajax({ cache : false,//每次都会向服务器请求
	        async : false,//同步
	        type : 'POST', dataType : "json", url : '${pageContext.request.contextPath}/monitoring/alarm/affirm',
	            data : { idsJSON : JSON.stringify(ids) }, dataType : "json",

	            success : function(result) {
		            if (result.success) {
			            alert("确认成功");
		            } else {
			            alert("确认失败");
		            }

	            } });

        }

        function assign() {

	        var list_map = new Array();//桩 离线数量

	        var message = "";

	        $("input[name='checkbox']:checked").each(
	                function() {

		                var trArr = $("tr[id='tr" + $(this).val() + "']");
		                var trArr2 = document.getElementById("tr" + $(this).val());

		                var cells = trArr2.getElementsByTagName("td");

		                if (cells[18].innerHTML == "0") {
			                message += "桩" + cells[5].innerHTML + "故障已恢复\n";

		                } else if (cells[11].innerHTML != "未派单") {
			                message += "桩" + cells[5].innerHTML + "已派发过工单\n";

		                } else {

			                //charge_station_id 桩id
			                //start_date 开始日期
			                //patrol_reason 故障内容
			                //fault_type 故障标识
			                //duration 持续时长 
			                list_map.push({ charge_station_id : cells[5].innerHTML,
			                start_date : cells[15].innerHTML, 
			                patrol_reason : cells[11].innerHTML, 
			                fault_type : cells[17].innerHTML,
			                    duration : cells[9].innerHTML, 
			                    
			                    
			                    
			                    
			                    
			                    
			                    
			                    });

		                }

	                });

	        if (message != "") {
		        alert(message);
	        }

	        if (list_map.length == 0) {

		        $("#myModal").modal("hide");

		        return false;
	        }

	        $.ajax({ cache : false,//每次都会向服务器请求
	        async : false,//同步
	        type : 'POST', 
	        dataType : "json", 
	        url : '${pageContext.request.contextPath}/monitoring/alarm/workOrderPayout',
	        data : { json : JSON.stringify(list_map) },
	            
	            success : function(result) {

		            $("#myModal").modal("hide");
		            searchData()

	            } });

        }

        /**
         *刷新左上脚统计数据和分页按钮
         */
        function refreshTotal(data) {

	        $('#faultNumber').html(data.faultNumber);
	        $('#payoutNumber').html(data.payoutNumber);
	        $('#disposeNumber').html(data.disposeNumber);
	        $('#concludeNumber').html(data.concludeNumber);

	        //分页添加

	        refreshPage(pageAllNumber);

        }

        /**
         *是否实时推送
         */
        function isRealRefresh() {
	        $("#fa-spin i").toggleClass("fa-spin");
	        isRealTime = !isRealTime;//停止实时刷新 
	        if (isRealTime) {
		        page = "1";
		        searchData();
	        }
        }

        window.onload = function() {
        	function ti(){
	        var explorer = navigator.userAgent;
	        //ie 
	        if (explorer.indexOf("MSIE") >= 0) {}
	        //firefox 
	        else if (explorer.indexOf("Firefox") >= 0) {
		        $("#left2").css("left", $("#left1").width() + 45);
		        $("#left3").css("left", $("#left1").width() + 40 + $("#left2").width() + 40);
		        $("#left4").css("left", $("#left1").width() + 35 + $("#left2").width() + 40 + $("#left3").width() + 40);
		        $("#left5").css("margin-left",
		                $("#left1").width() + 40 + $("#left2").width() + 40 + $("#left3").width() + 40 + $("#left4").width() + 40)
	        }
	        //Chrome
	        else if (explorer.indexOf("Chrome") >= 0) {
		        $("#left2").css("left", $("#left1").width() + 45);
		        $("#left3").css("left", $("#left1").width() + 40 + $("#left2").width() + 40);
		        $("#left4").css("left", $("#left1").width() + 35 + $("#left2").width() + 40 + $("#left3").width() + 40);
		        $("#left5").css("margin-left",
		                $("#left1").width() + 40 + $("#left2").width() + 40 + $("#left3").width() + 40 + $("#left4").width() + 40)
	        }
        	}
        	self.setInterval(function(){
       	     ti();
       		},100)
	        $("#fa-spin").click(function() {

		        isRealRefresh();
	        })
        }
        $("#put_away").click(
                function() {
	                $("#left7").show();
	                $("#left7").css("margin-left",
	                        $("#left1").width() + 40 + $("#left2").width() + 40 + $("#left3").width() + 40 + $("#left4").width() + 30)
	                $("#left5").hide(0);
	                $("#left6").hide(0);
	                $("#put_away").hide(500);

                })
        $("#left7").click(function() {
	        $("#left5").show();
	        $("#left6").show(0);
	        $("#put_away").show(500);
	        $("#left7").hide();
        })
        $("#Distributed").click(function() {
	        if ($("input:checked").length == 0) {
		        $("#Distributed_cen").text('请选择要派送的信息');
		        $("#Distributed_btn").hide();
	        } else {
		        $("#Distributed_cen").text('是否派发选中的信息');
		        $("#Distributed_btn").show();
	        }

	        $("#myModal").modal('show');
        })
	</script>

	<script type="text/javascript"
		src="<%=basePath%>js/view/monitoring/alarm/alarmList.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/view/monitoring/alarm/page.js"></script>
</body>
</html>

