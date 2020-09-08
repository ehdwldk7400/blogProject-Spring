/**
 * 
 */

$(document).ready(function(){
	
	let bno = $("#bno").val();
	console.log(bno);
	console.log();
	
	getAllList(bno);
	getRepltCnt(bno);
	
	$("#replycontent").on("focus", function(){
			$(".content-text").css("border", "2px solid #5d93ff");

			
	});
	$("#replycontent").on("blur", function(){
		$(".content-text").css("border", "2px solid #e1e4e5");
	});
	
});

function getAllList(bno){
	
	$.getJSON("/blog/reply/ReplyList/"+bno, function(data){
		console.log("reply.js");
		console.log("data ", data);
		
		let str = "";
		let date = new Date();
		
		$(data).each(function(){
				date = getFormatDate(this.regdate);
			str += " <li class='reply-content'>"
				+"         <div class='cng-container'>"
				+"             <div class='reply'>"
				+"					<div class='name-time'>"
				+"                 		<span class='blog-icon'></span>"
				+"                 		<span class='reply-name'>"+this.replyer+"</span>"
				+"                 		<time class='reply-time' datatime='"+ date +"'>"+ date +"</time>"
				+"					</div>"
				+"					<div class='delete-update'>"
				+"						<a href='#' class='link'>수정</a>"
				+"						<a href='#' class='link'>삭제</a>"
				+"						</div>"
				+"             </div>"
				+"             <div class='cng-content'>"
				+"                 <p class='speech'>"
				+"                     <span>"+ this.replytext
				+"                         <span>"
				+"                             <button class='btn-init'>↘답글달기</button>"
				+"                         </span>"
				+"                     </span>"
				+"                </p>"
				+"            </div>"
				+"        </div>"
				+"    </li>";
			$(".reply-list").html(str);
		});
		
	});
}
function getRepltCnt(bno){
	$.getJSON("/blog/reply/ReplyCnt/"+bno, function(data){
		
		let cnt = data;
		console.log(data);
		console.log(cnt);
		
		$(".reply-number").text(cnt);
	});
}

function getFormatDate(req){
	date = new Date(req);
//	console.log(date);
  	var year = date.getFullYear();              //yyyy
  	var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  day + '-' + month + '-' + year;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}
