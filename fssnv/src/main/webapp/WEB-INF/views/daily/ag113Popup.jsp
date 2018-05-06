<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/dfg_include.jsp"%>

<html>
<head>
<title>POP UP</title>
</head>
<body>
<!-- 내용 작성 할 부분 START  -->
<form role="formpop" method="post">

<div id="page-inner">
    <div class="row">
        <div class="panel panel-default">
            <div class="col-md-12">
            <h1 class="page-head-line">[AG113] Pop up 미완성</h1>
            <br>
            <h1 class="page-head-line">DATE : ${make_date}</h1>
            </div>
		</div>
	</div>
	
	<div class="row">
	<button type="button" class="btn btn-danger" id='popcloseBtn'>POP UP CLOSE</button>
	</div>
</div>

<script>
$(document).ready(function() {
	
	var formObj = $("form[role='formpop']");
	
	$('#popcloseBtn').on("click", function() {
		
		formObj.attr("action", "/fss/daily/ag113List");
		formObj.attr("method", "get");
		formObj.submit();
		
		self.close();
	});
	
});
</script>

</form>
</body>
</html>