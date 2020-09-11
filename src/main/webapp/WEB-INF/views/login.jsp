<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>

<link rel="stylesheet" href="resources/css/Default.css">
<link rel="stylesheet" href="resources/css/login.css">
</head>
<body class="pc">
    <div class="warpper" id="container">
        <div class="contents">
            <div class="inner-box">
                <header class="header">
                    <h1><a href="/blog">Sign In</a></h1>
                </header>
                <section class="login-wrap">
                    <form action="/blog/loginPost" method="POST" autocomplete="off">
                        <div class="input-email">
                            <input type="text" id="userid" name="userid" placeholder="이메일을 입력해주세요.">
                        </div>
                        <div class="input-passowrd">
                            <input type="password" id="userpw" name="userpw" placeholder="비밀번호">
                        </div>
                        <div class="iogin-util"></div>
                        <div class="btn-wrap">
                            <input type="submit" value="로그인">
                        </div>
                        <div class="account-info">
                            <ul class="account-info-list">
                                <li>
                                    <a href="#">계정찾기</a>
                                </li>
                                <li>
                                    <a href="#">비밀번호찾기</a>
                                </li>
                                <li>
                                    <a href="/blog/join">회원가입</a>
                                </li>
                            </ul>
                        </div>
                    </form>
                    <div class="login-3ard-party">
                        <p>
                            <button id="glogin" class="google-login-btn">
                                <span>
                                    Google로 로그인
                                </span>
                            </button>
                        </p>
                        <p>
                            <button id="nlogin" class="naver-login-btn">
                                <span>
                                    Naver로 로그인
                                </span>
                            </button>
                        </p>
                        <p>
                            <button id="klogin" class="kakao-login-btn">
                                <span>
                                    카카오톡으로 로그인
                                </span>
                            </button>
                        </p>
                    </div>
                </section>
            </div>
        </div>
    </div>
    <div class="footer">
        <p>Thank you all for coming.</p>
    </div>

</body>
</html>