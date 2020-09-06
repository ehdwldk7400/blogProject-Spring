$(document).ready(function () {

	// 아이디 입력 시 발생하는 이벤트
    $("#userid").on("keyup blur", function () {

        let userid = $('#userid').val();

        console.log(userid);
        console.log(userid.length);
        
        // 이메일 형식 검사
        if (!CheckEmail(userid)) {
        		$("#email-text").text("올바르지 않은 이메일 형식입니다.");
        		$("#email-text").addClass('invalid');
        		 $("#userid").addClass('invalid');
        		 $(".email-btn").removeAttr('disabled', 'disabled');
        		 if (userid != null && CheckEmail(userid) ) {
        			 $(".msg").removeClass('invalid');
        			 $("#userid").removeClass('invalid');
        		 }
        		 if (userid == "") {
        			 $(".email-btn").attr('disabled', 'disabled');
        			 $("#email-text").addClass('invalid');
        			 $("#email-text").text("이메일 주소를 입력해주세요.");
        			 $("#userid").addClass('invalid');
        		 }
        }else{
        	$.ajax({
        		type : 'post',
        		url : '/blog/idchk',
        		dataType : "json",
        		data :{
        			userid : userid
        		},
        		success : function(data){
        			console.log(userid);
        			console.log(data);
        			if(data == 1){
        				 $("#email-text").text("사용중인 이메일 입니다.");
        				 $("#email-text").addClass('invalid');
        				 $("#userid").addClass('invalid');
        				 $(".email-btn").attr('disabled', 'disabled');
        			}else{
        				 $(".msg").removeClass('invalid');
            			 $("#userid").removeClass('invalid');
            			 $("#email-text").text("사용가능한 이메일입니다.");
            			 $("#email-text").addClass('success-text');
            			 $(".email-btn").removeAttr('disabled', 'disabled');
        			}
        		}
        	});
        	
        }
        
    });
    
    
    // 비밀번호 유효성 검사
    $("#userpw").on("keyup blur", function(){
    	 var pw = $("#userpw").val();

 
    	 var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
    	 
    	 console.log(pw);
    	 console.log(reg.test(pw));
    		if(false === reg.test(pw)) {
    			 $(".pw-test").addClass('invalid');
        		 $(".pw-test").text("8~16자 영문 대소문자,숫자,특수문자 중 3가지 이상 조합해 주세요.");
        		 $(".pwd-strength-meter").css("display", "");
        		 $(".pwd-strength-meter").attr('data-meter', '0');
        		 $(".pwd-strength-meter p").text("사용불가");
        		 $("#userpw").addClass('invalid');
        		 
    		}else{
    			 $(".pwd-strength-meter").attr('data-meter', '3');
        		 $(".pwd-strength-meter p").text("안전");
        		 $(".pw-test").text("8 ~ 16자 영문 대소문자, 숫자, 특수문자를 조합해 주세요.");
        		 $(".pw-test").removeClass('invalid');
        		 $("#userpw").removeClass('invalid');
        		 $("#pw-confirm").removeAttr('disabled', 'disabled');
    		}


    });
    
    // 비밀번호 확인란 입력 시 발생하는 이벤트
    $("#pw-confirm").on("keyup blur", function(){
    	let pw = $("#userpw").val();
    	let confirm = $("#pw-confirm").val();
    	
    	if(pw != confirm){
    		$("#pw-confirm").addClass('invalid');
    		$("#confirm").addClass('invalid');
    		$("#confirm").text("비밀번호가 일치하지 않습니다.");    
    		$("#pw-icon").css("display", "none");
    		$("#username").attr('disabled', 'disabled');
    	}else{
    		$("#pw-confirm").removeClass('invalid');
    		$("#confirm").removeClass('invalid');
    		$("#confirm").text("비밀번호를 한번 더 입력해 주세요.");   
    		$("#pw-icon").css("display", "");
    		$("#username").removeAttr('disabled', 'disabled');
    	}
    });
    
    // 이름 입력 시 발생하는 이벤트
    $("#username").on("keyup blur", function(){	
    	let username = $("#username").val();
    	if(username.length > 2){
    		$("#name-icon").css("display", "");
    		$("#men").removeAttr('disabled', 'disabled');
    		$("#girl").removeAttr('disabled', 'disabled');
    		$("#submit").removeAttr('disabled', 'disabled');
    	}else{
    		$("#men").attr('disabled', 'disabled');
    		$("#girl").attr('disabled', 'disabled');
    		$("#submit").attr('disabled', 'disabled');
    	}
    });
    
    
    // 인증번호 입력 시 발생하는 이벤트
    $("#userNumer").on("keyup blur", function () {

        let userid = $('#userNumer').val();

        console.log(userid);
        if (userNumer != null) {
            $(".emailNumber-btn").removeAttr('disabled', 'disabled');
            $(".emailNumber-btn").removeClass('invalid');
        }
        if (userid == "") {
            $(".emailNumber-btn").attr('disabled', 'disabled');
            $("#userNumer").addClass('invalid');
        }
    });
    
//   이메일 인증 버튼 클릭 시 발생하는 이벤트
    $("#btn-primary, .btn-link").on("click", function(){
    	let userid =  $("#userid").val();
    	let random = $("#random").val();
    	
    	console.log(userid);
    	console.log(random);
    	$.ajax({
    		type: 'get',
    		url : '/blog/mail/creareEmailCheck',
    		data : {
    			userid : userid,
    			random : random
    		},
    		success: function(data){
    			openModal();
    			$(".number").css("display", "");
    			$("#btn-primary").css("display", "none");
    			$("#userid").attr('disabled', 'disabled');
    			$(".btn-reset").css("display", "");
    		
    			
    		},
    		error:function(data){
    			console.log(data);
    			alert("에러가 발생했습니다.");
    		}
    		
    	});
    });
    
    
    // 인증 메일 확인 버튼 클릭시 이벤트
    $("#emailNumber-btn").on("click", function(){
    	let number = $("#userNumer").val();
    	let random = $("#random").val();
    	
    	console.log(number);
    	console.log(random);
    	
    	$.ajax({
    		type : 'get',
    		url : '/blog/mail/emailAuth',
    		data : {
    			authCode : number,
    			random : random
    		},
    		success : function(data){
    			$(".number").css("display", "none");
    			$(".btn-reset").css("display", "none");
    			$("#id-icon").css("display", "");
    			$("#userpw").removeAttr('disabled', 'disabled');

    		},
    		error:function(data){
    			alert("에러가 발생했습니다.");
    		}
    	});
    	
    });
    
    // userid 리셋
    $(".btn-reset button").on("click", function(){
    	$("#userid").removeAttr('disabled', 'disabled');
		$(".btn-reset").css("display", "none");
		$("#btn-primary").css("display", "");
		$("#userid").val('');
		$(".number").css("display", "none");
		$("#userpw").attr('disabled', 'disabled');
		
    });


    // 이메일이 잘못되었는지 확인하는 함수 
    function CheckEmail(str) {

        var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

        if (!reg_email.test(str)) {
            return false;
        }
        else {
            return true;
        }
    }





});

//인증메일 발송 클릭 시 모달창
function openModal() {
    document.get
    $(".modal-container").fadeIn(300);
}
function offModal() {
    $(".modal-container").fadeOut(300);
}
