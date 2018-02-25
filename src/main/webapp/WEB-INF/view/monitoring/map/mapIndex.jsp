<%@ page contentType="text/html;charset=UTF-8"%> 
<%@ include file="../../taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String urlGloble = (String)request.getAttribute("urlGloble");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
	
} 
.mbtn{
    width: 35px;
    height: 35px;
    display: block;
    line-height: 30px;
    text-align: center;
    font-size: 40px;
    background:rgba(255,255,255,0.1) !important;
    border: 1px solid #fff;
    color: #fff;
    border-radius: 3px;
}
.mtop p{
	font-size:12px
}
</style>
<link href="<%=basePath%>css/font-awesome.css" rel="stylesheet"/>	
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=4GWtHUzEOteUSFkRpGcr4XiIre3XMYM7"></script>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/plugins/jquery/jquery-2.1.3.js"></script>
 
<title>动态标注</title>
</head>
<body>
	<div id="allmap"></div>
</body>
<script type="text/javascript">
 
	var map = new BMap.Map("allmap"); // 创建Map实例
	    
	
	map.centerAndZoom("${city}", 13);  // 用城市名设置地图中心点
	map.enableScrollWheelZoom(); //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom(); //启用地图惯性拖拽，默认禁用
	var type="0"; 
	var num=1000;
	var zoom=15;//缩放级别
	//------------------------------------添加地图样式-------------------------------------------
	map.setMapStyle({
  styleJson: 

[
          {
                    "featureType": "land",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#212121"
                    }
          },
          {
                    "featureType": "building",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#2b2b2b"
                    }
          },
          {
                    "featureType": "highway",
                    "elementType": "all",
                    "stylers": {
                              "lightness": -42,
                              "saturation": -91
                    }
          },
          {
                    "featureType": "arterial",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -77,
                              "saturation": -94
                    }
          },
          {
                    "featureType": "green",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#1b1b1b"
                    }
          },
          {
                    "featureType": "water",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#181818"
                    }
          },
          {
                    "featureType": "subway",
                    "elementType": "geometry.stroke",
                    "stylers": {
                              "color": "#181818"
                    }
          },
          {
                    "featureType": "railway",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -52
                    }
          },
          {
                    "featureType": "all",
                    "elementType": "labels.text.stroke",
                    "stylers": {
                              "color": "#313131"
                    }
          },
          {
                    "featureType": "all",
                    "elementType": "labels.text.fill",
                    "stylers": {
                              "color": "#8b8787"
                    }
          },
          {
                    "featureType": "manmade",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#1b1b1b"
                    }
          },
          {
                    "featureType": "local",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -75,
                              "saturation": -91
                    }
          },
          {
                    "featureType": "subway",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -65
                    }
          },
          {
                    "featureType": "railway",
                    "elementType": "all",
                    "stylers": {
                              "lightness": -40
                    }
          },
          {
                    "featureType": "boundary",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#8b8787",
                              "weight": "1",
                              "lightness": -29
                    }
          }
]

});
	  //----------------------------------放大按钮----------------------------------------------
	// 定义一个控件类,即function
	function UpZoomControl(){
	  // 默认停靠位置和偏移量
	  this.defaultAnchor = BMAP_ANCHOR_BOTTOM_LEFT ;
	  this.defaultOffset = new BMap.Size(20, 105);
	}

	// 通过JavaScript的prototype属性继承于BMap.Control
	UpZoomControl.prototype = new BMap.Control();

	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
	UpZoomControl.prototype.initialize = function(map){
	  // 创建一个DOM元素
	  var div = document.createElement("div");
	   
	  div.innerHTML='<span class="mbtn">+</span>';
	   
	  // 设置样式
	  div.style.cursor = "pointer";
	  // 绑定事件
	  div.onclick = function(e){
		map.setZoom(map.getZoom()+1);
	  };
	  // 添加DOM元素到地图中
	  map.getContainer().appendChild(div);
	  // 将DOM元素返回
	  return div;
	}
	// 创建控件
	var myUpZoomCtrl = new UpZoomControl();
	    //----------------------------------放大按钮结束----------------------------------------------
	      //----------------------------------缩小按钮开始----------------------------------------------
	      // 定义一个控件类,即function
	function DownZoomControl(){
	  // 默认停靠位置和偏移量
	  this.defaultAnchor = BMAP_ANCHOR_BOTTOM_LEFT ;
	  this.defaultOffset = new BMap.Size(20, 60);
	}

	// 通过JavaScript的prototype属性继承于BMap.Control
	DownZoomControl.prototype = new BMap.Control();

	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
	DownZoomControl.prototype.initialize = function(map){
	  // 创建一个DOM元素
	  var div = document.createElement("div");
	   
	  div.innerHTML='<span class="mbtn">-</span>';
	   
	  // 设置样式
	  div.style.cursor = "pointer";
	  // 绑定事件
	  div.onclick = function(e){
		map.setZoom(map.getZoom()-1);
	  };
	  // 添加DOM元素到地图中
	  map.getContainer().appendChild(div);
	  // 将DOM元素返回
	  return div;
	}
	// 创建控件
	var myDownZoomCtrl = new DownZoomControl();
	//-----------------------------------------------------------------------------------------------
	  // 将自定义组件添加到地图当中
	map.addControl(myUpZoomCtrl);
	map.addControl(myDownZoomCtrl);
  //----------------------------------------------------------------------------------------------------
  
	//切换城市
	function pickCity(name) {
	 map.centerAndZoom(name, 13);  // 用城市名设置地图中心点
	} 
 

	 
  
	var arr = new Array();//坐标
	var arrName = new Array();//站点名称 
	var arrCode = new Array(); //站点code
	var arrLocalCode = new Array(); //城市code
	var arrAlarmState = new Array(); //告警状态
	
	var arrAllNum= new Array();;// 全部桩数量
	var arrNormalNum= new Array();;//正常桩数量
	var arrFaultNum= new Array();;// 桩故障数量
	var arrOffNum= new Array();;//桩 离线数量
	
	
	/**
	*加载数据
	*/
	function loaderData(result) {  
	 
				for ( var i = 0; i < result.length; i++) {
					 
					if (typeof (result[i]) != "undefined") {
				 
					for ( var j = 0; j < result[i].stationList.length; j++) {
					
						if (typeof (result[i].stationList[j].lat) != "undefined") {
					 
								if (typeof (result[i].stationList[j].lng) != "undefined") {
								
							 
							arr.push(result[i].stationList[j].lng+","+result[i].stationList[j].lat  );
							arrName.push(result[i].stationList[j].local);
							arrCode.push(result[i].stationList[j].code);
							
							 
							arrLocalCode.push(result[i].code);
							arrAlarmState.push(result[i].stationList[j].alarmState);
							  
							arrAllNum.push(result[i].stationList[j].freeNum);   
							arrNormalNum.push(result[i].stationList[j].chargeNum);   
							arrFaultNum.push(result[i].stationList[j].maintainNum);   
							arrOffNum.push(result[i].stationList[j].offNum);   
							 
							
							}
						}
						
						}
						
						
						
					}
				}
			 
		//加载地图上的点
		showDot(type);

	}
