/*
 *翻页
 */
function turnPage(pages) {

	$(".paginate_button ").removeClass("current");

	// 停止实时刷新
	$(".mbcion span").removeClass("mclick1");
	$("#fa-spin").addClass("mclick1");

	$("#fa-spin i").removeClass("fa-spin");
	isRealTime = false;// 停止实时刷新
	if (pages == "-1") {// 上一页
		page = page - 1;

		var table = document.getElementById("page" + page);
		console.log(table);
		if (table = "null") {
			page = page + 1;
		}

	} else if (pages == "-2") {
		page = page + 1;

		var table = document.getElementById("page" + page);
		if (table = "null") {
			page = page - 1;
		}

	} else {
		page = pages;
	}
	$("#page" + page).addClass("current");
	searchData();

}

/**
 * 刷新分页按钮
 */
function refreshPage(pageAllNumber) {

	// 分页添加
	if (pageAllNumber == "undefined" || pageAllNumber == undefined) {

		$.ajax({ cache : false,// 每次都会向服务器请求
		async : false,// 同步
		type : 'POST', dataType : "json",
		    url : basePath + '/monitoring/alarm/getPageAllNumber?page=' + page + '&name=' + name + '&type=' + type,
		    success : function(result) {

			    refreshPageModule(result.pageAllNumber);

		    } });

	} else {
		refreshPageModule(pageAllNumber);

	}

}

/**
 * 刷新分页组件
 */
function refreshPageModule(pageAllNumber) {
	// 分页添加
	allPage = parseInt(pageAllNumber / 50) + 1;
	var pageHtml = "";

	for (var i = 0; i < allPage; i++) {

		pageHtml += "<a id='page" + (i + 1) + "' class='paginate_button '  onclick=turnPage('" + (i + 1) + "') tabindex='0'>" + (i + 1) + "</a>"

	}
	$('#paging').html(pageHtml);
	$("#page" + page).addClass("current");

}
