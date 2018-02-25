/*websoket 连接 改写*/
	
	var horizon = {};
	
	horizon.websocket = null;

	 if('WebSocket' in window){
		 //"ws://"+basePath.substring(6,basePath.length)+urlGloble
		 horizon.websocket = new WebSocket(url);//
     }
     else{
         alert('Not support websocket');
     }

      //连接发生错误的回调方法
	 horizon.websocket.onerror = function(){
        //alert("error");
        // setMessageInnerHTML("error");
      };
      
      //连接成功建立的回调方法
      horizon.websocket.onopen = function(event){
    	  send("");
    	  //horizon.websocket.send("aa");
         // setMessageInnerHTML("open");
      }
       
      //接收到消息的回调方法
      horizon.websocket.onmessage = function(event){
    	  console.info(event.data);
    	  getMassage(event.data);
      }
       
      //连接关闭的回调方法
      horizon.websocket.onclose = function(){
          //setMessageInnerHTML("close");
      }
       
      //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = function(){
    	  horizon.websocket.close();
      }
