 
/**
 * 时间格式化
 * @param da 时间搓
 * @returns
 */
function format(da) {  
	try{
		if(da==null){
			
		     return "";
		}
		
		
	    da = new Date(da);
	    var year = da.getFullYear()+'年';
	    var month = da.getMonth()+1+'月';
	    var date = da.getDate()+'日';
 
	    var hh = da.getHours()+'时';
	    var mm = da.getMinutes()+'分';
	    var ss = da.getSeconds()+'秒';
 
      return [year,month,date,hh,mm,ss].join('-');
		
		
	}catch(e){
	      return "";
	}
	 

}
/**
 *重新加载表格
 */
function creactTable(data) {

    var table = document.getElementById('mexampleTbody');

    var tbody = "";

    for (var i = 0; i < data.length&&i <50; i++) {
    	
     
    	 var faultEndTime=data[i].faultEndTime||new Date();
    	 var faultTimer=parseInt((faultEndTime-data[i].faultStateTime)/(1000*60));
    	 
    	 
    	 var orderEndTime=data[i].orderEndTime||new Date();
    	 var orderElapsedTime=""
    	 if(data[i].orderStartTime==""||data[i].orderStartTime==null){
    		 orderElapsedTime=""
    	 }else{
    		orderElapsedTime=parseInt((orderEndTime-data[i].orderStartTime)/(1000*60));
    	 } 
    	 
    	
    	var orderStateName="";
    	
    	if ("1"==data[i].orderState) {
    		orderStateName="未派单";
		} else if ("2"==data[i].orderState) {
			orderStateName="未接单"; 
		} else if ("3"==data[i].orderState) {
			orderStateName="处理中";
		} else if ("4"==data[i].orderState) {
			orderStateName="办结";
		}else{
			orderStateName="未派单";
		}
    	 var val= new Array();
    	 $("input[name='checkbox']:checked").each(
	                function() {
	                	val.push($(this).val());	
	                	
	                });
    	
    	var isAffrim=data[i].isAffrim||"否";
    	var orderId=data[i].orderId||""; 
    	
    	
        tbody += "<tr id='tr"+data[i].id+"'>" 
        		+ "<td><input type='checkbox' name='checkbox' value='"+data[i].id+"'/></td>"
                + "<td>"+(i+1)+"</td>" 
                + "<td>"+data[i].provinces+"</td>"
                + "<td>"+data[i].city+"</td>"   
                + "<td>"+data[i].stationName+"</td>"
                
                + "<td>"+data[i].pile+"</td>" 
                + "<td>"+data[i].faultType+"</td>"
                + "<td>"+format(data[i].faultStateTime)+"</td>" 
                + "<td>"+faultTimer+"</td>" 
                + "<td>"+data[i].faultDetails+"</td>"
                
                + "<td>"+orderId+"</td>"
                + "<td>"+orderStateName+"</td>"
                + "<td>"+format(data[i].orderStartTime)+"</td>" 
                + "<td>"+orderElapsedTime+"</td>"
                + "<td>"+isAffrim+"</td>"
                
                + "<td style='display:none; '>"+data[i].faultStateTime+"</td>"
                + "<td style='display:none; '>"+data[i].stationId+"</td>"//充电站ID
                + "<td style='display:none; '>"+data[i].faultMarking+"</td>"//故障标识
                + "<td style='display:none; '>"+data[i].faultState+"</td>"//充电桩状态（故障，恢复）
                + "<td style='display:none; '>"+data[i].isTop+"</td>"//充电桩状态（故障，恢复）
                + "</tr>";
        }
    table.innerHTML = tbody; 
	  var boxes = document.getElementsByName("checkbox");
	   
		    for(i=0;i<boxes.length;i++){
		    	
		        for(j=0;j<val.length;j++){
		            if(boxes[i].value == val[j]){
		                boxes[i].checked = true;
		                break
		            }
		        }
		    }
    
 
 
    $('tbody tr td').each(function(){
		//aa.width($("#example tbody tr td").width());
		
		 var divlength= this.clientWidth;
         
          var textLength = this.scrollWidth;
           //alert(divlength);
          	
        if (textLength > divlength)
         {
         
         	//alert(textLength+"-------------"+divlength);
         	var aa=$(this);
         	$(aa).attr('display', 'block');
         	$(aa).width(divlength);
     	   //alert($(aa).width());
         	$(aa).attr('class', 'tip1');
            
			var sBrowser = $(aa).text();
			$(aa).attr( 'data-tipso', sBrowser);//setAttribute() 方法添加指定的属性，并为其赋指定的值。
			//this.setAttribute( 'title', '');
         }  
        $(function() {	 	
        		$('.tip1').tipso({
        				useTitle: false
        	                 	});
                      });
});}

