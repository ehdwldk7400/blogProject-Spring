<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!-- include summernote css/js-->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
<script src="../resources/js/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="../resources/css/Default.css">
<link rel="stylesheet" href="../resources/css/update.css">
<title>글 작성</title>
<script>
$(document).ready(function() {
	  $('#summernote').summernote({
 	    	placeholder: 'content',
	        minHeight: 370,
	        maxHeight: null,
	        focus: true, 
	        lang : 'ko-KR'
	  });
	  
	  //let content = encodeURIComponent('${read.content}');
	  let content = '${read.content}';
	  console.log(content);
	  $(".content").summernote('code', '${read.content}');
	});
</script>
</head>
<body>
   <div class="main-group">
        <form action="/blog/post/postUpdate" method="POST"  autocomplete="off">
        		<input type="hidden" name="bno" value="${read.bno }">
            <div class="headline">
                <input type="text" class="title" name="title" placeholder="제목" value="${read.title}">
                <!--  -->
             	<input type="text" class="tagname" name="tagname" value="${read.tagname}" readonly style='font-size:2rem'>
                <input type="submit" value="작성" class="submit">
            </div>
            <textarea name="content" id="summernote" class="content"></textarea>
        </form>

    </div>

</body>
</html>