//增点点的点击事件
	function addPoint(i,marker) { 
 		map.addOverlay(marker);
 		
 		  
		var name = arrName[i]; 
		var code = arrCode[i]; 
		var localCode = arrLocalCode[i]; 
 
		var allNum= arrAllNum[i];
		var normalNum= arrNormalNum[i];
		var faultNum= arrFaultNum[i];
		var offNum= arrOffNum[i];
		 
		var sContent = '<div class="mtop" style="min-width:100px;margin-top:7px;">' 
				+ '<div  style="width:100%;text-overflow:ellipsis;font-zise:16px;text-align:center;;margin:5px auto">'
				+ '<div>'
				+ name
				+ '</div>'
				+ '</div>'
				+ '<div style="clear:both;">'
				+ '<p style="max-width:200px;text-overflow:ellipsis;;margin:5px o">桩编号：'
				+ code
				+ '</p>'
				+ '<p style="width:50%;float:left;margin:5px auto">空闲:'+allNum+'</p>'
				+ '<p style="width:50%;float:left;margin:5px auto">充电:'+normalNum+'</p>'
				+ '<p style="width:50%;float:left;margin:5px auto">维护:'+faultNum+'</p>'
				+ '<p style="width:50%;float:left;margin:5px auto">离线:'+offNum+'</p>' 
				+ '</div>'
				+ '<div style="border-top:1px dashed #EBE1E0; text-align:center;width:100%;float:left;margin-top:20px">' 
				+ '<button style="background:red;border:none;border-radius:6px;color:white;width:96px;height:30px;margin-top:15px;cursor: pointer" onclick=detial("'
				+ code + '","'+localCode+'")>查看详情</button>' + '</div>' + '</div>';
		addClickHandler(marker, sContent); 
	}
	
	var m = new Map();
	//新增点
	function addOrdinaryPoint(i) {
	
		var url="";
	if(arrAlarmState[i]=="0"){
	url="${pageContext.request.contextPath}/images/pile_green.png"
	}else if(arrAlarmState[i]=="1"){
		url="${pageContext.request.contextPath}/images/pile_yellow.png"
	}else if(arrAlarmState[i]=="2"){
		url="${pageContext.request.contextPath}/images/pile_red.png"
	}
	
	var mark=  m.get(arrCode[i]);
	 
	if(mark==null){
	 createMark(url,i); 
	 mark=  m.get(arrCode[i]);
	} 
	  var  myIcon = new BMap.Icon(url, new BMap.Size(44, 50)); 
		 mark.setIcon(myIcon);//改变颜色 
	 	 addPoint(i,mark); //更改mark的点击事件 
	 	 
		 if(type=="2"){
			 if(arrAlarmState[i]=="2"){
			 mark.show();
			 }else{
			  mark.hide();
			 } 
		 }else if(type=="1"){
		  if(arrAlarmState[i]=="1"){
			 mark.show();
			 }else{
			  mark.hide();
			 }  
		 }else{
			 mark.show(); 
		}  
	  
	}
	
	/**
	*创建点
	*/
	function createMark(url,i){
	 
		 var  myIcon = new BMap.Icon(url, new BMap.Size(44, 50));
		     
			var marker = new BMap.Marker(new BMap.Point(arr[i].split(",")[0], arr[i].split(",")[1]), {
				icon : myIcon
			});
		  
		  addPoint(i,marker); 
		m.set(arrCode[i], marker);  
	}
 
	 
	
	//加载地图上的点
	function showDot(showType) { 
		type=showType;
	
		for ( var i = 0; i < arr.length; i++) {
			if (arr[i] != "") {
					addOrdinaryPoint(i);
			};
		};
	}

	//弹出框中点击查看详情触发事件
	/**ID为站code，local为城市code
	*/
	function detial(id,localCode) {
	
	 
	
		window.parent.window.tz(id,localCode);
	}
	
	//弹出框事件
	function addClickHandler(marker, sContent) {

		marker.addEventListener("click", function(e) {

			var p = e.target;
			var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
			var infoWindow = new BMap.InfoWindow(sContent); // 创建信息窗口对象 
			map.openInfoWindow(infoWindow, point); //开启信息窗口
		});

	}
 
	  
 
 	 
	
	
 
</script>
</html>
