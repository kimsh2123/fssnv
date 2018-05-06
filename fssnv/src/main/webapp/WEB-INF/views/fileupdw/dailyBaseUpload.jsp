<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/dfg_include.jsp"%>

<html>
<head>
	<title>Daily Base File Upload</title>
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
    <h1 class="page-head-line">Daily Base File Upload</h1>
    </div>
         

	<div class="container">
	<div class="row">
		<div class="col-lg-12">
		작성 기준일자 : <input type="text" maxlength="8" name="make_date" id="make_date" value="${make_date}">
          
        <button type="button" class="btn btn-primary" id='searchBtn' name='search'>SEARCH</button>
        <button type="button" class="btn btn-info" id='fileupBtn'>File Upload</button>
	</div>
	</div>

	<div class="row">
		<div class="col-lg-5">
		<table class="table table-striped table-bordered table-hover" sytle="width: 50%">
           	<thead>
            <tr class="danger">
            	<th style="width:50%;text-align:center">#파일명</th>
                <th style="width:50%;text-align:center">LINE</th>
            </tr>
            </thead>
            <tbody>
				<tr class="success">
					<td align="center"><c:out value="${basefilecnt['filename']}"/></td>
					<td align="center"><c:out value="${basefilecnt['basefilecnt']}"/></td>
					
					<input type=hidden name='filename' value='${basefilecnt['filename']}'>
					<input type=hidden name='user_id' value='${login.user_id}'>
				</tr>
            </tbody>
		</table>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-5">
		<label>UPLOAD 자료 선택</label>
        	<select class="form-control" name="menu_id">
            	<option value="ALL">ALL UPLOAD</option>
            	<c:forEach items="${menulist}" var="MenuVO">
            		<option value='${MenuVO.menu_id}'>${fn:toUpperCase(MenuVO.menu_id)} - ${MenuVO.menu_name}</option>
            	</c:forEach>
            </select>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-8">
		<!--   Classes  -->
        <div class="panel panel-default">
                   
        	<div class="panel-heading">
            	File Upload Count
            </div>
                    
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
						</tr>         
            		</c:forEach>
					</tbody>
					
					</table>
				</div>
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
		formObj.attr("action", "/fss/fileupdw/dailyBaseUpload");
		formObj.attr("method", "get");
		formObj.submit();
	});

	$('#fileupBtn').on("click", function() {
		formObj.attr("action", "/fss/fileupdw/dailyBaseUpload");
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