/**
 *删除指定行
 */
function deleteAssignRow(row) {

    $("tr[id='tr" + row.id + "']").remove();//更具桩id删除数据

    try {
        var table = document.getElementById('mexample');
        table.deleteRow("49"); //从table中删除 最后一行
    } catch (e) {

    }

}


/**
 * 修改指定行数据
 */
function updateAssignRow(row) {
	var tableObj = document.getElementById("mexample");

	$("tr[id='tr" + row.id + "']").html(
			+ "<td><input type='checkbox' name='checkbox' value='"+row.id+"' /></td>"
            + "<td>"+1+"</td>" 
            + "<td>"+row.provinces+"</td>"
            + "<td>"+row.city+"</td>"  
            + "<td>"+row.station+"</td>"
            + "<td>"+row.pile+"</td>" 
            
            + "<td>"+row.faultType+"</td>"
            + "<td>"+row.faultTime+"</td>" 
            + "<td>"+row.faultTimer+"</td>" 
            + "<td>"+row.faultDetails+"</td>"
            
            + "<td>"+row.orderId+"</td>"
            + "<td>"+row.orderState+"</td>"
            + "<td>"+row.orderPayoutTime+"</td>" 
            + "<td>"+row.orderElapsedTime+"</td>" 
            + "<td>"+row.isAffrim+"</td>"
	);

	var tableObj = document.getElementById("mexample");
	// 循环更改序号

	for (var i = 1; i < tableObj.rows.length; i++) {
		tableObj.rows[i].cells[1].innerHTML = i;
	}
  
}

/**
 *颜色改变
 */
function changeColour(data) {

    for (var i = 0; i < data.length; i++) {

        var trArr = $("tr[id='tr" + data[i].id + "']");
//        var orderState = data[i].orderState;
//         
//	   	 var orderEndTime=data[i].orderEndTime||new Date();
//	   	 var orderElapsedTime=""
//	   	 if(data[i].orderStartTime==""||data[i].orderStartTime==null){
//	   		 orderElapsedTime=""
//	   	 }else{
//	   		 orderElapsedTime=parseInt((orderEndTime-data[i].orderStartTime)/(1000*60));
//	   	 } 
// 
//
//        if (orderState == "2" && orderElapsedTime > 15) { //15分钟没接单
//	        trArr.addClass("dadui");
//        } else if (orderState != "4" && orderElapsedTime > 120) { //2小时没处理
//	        trArr.addClass("dadui");
//        } else {
//	        trArr.removeClass("dadui");
//        }
var isTop=data[i].isTop;
        if(isTop==1){
        	
            trArr.addClass("dadui");
        	
        }else if(isTop==2){
            trArr.addClass("dadui");
        } else{
            trArr.removeClass("dadui");
        	
        }
        
    }
}

/**
 *插入指定行
 */
function insertAssignRow(row,isRed) {
      var tableObj = document.getElementById("mexample");

    var newTr = tableObj.insertRow("1");

    newTr.setAttribute("id", "tr"+row.id);

    if (isRed) { 
        newTr.setAttribute("class", "dadui");
    }
      
    newTr.innerHTML= "<td><input type='checkbox' name='checkbox' value='"+row.id+"'/></td>"
                + "<td>"+1+"</td>" 
                + "<td>"+row.provinces+"</td>"
                + "<td>"+row.city+"</td>"  
                + "<td>"+row.station+"</td>"
                + "<td>"+row.pile+"</td>" 
                
                + "<td>"+row.faultType+"</td>"
                + "<td>"+row.faultTime+"</td>" 
                + "<td>"+row.faultTimer+"</td>" 
                + "<td>"+row.faultDetails+"</td>"
                
                + "<td>"+row.orderId+"</td>"
                + "<td>"+row.orderState+"</td>"
                + "<td>"+row.orderPayoutTime+"</td>" 
                + "<td>"+row.orderElapsedTime+"</td>"
                + "<td>"+row.isAffrim+"</td>";
               
    
    //循环更改序号 
	    for(var i=1;i<tableObj.rows.length;i++){ 
	        tableObj.rows[i].cells[1].innerHTML = i;  
	    }  
      
}


/**
 * 判空
 * @param data 数据，defaultData 默认值
 * @returns
 */
function isNull(data,defaultData) {   
	 var returnData=data||"";
	 
	 if(returnData==null){
		 returnData="";
	 }
	 if(returnData=="null"){
		 returnData="";
	 }
	 if(returnData==undefined){
		 returnData="";
	 }
	 if(returnData=="undefined"){
		 returnData="";
	 } 
 
	 
	 if(returnData==""){
		 returnData=defaultData;
	 }
	 
	 
	 return  returnData;
	 
}



