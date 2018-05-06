<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/dfg_include.jsp"%>

<html>
<head>
	<title>Daily Base File Download</title>
</head>
<body>

<style>
 table {table-layout: fixed;}
</style>

<!-- 내용 작성 할 부분 START  -->
<form role="form" method="post">
<div id="page-inner">
	<div class="panel panel-default">
   	<div class="col-lg-12">
    <h1 class="page-head-line">Daily Base File Download</h1>
    </div>

	<div class="container">
	<div class="row">
		<div class="col-lg-12">
		작성 기준일자 : <input type="text" maxlength="8" name="make_date" id="make_date" value="${make_date}">
          
        <button type="button" class="btn btn-primary" id='searchBtn' name='search'>SEARCH</button>
        <button type="button" class="btn btn-info" id='filedownBtn'>File Download</button>
		</div>
	</div>
	
<c:if test="${zip_file == 'N' ? false : true}">
	<div class="panel-body">
	<div class="table-responsive">
		<table class="table table-striped table-bordered table-hover">
			<thead>
			    <tr>
			   		<th style="width:30%;text-align:center">Zip 파일 Download</th>
			   		<td align="center">
			   			<a href="/fss/fileupdw/dailybasefiledown?file_name=${zip_file}">${zip_file}</a>
			   		</td>
			    </tr>
			</thead>
		</table>
	</div>
	</div>
</c:if>
	
	<div class="row">
		<div class="col-lg-8">
		<!--   Classes  -->
                   
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
					    <tr class="info">
					   		<th style="width:25%;text-align:center">#자료번호</th>
							<th style="width:55%;text-align:center">자료명</th>
							<th style="width:20%;text-align:center">자료건수</th>
					    </tr>
					</thead>
				
				<tbody>
				<c:forEach items="${menulist}" var="MenuVO">
					<tr>
						<td align="center">${fn:toUpperCase(MenuVO.menu_id)}</td>
						<td>${MenuVO.menu_name}</td>
						<td align="right"><c:out value="${hash_cnt[MenuVO.menu_id]}"/></td>
						
						<input type="hidden" name="menu_id" value="${MenuVO.menu_id}">
					</tr>         
				</c:forEach>
				</tbody>
				
				</table>
			</div>
		</div>
   		<!--  end  Classes  -->
		</div>
	</div>
	</div>

	</div>
</div>
</form>
<!-- 내용 작성 할 부분 END  -->

<script>
$(document).ready(function() {
	
	var formObj = $("form[role='form']");
	
	$("#make_date").on('keydown', function(e){
		if(e.keyCode == 13){
			$("#searchBtn").click();
		}
	});
	
	$("#searchBtn").on("click", function() {
		formObj.attr("action", "/fss/fileupdw/dailyBaseDown");
		formObj.attr("method", "get");
		formObj.submit();
	});

	$('#filedownBtn').on("click", function() {
		formObj.attr("action", "/fss/fileupdw/dailyBaseDown");
		formObj.attr("method", "post");
		formObj.submit();
	});

});
</script>

<script>
var result = '${msg}';
if (result == 'SUCCESS') {
	alert("처리가 완료되었습니다.");
}
</script>

</body>
</html>

