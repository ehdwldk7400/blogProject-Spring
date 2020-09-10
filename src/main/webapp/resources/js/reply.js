$(document).ready(function(){
	
	let bno = $("#bno").val();
	let page = 1;
//	console.log(bno);
//	console.log();
	
	getAllList(bno, page);
	getRepltCnt(bno);
	
	// 댓글창 테두리 변경
	$("#replycontent").on("focus", function(){
			$(".content-text").css("border", "2px solid #5d93ff");

			
	});
	// 댓글창 테두리 변경
	$("#replycontent").on("blur", function(){
		$(".content-text").css("border", "2px solid #e1e4e5");
	});
	
	

	// 댓글 삭제
	$(document).on("click", ".reply-delete", function(){
		let rno = $("#rno").val();
//		console.log(rno);	
		$.ajax({
			type : 'delete',
			url : '/blog/reply/'+rno,
			headers : {"Content-Type":"application/json","X-HTTP-Method-Overried":"DELETE"},
			dateType : 'text',
			data : JSON.stringify({ rno: rno}),
			
			success: function(data){
				getAllList(bno);
			},
			error: function(data){
				alert("댓글 삭제에 실패했습니다."+ data);
			}
			
		});
		
	});
	
	$(document).on("click", ".reply-update", function(){
			let bno = $("#bno").val();
			let div = $(this).parent();	
			let rno = div.attr("data-rno");
		    let replytext = $("span[data-rno="+rno+"]").html();
			
		    let str = "";
			
			str += "	 <div class='reply-contents'>"
				+"      	 <input type='hidden' id='bno' name='bno' value='"+ bno+"'>"
				+"         	 <input type='hidden' id='replyer' name='replyer' value='test'>"
				+"   		 <input type='hidden' id='rno' value='"+rno+"'> "
				+"              <div class='content-text'>"
				+"                  <input tpye='text' name='replytext'id='replytext' class='replycontent' value='"+replytext+"'>"
				+"                  <div class='submit-btn' data-rno="+rno+">"
				+"                      <button type='button'id='update-btn' class='reply-btn'>수정</button>"
				+"                 </div>"
				+"              </div>"
				+"              <div class='checkbox'>"
				+"                  <label for='reY' class='ck-radio'>"
				+"                      <input type='radio' id='reY' name='open' value='Y' checked>"
				+"                      <span>공개</span>"
				+"                  </label>"
				+"                  <label for='reN' class='ck-radio'>"
				+"                      <input type='radio' id='reN' name='open' value='N'>"
				+"                      <span>비공개</span>"
				+"                  </label>"
				+"              </div>"
				+"     </div>";
			
			
			
			
			$("li[data-rno="+rno+"]").html(str);
			
	});
	
	$(document).on("click", "#update-btn", function(){
		let bno = $("#bno").val();
		let div = $(this).parent();	
		let rno = div.attr("data-rno")
		let replytext =  $("#replytext").val();
		let open = $('input[name="open"]:checked').val();
		

		console.log(bno);
		console.log(rno);

		console.log(replytext);
		console.log(open);
		
		$.ajax({
			type : 'put',
			url : '/blog/reply/'+rno,
			headers : {"Content-Type":"application/json","X-HTTP-Method-Overried":"PUT"},
			dateType : 'text',
			data : JSON.stringify({ 
				rno: rno,
				replytext : replytext,
				open : open
			}),
			
			success: function(data){
				getAllList(bno);
			},
			error: function(data){
				alert("댓글 수정에 실패했습니다."+ data);
			}
			
		});
		
	});
	
	$(document).on("click", ".next", function(e){
		e.preventDefault();
		let targetPageNum = $(this).attr('href');
		
		page += 1;
		
		getAllList(bno, page);
	});
	$(document).on("click", ".prev", function(e){
		e.preventDefault();
		let targetPageNum = $(this).attr('href');
		
		page -= 1;
		
		getAllList(bno, page);
	});
	
});

function getAllList(bno, page){
	
	$.getJSON("/blog/reply/"+bno+"/"+page, function(data){
//		console.log("reply.js");
//		console.log("data ", data);
		
		let str = "";
		let date = new Date();
		
		console.log(data);
		console.log(data.list);
		
		$(data.list).each(function(){
				date = getFormatDate(this.regdate);
			str += " <li class='reply-content' data-rno="+ this.rno +">"
				+"   <input type='hidden' id='rno' value='"+this.rno+"'> "
				+"         <div class='cng-container'>"
				+"             <div class='reply'>"
				+"					<div class='name-time'>"
				+"                 		<span class='blog-icon'></span>"
				+"                 		<span class='reply-name'>"+this.replyer+"</span>"
				+"                 		<time class='reply-time' datatime='"+ date +"'>"+ date +"</time>"
				+"					</div>"
				+"					<div class='delete-update'data-rno="+ this.rno +">"
				+"						<a href='#none' class='reply-update link'>수정 </a>"
				+"						<a href='#none' class='reply-delete link'>삭제 </a>"
				+"						</div>"
				+"             </div>"
				+"             <div class='cng-content'>"
				+"                 <p class='speech'>"
				+"                     <span class='text-reply' data-rno="+ this.rno +">"+ this.replytext
				+"                     </span>"
				+"                         <span>"
				+"                             <button class='btn-init'>↘답글달기</button>"
				+"                         </span>"
				+"                </p>"
				+"            </div>"
				+"        </div>"
				+"    </li>";
		});
		console.log("댓글 수 : ", data.replycnt);
		
		let endNum = Math.ceil(page/10.0)*10;
		let startNum = endNum-9;
		console.log("endNum : ", endNum);
		console.log("page : ", page);
		
		let prev = startNum > 1;
		let next = false;
		
		console.log("endNum*10 : ", endNum*10);
		console.log("data.replycnt : ", data.replycnt);
		if(endNum*10 < data.replycnt){
			next = true;
		}

		if(endNum*10 > data.replycnt){
			endNum = Math.ceil(data.replycnt/10.0);
		}	
		console.log("endNum : ", endNum);
		
		if(1 == page){
			$(".prev").addClass('disabled');
		}else{
			$(".prev").removeClass('disabled');
		}
		if(page == endNum || endNum == 0){
			$(".next").addClass('disabled');
		}else{
			$(".next").removeClass('disabled');
		}
		
		$(".next").attr('href', page);
		$(".prev").attr('href', page);
		console.log("next : ",next);
		console.log("endNum*10 : ", endNum*10);
		
		$(".reply-list").html(str);
	});
}
function getRepltCnt(bno){
	$.getJSON("/blog/reply/ReplyCnt/"+bno, function(data){
		
		let cnt = data;
//		console.log(data);
//		console.log(cnt);
		
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


function replyUpdate(bno, replytext){
	
}
