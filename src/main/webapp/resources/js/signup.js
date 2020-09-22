$(document).ready(function() {
	let formObj = $("form[role='form']");
	let google_url = $("#googleurl").val()
	let naver_url = $("#naverurl").val()
	
	$("#glogin").on("click", function(){	
		formObj.attr("action", google_url);
		formObj.attr('method','POST');
		formObj.submit();
	});
	
	$("#nlogin").on("click", function(){	
		formObj.attr("action", naver_url);
		formObj.attr('method','POST');
		formObj.submit();
	});
});