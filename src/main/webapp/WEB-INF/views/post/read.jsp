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

<body>
   <header>
        <a href="/blog">All-List</a>
    </header>
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
                        <li><a href="#">수정</a></li>
                        <li><a href="#">삭제</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="view-body">
            ${read.content}
        </div>
    </section>


</body>

</html>