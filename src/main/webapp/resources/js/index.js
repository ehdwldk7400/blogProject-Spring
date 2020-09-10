
let pageNum = 1;	// 페이지 번호
let amount = 10;	//게시물 표시 겟수
let tagPageNum = 1;

$(document).ready(function () {
	
	AllList();
	
	let tagname ="";
	
	$(".tag-ul li a").on("click", function(){
	console.log($(this).attr("id"));
		tagname = $(this).attr("id");
		
		getTagList(tagname, tagPageNum);
		$(".in-cont").css("width", "100%");
		$(".in-cont").css("overflow-y", "hidden");
		$(".in-cont").css("height", "100%");
		$(".main-text").css("overflow", "visible");
		
		let titletag = $(".title-tag").html();
		
		if(titletag != "All-list"){
			let div = "";
			
			div = "  <div class='prev-btn'>" 
	              +"       <a href='' class='prev'>← PREV</a>" 
	              +" </div>" 
	              +"   <div class='next-btn'>" 
	              +"       <a href='' class='next'>→ NEXT</a>" 
	              +"   </div>" ;
	        
			$(".paging-list").html(div);
		}
	});
	$(".in-cont").on("scroll", function(){
			titletag = $(".title-tag").html();
			
//		console.log($(this).height());
			
			let $maxheight = $(window).height();
			let $ScrollTop = $(this).scrollTop();
			let ScrollHeight = this.scrollHeight;
//		let ScrollHeight = 
			
//			console.log($maxheight);
//			console.log($ScrollTop);
//			console.log(ScrollHeight);
//			
			if(ScrollHeight - $ScrollTop === $maxheight && titletag == "All_list"){
				pageNum = pageNum + 1;
				
				$("#loading").css("display", "block");
				$.getJSON("/blog/post/PagingList?pageNum="+pageNum+"&amount="+amount, function(data){
					
					
					console.log(data);
					
					let str = "";
					let date = new Date();
					if(data[0] != null){
						console.log("loading start");
						$(data).each(function(){
//					console.log(this.regdate);
							date = getFormatDate(this.regdate);
//					console.log(date);
							
							str += "<div class='cont-in-list'> <div class='cont-itme'> <time class='cont-time' datatime='"+ date +"'>"+ date +"</time>"
							+ "<span class='item-span-point'></span>" + "<span class='item-sapn'><a>"+ this.tagname +"</a></span>"
							+ "<span class='item-span-point'></span>" + "<span class='item-span-point'><img src='resources/img/user.png'></span>"
							+ "<span class='viewcont'>"+ this.viewcnt +"</span> </div>"
							+ "<h2 class='title-h2'><a href='/blog/post/read?bno="+ this.bno +"'>"+ this.title +"</a></h2>"
							+ "<p class='title-p'>"+ this.content +"</p>"
							+ "<a href='/blog/read?bno="+ this.bno +"' class='item-readmove'>Read</a>"
							+"</div>";
							
							$(".cont-list").html(str);
							$(".cont-list").show("slow");
						});			
					}else{
						str = "<h1>등록된 게시물이 없습니다.</h1>";
						$(".cont-list").html(str);
					}
					  $("#loading").css("display","none");
					
				});
			}
		});
		
	$(document).on("click", ".next", function(e){
		e.preventDefault();
		let targetPageNum = $(this).attr('href');
		
		tagPageNum += 1;
		
		getTagList(tagname, tagPageNum);
	});
	$(document).on("click", ".prev", function(e){
		e.preventDefault();
		let targetPageNum = $(this).attr('href');
		
		tagPageNum -= 1;
		
		getTagList(tagname, tagPageNum);
	});
	


});

function openModal(modalname) {
    document.get

    console.log(modalname);
    $("#modal").fadeIn(300);
    $("." + modalname).fadeIn(300);
}
function offModal() {
    $("#modal").fadeOut(300);
    $(".modal-con").fadeOut(300);
}


