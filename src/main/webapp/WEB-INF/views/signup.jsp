<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="resources/css/Default.css">
<link rel="stylesheet" href="resources/css/signup.css">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="resources/js/signup.js"></script>
</head>
<body>
 <div class="container">
        <div class="wrap-contents">
            <div class="contents">
                <div class="content-section">
                    <div class="content-header">
                        <h1 class="title">회원가입</h1>
                        <p class="desc">회원가입 수단을 선택해 주세요.</p>
                    </div>
                    <article class="selet-method">
                    <form role="form">
                        <div class="btn-wrap">
                            <a href="/blog/join" class="join btn">
                                <span>이메일로 회원가입</span>
                            </a>
                            <p class="or">또는</p>
                        </div>
                        <!-- google -->
                        <div class="btn-wrap">
                        	<input type="hidden" value="${google_url}" id="googleurl">
                            <button id="glogin" class="google-login-btn btn">
                                <span>
                                    Google로 회원가입
                                </span>
                            </button>
                        </div>
                        <!-- Naver -->
                        <div class="btn-wrap">
                        	<input type="hidden" value="${naver_url}" id="naverurl">	
                            <button id="nlogin" class="naver-login-btn btn">
                                <span>
                                    Naveer로 회원가입
                                </span>
                            </button>
                        </div>
                        <!-- KaKao -->
                        <div class="btn-wrap">
                       	 <input type="hidden" value="${kakaotalk_url}" id="kakaourl">	
                            <button id="klogin" class="kakao-login-btn btn">
                                <span>
                                 	   카카오톡으로 회원가입
                                </span>
                            </button>
                        </div>
                        </form>
                    </article>
                </div>
            </div>
        </div>
    </div>

</body>
</html>