$(document).ready(function() {
	  $('#summernote').summernote({
			placeholder: '글작성해주세요 ㅎ',
	        minHeight: 800,
	        maxHeight: null,
	        focus: true, 
	        lang : 'ko-KR',
	        callbacks:{
	        	onImageUpload : function(files, editor, welEditable){
	        		console.log(files);
	        		console.log(editor);
	        		console.log(welEditable);
//	        		uploadImageFile(files[0], this);
	        		for(let i = 0; i <= files.length -1; i++ ){
	        			uploadImageFile(files[i], this);
	        		}
	        	}
	        }
	  });
	  
//	  $(document).on('drop','.note-editable',function(e){
//		  console.log(e);
//		  for(i=0; i< e.originalEvent.dataTransfer.files.length; i++){
//			  uploadSummernoteImageFile(e.originalEvent.dataTransfer.files[i],$("#summernote")[0]);
//		  }
//		  e.preventDefault();
//	  });
	  $('.note-editable').css('font-size','14px');
	  
	  
	  function uploadImageFile(file, editor){
		  let data = new FormData();
		  data.append("file", file);
		  
		  console.log(file);
		 
		  console.log(data);
		  $.ajax({
			  type : "POST", 
			  data : data,
			  url : "/blog/upload",
			  dataType : 'json',
			  cache: false,
			  contentType : false,
			  processData : false,
			  success : function(data){
				  console.log(data);
				  $(editor).summernote('insertImage', data.url);
			  },
			  error : function(data){
				  console.log("error: ", data);
			  }
		  });
	  }
	  
	
});