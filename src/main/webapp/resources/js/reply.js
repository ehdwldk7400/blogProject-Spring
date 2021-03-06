$(document).ready(function(){
	
	let bno = $("#bno").val();
	let page = 1;
	let username = $("#username").val();
//	console.log(bno);
	
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
		let bno = $("#bno").val();
		let div = $(this).parent();	
		let rno = div.attr("data-rno");
		 let replyname = $("span[data-name="+rno+"]").html();
		 if(username.indexOf(replyname) != 0 && username.indexOf("관리자") != 0){
		    	alert("삭제 권한이 없습니다.");
		 }else{
			let rno = $("#rno").val();
			 $.ajax({
				 type : 'delete',
				 url : '/blog/reply/'+rno,
				 headers : {"Content-Type":"application/json","X-HTTP-Method-Overried":"DELETE"},
				 dateType : 'text',
				 data : JSON.stringify({ rno: rno}),
				 
				 success: function(data){
					 getAllList(bno, page);
				 },
				 error: function(data){
					 alert("댓글 삭제에 실패했습니다."+ data);
				 }
				 
			 });
		 }

//		console.log(rno);	
		
	});
	
	// 댓글 리스트 중 수정 링크 클릭 시 입력폼으로 변경 이벤트
	$(document).on("click", ".reply-update", function(){
			let bno = $("#bno").val();
			let div = $(this).parent();	
			let rno = div.attr("data-rno");
		    let replytext = $("span[data-rno="+rno+"]").html();
		    let replyname = $("span[data-name="+rno+"]").html();
			
		    if(username.indexOf(replyname) != 0 && username.indexOf("관리자") != 0){
		    	alert("수정 권한이 없습니다.");
		    }else{
		    	let str = "";
		    	
		    	str += "	 <div class='reply-contents'>"
		    		+"      	 <input type='hidden' id='bno' name='bno' value='"+ bno+"'>"
		    		+"         	 <input type='hidden' id='replyer' name='replyer' value='${login.username }'>"
		    		+"   		 <input type='hidden' id='rno' value='"+rno+"'> "
		    		+"              <div class='content-text'>"
		    		+"                  <input tpye='text' name='replytext'id='replytext' class='replycontent' value='"+replytext+"'>"
		    		+"                  <div class='submit-btn' data-rno="+rno+">"
		    		+"                      <button type='button'id='update-btn' class='reply-btn'>수정</button>"
		    		+"                 </div>"
		    		+"              </div>"
		    		+"              <div class='checkbox'>"
		    		+"                  <label for='reY' class='ck-radio'>"
		    		+"                      <input type='radio' id='reY' name='open' value='Y' data-gender="+rno+" checked>"
		    		+"                      <span>공개</span>"
		    		+"                  </label>"
		    		+"                  <label for='reN' class='ck-radio'>"
		    		+"                      <input type='radio' id='reN' name='open' value='N'data-gender="+rno+">"
		    		+"                      <span>비공개</span>"
		    		+"                  </label>"
		    		+"              </div>"
		    		+"     </div>";
		    	
		    	
		    	
		    	
		    	$("li[data-rno="+rno+"]").html(str);
		    	
		    }		    
			
	});
	
	
	// 수정버튼 클릭 시 이벤트
	$(document).on("click", "#update-btn", function(){
		let bno = $("#bno").val();
		let div = $(this).parent();	
		let rno = div.attr("data-rno");
		let replytext =  $("#replytext").val();
		let open = $('input[data-gender="'+ rno +'"]:checked').val();
			
		$.ajax({
			type : "PUT",
			url : '/blog/reply/'+rno,
			headers : {"Content-Type":"application/json","X-HTTP-Method-Overried":"PUT"},
			dateType : 'text',
			data : JSON.stringify({ 
				rno: rno,
				replytext : replytext,
				open : open
			}),
			
			success: function(data){
				getAllList(bno, page);
			},
			error: function(data){
				alert("댓글 수정에 실패했습니다."+ data);
			}
			
		});
		
	});
	
	// NEXT 버튼 클릭시 이벤트
	$(document).on("click", ".next", function(e){
		e.preventDefault();
		let targetPageNum = $(this).attr('href');
		
		page += 1;
		
		getAllList(bno, page);
	});
	
	// PREV 버튼 클릭 시 이벤트
	$(document).on("click", ".prev", function(e){
		e.preventDefault();
		let targetPageNum = $(this).attr('href');
		
		page -= 1;
		
		getAllList(bno, page);
	});
	
	
	
	// 답글달기 클릭 시 이벤트
	$(document).on("click", ".btn-init", function(){
		
		let bno = $("#bno").val();
		let div = $(this).parent();	
		let rnogroup = div.attr("data-rno");
//	    let replytext = $("span[data-rno="+rno+"]").html();
	    
	 
		
	    let str = "";
		
		str += "	 <div class='reply-contents'>"
			+"   		 <input type='hidden' id='rnogroup' value='"+rnogroup+"'> "
			+"              <div class='content-text'>"
			+"                  <textarea name='replytext'id='replytext' class='replycontent' placeholder='답글을 남겨주세요.'> </textarea>"
			+"                  <div class='submit-btn' data-rno="+rnogroup+">"
			+"                      <button type='button'id='replygroup-btn' class='reply-btn'>답글쓰기</button>"
			+"                 </div>"
			+"              </div>"
			+"              <div class='checkbox'>"
			+"                  <label for='regY' class='ck-radio'>"
			+"                      <input type='radio' id='regY' name='regopen' value='Y' data-gender="+rnogroup+" checked>"
			+"                      <span>공개</span>"
			+"                  </label>"
			+"                  <label for='regN' class='ck-radio'>"
			+"                      <input type='radio' id='regN' name='regopen' value='N' data-gender="+rnogroup+">"
			+"                      <span>비공개</span>"
			+"                  </label>"
			+"              </div>"
			+"     </div>";
		
		$("li[data-rno="+rnogroup+"]").append(str);
		
	});
	
	// 답글쓰기 버튼 클릭 시 이벤트
	$(document).on("click", "#replygroup-btn", function(){
		
		var rnogroup = $("#rnogroup").val();
		var replyer = $("#replyer").val();
		var replytext = $("#replytext").val();
		let open = $('input[data-gender="'+ rnogroup +'"]:checked').val();
		
		
		console.log(rnogroup);
		console.log(replyer);
		console.log(replytext);
		console.log(open);

		
		$.ajax({
			type : "POST",
			url : '/blog/reply/'+rnogroup,
			headers : {"Content-Type":"application/json","X-HTTP-Method-Overried":"POST"},
			dateType : 'text',
			data : JSON.stringify({ 
				bno : bno,
				rnogroup: rnogroup,
				replyer : replyer,
				replytext : replytext,
				open : open
			}),
			
			success: function(data){
				getAllList(bno, page);
			},
			error: function(data){
				alert("댓글 수정에 실패했습니다."+ data);
			}
		});
	});
	
	
	
	// 대댓글 수정 링크 클릭 시 이벤트
	$(document).on("click", ".Comment-update", function(){
		let bno = $("#bno").val();
		let div = $(this).parent();	
		let rno = div.attr("data-rno");
	    let replytext = $("span[data-rno="+rno+"]").html();
	    let replyname = $("span[data-name="+rno+"]").html();
	    
	    console.log(rno);
	    console.log(replytext);
		
	    if(username.indexOf(replyname) != 0 && username.indexOf("관리자") != 0){
	    	alert("수정 권한이 없습니다.");
	    }else{
	    	let str = "";
	    	
	    	str += "	 <div class='reply-contents' data-list="+rno+">"
	    		+"      	 <input type='hidden' id='bno' name='bno' value='"+ bno+"'>"
	    		+"         	 <input type='hidden' id='replyer' name='replyer' value='${login.username }'>"
	    		+"   		 <input type='hidden' id='rno' value='"+rno+"'> "
	    		+"              <div class='content-text'>"
	    		+"                  <input tpye='text' name='replytext'id='replytext' class='replycontent' value='"+replytext+"'>"
	    		+"                  <div class='submit-btn' data-rno="+rno+">"
	    		+"                      <button type='button'id='Comment-btn' class='reply-btn'>수정</button>"
	    		+"                 </div>"
	    		+"              </div>"
	    		+"              <div class='checkbox'>"
	    		+"                  <label for='reY' class='ck-radio'>"
	    		+"                      <input type='radio' id='reY' name='open' value='Y' data-gender="+rno+" checked>"
	    		+"                      <span>공개</span>"
	    		+"                  </label>"
	    		+"                  <label for='reN' class='ck-radio'>"
	    		+"                      <input type='radio' id='reN' name='open' value='N'data-gender="+rno+">"
	    		+"                      <span>비공개</span>"
	    		+"                  </label>"
	    		+"              </div>"
	    		+"     </div>";
	    	
	    	$("div[data-list="+rno+"]").html(str);
	    	
	    }		    
		
	});
	
	// 대댓글 수정 하는 쿼리
	$(document).on("click", "#Comment-btn", function(){
		
		let bno = $("#bno").val();
		let div = $(this).parent();	
		let rno = div.attr("data-rno");
		let replytext =  $("#replytext").val();
		let open = $('input[data-gender="'+ rno +'"]:checked').val();
			
		$.ajax({
			type : "PUT",
			url : '/blog/reply/'+rno,
			headers : {"Content-Type":"application/json","X-HTTP-Method-Overried":"PUT"},
			dateType : 'text',
			data : JSON.stringify({ 
				rno: rno,
				replytext : replytext,
				open : open
			}),
			
			success: function(data){
				getAllList(bno, page);
			},
			error: function(data){
				alert("댓글 수정에 실패했습니다."+ data);
			}
			
		});
		
	});
	
	// 대댓글 삭제
	$(document).on("click", ".Comment-delete", function(){
		let bno = $("#bno").val();
		let div = $(this).parent();	
		let rno = div.attr("data-rno");
		 let replyname = $("span[data-name="+rno+"]").html();
		 console.log(rno);
		 
		 if(username.indexOf(replyname) != 0 && username.indexOf("관리자") != 0){
		    	alert("삭제 권한이 없습니다.");
		 }else{
		
			 $.ajax({
				 type : 'DELETE',
				 url : '/blog/reply/comment/'+rno,
				 headers : {"Content-Type":"application/json","X-HTTP-Method-Overried":"DELETE"},
				 dateType : 'text',
				 data : JSON.stringify({ rno: rno}),
				 
				 success: function(data){
					 getAllList(bno, page);
				 },
				 error: function(data){
					 alert("댓글 삭제에 실패했습니다."+ data);
				 }
				 
			 });
		 }

//		console.log(rno);	
		
	});
	
});

