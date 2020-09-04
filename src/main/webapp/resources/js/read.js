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
	$(".delete").on("click",function(){
		formObj.attr("action","/blog/post/postDelete");
		formObj.attr('method','post');
		formObj.submit();
	});
});