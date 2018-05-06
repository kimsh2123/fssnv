<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
.fileDrop {
	width: 100%;
	height: 200px;
	border: 1px dotted blue;
}

small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
</head>
<body>

	<h3>Ajax File Upload</h3>
	<div class='fileDrop'></div>

	<div class='uploadedList'></div>

	<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	
	<script>
	
		$(".fileDrop").on("dragenter dragover", function(event) {
			event.preventDefault();
		});
		
		/*
		// 1.4 Ajax 방식의 파일 업로드
		$(".fileDrop").on("drop", function(event) {
			
			event.preventDefault();

			// 1.4.2 drop 이벤트 처리, 전달된 파일 데이터 가져옴
			var files = event.originalEvent.dataTransfer.files;

			var file = files[0];

			console.log(file);
			
			// 1.4.3 HTML5에서 지원하는 방식
			var formData = new FormData();

			formData.append("file", file);

			$.ajax({
				url : '/uploadAjax',
				data : formData,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data) {
					alert(data);
				}
			});

		});
		*/
		
		// 4.2 JSP에서 파일 출력하기
		$(".fileDrop").on("drop",function(event) {
			event.preventDefault();

			//dataTransfer.files 이 전달된 파일 데이터를 가져오는 부분
			var files = event.originalEvent.dataTransfer.files;

			var file = files[0];

			var formData = new FormData();

			// FormDate를 이용한 서버 호출, IE10 이상 가능
			formData.append("file", file);

			$.ajax({
				url : '/uploadAjax',
				data : formData,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data) {

					var str = "";

					// 이미지 파일일 경우 체크
					if (checkImageType(data)) {
						str = "<div><a href=displayFile?fileName="
							+ getImageLink(data)
							+ ">"
							+ "<img src='displayFile?fileName="
							+ data
							+ "'/>"
							+ "</a><small data-src="+data+">X</small></div>";
					} else {
						str = "<div><a href='displayFile?fileName="
							+ data
							+ "'>"
							+ getOriginalName(data)
							+ "</a>"
							+ "<small data-src="+data+">X</small></div></div>";
					}

					$(".uploadedList").append(str);
				}
			});
		});
		
		// 5.2 jsp에서 첨부파일 삭제
		$(".uploadedList").on("click", "small", function(event) {

			var that = $(this);

			$.ajax({
				url : "deleteFile",
				type : "post",
				data : {fileName : $(this).attr("data-src")},
				dataType : "text",
				// 정상적으로 삭제 완료 메세지 받으면 화면에서 삭제
				success : function(result) {
					if (result == 'deleted') {
						that.parent("div").remove();
					}
				}
			});
		});

		/* 		
		 $(".fileDrop").on("drop", function(event) {
		 event.preventDefault();
		
		 var files = event.originalEvent.dataTransfer.files;
		
		 var file = files[0];

		 //console.log(file);
		 var formData = new FormData();
		
		 formData.append("file", file);

		
		 $.ajax({
		 url: '/uploadAjax',
		 data: formData,
		 dataType:'text',
		 processData: false,
		 contentType: false,
		 type: 'POST',
		 success: function(data){
		
		 var str ="";
		
		 console.log(data);
		 console.log(checkImageType(data));
		
		 if(checkImageType(data)){
		 str ="<div><a href='displayFile?fileName="+getImageLink(data)+"'>"
		 +"<img src='displayFile?fileName="+data+"'/></a>"
		 +data +"</div>";
		 }else{
		 str = "<div><a href='displayFile?fileName="+data+"'>" 
		 + getOriginalName(data)+"</a></div>";
		 }
		
		 $(".uploadedList").append(str);
		 }
		 });			
		 });	 

		 */

		// 이미지 파일 원본 찾기
		function getImageLink(fileName) {

			if (!checkImageType(fileName)) {
				return;
			}
			// '/년/월/일' 경로 추출
			var front = fileName.substr(0, 12);
			// "s_" 제거
			var end = fileName.substr(14);

			return front + end;
		}

		// 파일이름 줄여주는 기능, UUID의 _를 제거
		function getOriginalName(fileName) {

			if (checkImageType(fileName)) {
				return;
			}

			var idx = fileName.indexOf("_") + 1;
			return fileName.substr(idx);
		}

		// image 파일인지 구분, i는 대소문자 구분없음을 의미
		function checkImageType(fileName) {

			var pattern = /jpg|gif|png|jpeg/i;

			return fileName.match(pattern);

		}
	</script>

</body>
</html>