function getAllList(bno, page){
	let username = $("#username").val();
	
	$.getJSON("/blog/reply/"+bno+"/"+page, function(data){
//		console.log("reply.js");
//		console.log("data ", data);
		let lilist = "";
		console.log("data ", data);
		

//		if(data.list[0].bundel_order != 0){
//			
//			lilist += "<li class='reply-content' data-rno="+ data.list[0].rnogroup +"></li>";
//		
//			$(".reply-list").html(lilist);
//					  
//		}

		
		
		let str = "";
		let bundel = "";

		let date = new Date();
		
		console.log("getAllList data : ",data);
		console.log("getAllList data.list : ",data.list);
		
		$(data.list).each(function(){
				date = getFormatDate(this.regdate);
//				console.log(this.bundel_order);
				if(this.bundel_order == 0 && this.rno == this.rnogroup){
					
					str += " <li class='reply-content' data-rno="+ this.rno +">"
						+"   <input type='hidden' id='rno' value='"+this.rno+"'> "
						+"         <div class='cng-container'>"
						+"             <div class='reply'>"
						+"					<div class='name-time'>"
						+"                 		<span class='blog-icon'></span>"
						+"                 		<span class='reply-name' data-name="+ this.rno +">"+this.replyer+"</span>"
						+"                 		<time class='reply-time' datatime='"+ date +"'>"+ date +"</time>"
						+"					</div>"
						+"					<div class='delete-update'data-rno="+ this.rno +">"
						+"						<a href='#none' class='reply-update link'>수정 </a>"
						+"						<a href='#none' class='reply-delete link'>삭제 </a>"
						+"					</div>"
									
						+"             </div>"
						+"             <div class='cng-content'>"
						+"                 <p class='speech'>";
			
											if(this.open == "N" && (username.indexOf(this.replyer) != 0 && username.indexOf("관리자") != 0)){
						str +="                     <span class='text-reply' data-rno="+ this.rno +"> 비밀글 입니다."
						+"                     </span>";													
									
												
											}else{
						str +="                     <span class='text-reply' data-rno="+ this.rno +">"+ this.replytext
						+"                     </span>"	;						
											}
						str +="                         <span data-rno="+this.rnogroup+">"
						+"                             <button class='btn-init'>↘답글달기</button>"
						+"                         </span>"
						+"                </p>"
						+"            </div>"
						+"        	</div>"
						+"    </li>";	
//						console.log(str);
				}
			
					
					$(".reply-list").html(str);
		});
		$(data.list).each(function(){
			date = getFormatDate(this.regdate);
			let replyname = $("span[data-name="+this.rnogroup+"]").html();
//			console.log(replyname);
			if(this.bundel_order != 0){
				bundel = "<div class='cng-container replygroup' data-list="+ this.rno+">"
				+"             <div class='reply'>"
				+"					<div class='name-time'>"
				+"                 		<span class='blog-icon'></span>"
				+"                 		<span class='reply-name' data-name="+ this.rno +">"+this.replyer+"</span>"
				+"                 		<time class='reply-time' datatime='"+ date +"'>"+ date +"</time>"
				+"					</div>"
				+"					<div class='delete-update'data-rno="+ this.rno +">"
				+"						<a href='#none' class='Comment-update link'>수정 </a>"
				+"						<a href='#none' class='Comment-delete link'>삭제 </a>"
				+"					</div>"
				+"             </div>"
				+"             <div class='cng-content'>"
				+"                 <p class='speech'>";
			
								if(this.open == "N" && (username.indexOf(this.replyer) != 0 && username.indexOf("관리자") != 0 && username.indexOf(replyname) != 0)){
		 bundel +="                     <span class='text-reply' data-rno="+ this.rno +"> 비밀글 입니다."
				+"                     </span>";													
								
											
										}else{
		bundel +="                     <span class='text-reply' data-rno="+ this.rno +">"+ this.replytext
				+"                     </span>"	;						
										}               
		bundel	+"                </p>"
				+"            </div>"
				+"        	</div>";
//				console.log("rno : ", this.rno);
				
			
				$("li[data-rno="+this.rnogroup+"]").append(bundel);
			}
		});
//		console.log(bundel);
//		console.log("댓글 수 : ", data.replycnt);
		
		let endNum = Math.ceil(page/10.0)*10;
		let startNum = endNum-9;
//		console.log("endNum : ", endNum);
//		console.log("page : ", page);
		
		let prev = startNum > 1;
		let next = false;
		
//		console.log("endNum*10 : ", endNum*10);
//		console.log("data.replycnt : ", data.replycnt);
		if(endNum*10 < data.replycnt){
			next = true;
		}

		if(endNum*10 > data.replycnt){
			endNum = Math.ceil(data.replycnt/10.0);
		}	
//		console.log("endNum : ", endNum);
		
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
//		console.log("next : ",next);
//		console.log("endNum*10 : ", endNum*10);
		
		
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



