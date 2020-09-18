<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Yang Dongjin Post</title>
<link rel="stylesheet" href="resources/css/Default.css">
<link rel="stylesheet" href="resources/css/index.css">
<link rel="stylesheet" href="resources/css/search.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="resources/js/index.js"></script>

</head>
<body>
	<section class="main-group">
		<header>
			<ul class="header-ul">
			<c:if test="${empty login}">
				<li><a href="/blog/signup"> join</a></li>
				<li><a href="/blog/login"> login</a></li>
			</c:if>
			<c:if test="${!empty login}">
				<li class="user-profile">
					<img src="resources/img/profile.jpg" alt="프로필 사진">

				</li>
				<li class="user-profile">
					<a href="#" >${login.username}님</a>
				</li>
				<li><a href="/blog/logout" class="logout">logout</a></li>
			</c:if>
			</ul>
			<div id="Search-container">
				<input type="search" placeholder="Search" id="search" autocomplete="off" name="keyword">
			</div>
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
						<li><a href="/blog">All_List</a></li>
						<li><a href="#">About me</a></li>
						<c:if test="${login.verify eq 1}">
						<li><a href="/blog/post/postWrite">Write</a></li>
						</c:if>
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
				<c:if test="${login.verify eq 1}">
					<a href="javascript:openModal('modal1');" class="button modal-open">Tag Add</a>
				</c:if>
				<div id="modal" onclick="javascript:offModal();"></div>
				<div class="modal-con modal1">
					<a href="javascript:offModal();" class="close">X</a>
					<p class="modal-title">Tag Title</p>
					<div class="con">
						<span>추가할 태그를 입력하세요.</span> <br>
						<form action="/blog/tag" method="post"  autocomplete="off">
							<input type="text" name="tagname" id="tagname"> 
							<input type="submit" value="추가" id="add">
						</form>
					</div>
				</div>
				<div class="tag">
					<ul class="tag-ul">
					<c:forEach items="${tag}" var="Tag">
						<li>
							<a id="${Tag.tagname}" href="#">${Tag.tagname}</a>
							<input type="hidden" class="${Tag.tagname}" value="${Tag.tagname}">	
						</li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<!-- 콘텐츠 영역 -->
		<div class="main-text">
			<div class="in-cont">
				<div class="title-mouse">
					<h1 class="title-tag">All_list</h1>
					<div class="mouse-wheel">
						<div class="mouse">
							<div class="wheel"></div>
						</div>
						<div><span class="arrow"></span></div>
					</div>
				</div>
						<div class="cont-list">
							<!-- 게시시판 리스트 표시 영역 -->
						</div>
						<div id="loading">
							<img alt="로딩" src="resources/img/loading.gif">
						</div>
						<div class="paging-list">
						
						
						</div>
			</div>
			
			
		</div>
		</main>
		<footer> </footer>
	</section>

</body>
</html>