function getFormatDate(req){
		date = new Date(req);
//		console.log(date);
	  	var year = date.getFullYear();              //yyyy
	  	var month = (1 + date.getMonth());          //M
	    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
	    var day = date.getDate();                   //d
	    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
	    return  day + '-' + month + '-' + year;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}


// Tag 별 리스트 표현해주는 함수
function getTagList(tagname, tagPageNum){
	
	$("#loading").css("display","block");
	$.getJSON("/blog/post/"+encodeURIComponent(tagname)+"/"+tagPageNum, function(data){
	
		console.log(data);
		console.log(data.list);
		
		let str = "";
		$(".title-tag").html(tagname);
		let date = new Date();
		if(data.list[0] != null){
			$(data.list).each(function(){
//			console.log(this.regdate);
				date = getFormatDate(this.regdate);
//			console.log(date);
				
				str += "<div class='cont-in-list'> <div class='cont-itme'> <time class='cont-time' datatime='"+ date +"'>"+ date +"</time>"
				+ "<span class='item-span-point'></span>" + "<span class='item-sapn'><a>"+ this.tagname +"</a></span>"
				+ "<span class='item-span-point'></span>" + "<span class='item-span-point'><img src='resources/img/user.png'></span>"
				+ "<span class='viewcont'>"+ this.viewcnt +"</span> </div>"
				+ "<h2 class='title-h2'><a href='/blog/post/read?bno="+ this.bno +"'>"+ this.title +"</a></h2>"
				+ "<p class='title-p'>"+ this.content +"</p>"
				+ "<a href='/blog/read?bno="+ this.bno +"' class='item-readmove'>Read</a>"
				+"</div>";
				
				$(".cont-list").html(str);
			});			
		}else{
			str = "<h1>등록된 게시물이 없습니다.</h1>";
			$(".cont-list").html(str);
		}
		let endNum = Math.ceil(data.postcnt/10.0)*10;
		let startNum = endNum-9;
	
		
		let prev = startNum > 1;
		let next = false;
		
		if(endNum*10 > data.tagPageNum){
			endNum = Math.ceil(data.postcnt/10.0);
		}
		console.log("endNum if: ", endNum);
		
		// PREV 버튼 활성화 / 비활성화
		if(1 == tagPageNum){
			$(".prev").addClass('disabled');
		}else{
			$(".prev").removeClass('disabled');
		}
		
		// NEXT 버튼 활성화 / 비활성화
		if(tagPageNum*10 == endNum || endNum == 0){
			$(".next").addClass('disabled');
		}else{
			$(".next").removeClass('disabled');
		}
		
		$(".next").attr('href', tagPageNum);
		$(".prev").attr('href', tagPageNum);
		
		$("#loading").css("display","none");
		
	});
}


// 태그 리스트 페이징


// 전체 게시물 리스트 표현해주는 함수
let AllList = function(){
	let str = "";
	let date = new Date();
	
	
	$("#loading").css("display","block");
	$.getJSON("/blog/post/PagingList?pageNum="+pageNum+"&amount="+amount, function(data){
		console.log(data);
		
		$(data).each(function(){
//			console.log(this.regdate);
			date = getFormatDate(this.regdate);
//			console.log(date);
			
		    str += "<div class='cont-in-list'> <div class='cont-itme'> <time class='cont-time' datatime='"+ date +"'>"+ date +"</time>"
		    + "<span class='item-span-point'></span>" + "<span class='item-sapn'><a>"+ this.tagname +"</a></span>"
		    + "<span class='item-span-point'></span>" + "<span class='item-span-point'><img src='resources/img/user.png'></span>"
		    + "<span class='viewcont'>"+ this.viewcnt +"</span> </div>"
		    + "<h2 class='title-h2'><a href='/blog/post/read?bno="+ this.bno +"'>"+ this.title +"</a></h2>"
		    + "<p class='title-p'>"+ this.content +"</p>"
		    + "<a href='/blog/read?bno="+ this.bno +"' class='item-readmove'>Read</a>"
		    +"</div>";
			
		    $(".cont-list").html(str);
		    $("#loading").css("display","none");
		});
	});
}