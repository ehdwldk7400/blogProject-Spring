<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>${read.tagname}</title>
	
	
 <link rel="stylesheet" href="../resources/css/Default.css">
<link rel="stylesheet" href="../resources/css/read.css">
<link rel="stylesheet" href="../resources/css/reply.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="../resources/js/read.js"></script>
<script src="../resources/js/reply.js"></script>

<body>
   <header>
        <a href="/blog">All-List</a>
    </header>
    
    <section class="contents">
   <form action="" role="form" id="fr">
	<input type="hidden" id="bno" name="bno" value="${read.bno }">
        <div class="view-header">
            <h2 class="view-title">${read.title}</h2>
            <div class="header-group">
                <div class="post-info">
                    <span class="tagname">${read.tagname}</span>
                    <span class="date">${read.regdate}</span>
                    <span class="item-span-point"></span>
                    <span class="img"><img src="../resources/img/user.png"></span>
                    <span class="viewcnt">${read.viewcnt}</span>
                </div>
                <div class="action">
                    <ul class="action-ul">
                        <li><a href="#" class="update">수정</a></li>
                        <li><a href="#" class="delete">삭제</a></li>
                    </ul>
                </div>
            </div>
        </div>
         </form>
        <div class="view-body">
            ${read.content}
        </div>
             <div class="reply-container">
                <div class="reply-count">
                    <span class="reply-text">댓글</span>
                    <span class="reply-number"></span>
                </div>
                <div class="reply-contents">
               <form action="/blog/reply/ReplyCreate" method="post">
                	 <input type="hidden" id="bno" name="bno" value="${read.bno}">
                   	 <input type="hidden" id="replyer" name="replyer" value="${login.username }">
                        <div class="content-text">
                            <textarea name="replytext" id="replycontent"class="replycontent" placeholder="댓글을 남겨주세요."></textarea>
                            <div class="submit-btn">
                                <button type="submit" class="reply-btn">댓글남기기</button>
                            </div>
                        </div>
                        <div class="checkbox">
                            <label for="Y" class="ck-radio">
                                <input type="radio" id="Y" name="open" value="Y" checked>
                                <span>공개</span>
                            </label>
                            <label for="N" class="ck-radio">
                                <input type="radio" id="N" name="open" value="N">
                                <span>비공개</span>
                            </label>
                        </div>
      			</form>
                </div>
            </div><!-- reply-container -->
            <div class="replylist-container">
                <ul class="reply-list">
                   
                </ul>
            </div>
             <div class="paging-list">
                <div class="prev-btn">
                    <a href="" class="prev">← PREV</a>
                </div>
                <div class="next-btn">
                    <a href="" class="next">→ NEXT</a>
                </div>
            </div>
    </section>

</body>

</html>