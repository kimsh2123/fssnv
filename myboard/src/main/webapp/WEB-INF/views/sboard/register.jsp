<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<style>
.fileDrop {
  width: 80%;
  height: 100px;
  border: 1px dotted gray;
  background-color: lightslategrey;
  margin: auto;
  
}
</style>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
				<!-- /.box-header -->

<form id='registerForm' role="form" method="post">
	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">Title</label> <input type="text"
				name='title' class="form-control" placeholder="Enter Title">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3"
				placeholder="Enter ..."></textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label>
			<!-- 3.2.1 로그인 사용자 처리 추가  --> 
			<input type="text" name="writer" class="form-control" value='${login.uid }' readonly>
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">File DROP Here</label>
			<div class="fileDrop"></div>
		</div>
	</div>

	<!-- /.box-body -->

	<div class="box-footer">
		<div>
			<hr>
		</div>

		<!-- 업로드 파일 보여질 부분 -->
		<ul class="mailbox-attachments clearfix uploadedList">
		</ul>

		<button type="submit" class="btn btn-primary">Submit</button>

	</div>
</form>

			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script type="text/javascript" src="/resources/js/upload.js"></script>
<!-- handlebars 탬플릿 사용 -->
<script type="text/javascript" src="/resources/js/handlebars-v4.0.5.js"></script>

<script id="template" type="text/x-handlebars-template">
<li>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a href="{{fullName}}" 
     class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
	</span>
  </div>
</li>                
</script>    

<script>

var template = Handlebars.compile($("#template").html());

$(".fileDrop").on("dragenter dragover", function(event){
	event.preventDefault();
});


$(".fileDrop").on("drop", function(event){
	event.preventDefault();
	
	var files = event.originalEvent.dataTransfer.files;
	
	var file = files[0];

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
			  
			var fileInfo = getFileInfo(data); //탬플릿에 필요한 객체를 생성
			  
			var html = template(fileInfo);
			  
			$(".uploadedList").append(html); //첨부된 파일이 보여지는 곳
		}
	});	
});

// 게시물 등록 작업 처리
$("#registerForm").submit(function(event){
	event.preventDefault();
	
	var that = $(this);
	
	// 첨부파일 정보는 hidden으로 추가
	var str ="";
	$(".uploadedList .delbtn").each(function(index){
		str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
	});
	
	that.append(str);
	
	// get(0)은 순수한 DOM객체를 얻어내는데 사용
	that.get(0).submit();
});

</script>
 

<%@include file="../include/footer.jsp"%>
