<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../include/dfg_include.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload</title>
</head>
<body>

<!-- 내용 작성 할 부분 START  -->
<div id="page-inner">
    <div class="row">
        <div class="panel panel-default">
        
        <div class="col-md-12">
    	    <h1 class="page-head-line">File Upload ( ${menu_id} )</h1>
        </div>

		<form id='form1' action="fileupload" method="post" enctype="multipart/form-data">
			<input type='file' name='file'> 
			<input type='submit'>
			
			<input type=hidden name='menu_id' value='${menu_id}'>
			<input type=hidden name='up_id' value='${login.user_id}'>
		</form>
            
		</div>
    </div>
</div>
<!-- 내용 작성 할 부분 END  -->       

</body>
</html>