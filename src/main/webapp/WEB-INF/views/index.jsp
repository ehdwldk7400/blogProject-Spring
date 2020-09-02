<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yang Dongjin Post</title>
<link rel="stylesheet" href="resources/css/Default.css">
<link rel="stylesheet" href="resources/css/index.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="resources/js/index.js"></script>

</head>
<body>
	<section class="main-group">
		<header>
			<ul class="header-ul">
				<li><a href="/blog/join"> join</a></li>
				<li><a href="/blog/login"> login</a></li>
				<li>${login.username }</li>
			</ul>
		</header>
		<main>
		<div class="pro-tag">
			<div class="in-group">
				<div class="profile">
					<a href="#"><img src="resources/img/profile.jpg" alt="프로필 사진"></a>
					<h1>Yang Dongjin</h1>
				</div>
				<nav class="list">
					<ul>
						<li><a href="#">All_List</a></li>
						<li><a href="#">About me</a></li>
					</ul>
				</nav>
				<div class="img">
					<ul>
						<li><a href="#"><img src="resources/img/github.png"
								alt="깃허브"></a></li>
						<li><a href="#"><img src="resources/img/email.png"
								alt="이메일"></a></li>
						<li><a href="#"><img src="resources/img/kakao-talk.png"
								alt="카카오톡"></a></li>
					</ul>
				</div>
				<div class="tag-list">© All Tag List</div>
				<a href="javascript:openModal('modal1');" class="button modal-open">Tag
					Add</a>
				<div id="modal" onclick="javascript:offModal();"></div>
				<div class="modal-con modal1">
					<a href="javascript:offModal();" class="close">X</a>
					<p class="modal-title">Tag Title</p>
					<div class="con">
						<span>추가할 태그를 입력하세요.</span> <br>
						<form action="/blog/tag" method="post">
							<input type="text" name="tagname" id="tagname"> 
							<input type="submit" value="추가" id="add">
						</form>
					</div>
				</div>
				<div class="tag">
					<ul class="tag-ul">
					</ul>
				</div>
			</div>
		</div>
		<!-- 콘텐츠 영역 -->
		<div class="main-text">
			<div class="in-cont">
				<h1 class="title-tag">nodejs</h1>
				<div class="cont-list"></div>
			</div>
		</div>
		</main>
		<footer> </footer>
	</section>
</body>
</html>