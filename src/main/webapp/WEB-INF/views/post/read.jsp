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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="../resources/js/read.js"></script>

<body>
   <header>
        <a href="/blog">All-List</a>
    </header>
    
    <form action="" role="form" id="fr">
	<input type="hidden" id="bno" name="bno" value="${read.bno }">
    <section class="contents">
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
        <div class="view-body">
            ${read.content}
        </div>
    </section>
    </form>


</body>

</html>