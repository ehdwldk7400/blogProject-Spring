$(document).ready(function() {
	let formObj = $("form[role='form']");
	let url = $("#googleurl").val()
	console.log(url);
	
	$("#glogin").on("click", function(){
		
		
		formObj.attr("action", url);
		formObj.attr('method','POST');
		formObj.submit();
	});
});