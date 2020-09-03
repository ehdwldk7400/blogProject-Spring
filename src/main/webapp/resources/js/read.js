/**
 * 
 */

$(document).ready(function(){
	
	let formObj = $("form[role='form']");
	
	$(".update").on("click",function(){
		formObj.attr("action","/blog/post/postUpdate");
		formObj.attr('method','get');
		formObj.submit();
	});
	$(".btn-danger").on("click",function(){
		formObj.attr("action","/jin/board/delete");
		formObj.attr('method','post');
		formObj.submit();
	});
});