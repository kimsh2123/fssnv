<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/dfg_include.jsp"%>

<html>
<head>
	<title>AG113</title>
</head>
<body>
<!-- 내용 작성 할 부분 START  -->
<form role="form" method="post">
<div id="page-inner">
    <div class="row">
        <div class="panel panel-default">
            <div class="col-md-10">
            <h1 class="page-head-line">상품주식 거래 내역 [AG113]</h1>
            </div>
            <div class="col-md-2">
            <c:if test="${not empty login}">USER [ ${login.user_name} ]</c:if>
            </div>
            
            <div class="col-md-12">
			작성 기준일자 : <input type="text" maxlength="8" size="8" name="make_date" id="make_date" value="${make_date}">
            
            <button type="button" class="btn btn-primary" id='searchBtn' name='search'>SEARCH</button>
            <button type="button" class="btn btn-success" id='newBtn'>Register</button>
            <button type="button" class="btn btn-info" id='fileupBtn'>File Upload</button>
            <button type="button" class="btn btn-danger" id='delallBtn'>Del All</button>
            </div>
            
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr class="info">
                                <th>#번호</th>
                                <th>종목명</th>
                                <th>거래구분</th>
                                <th>거래일자</th>
                                <th>등록일</th>
                            </tr>
                        </thead>
                        <tbody>
                        
						<c:forEach items="${list}" var="Ag113VO">

							<tr>
								<td><a href="#" onclick="popupModal('${Ag113VO.num}', '${Ag113VO.trade_date}')" class="btn btn-primary btn-xs">${Ag113VO.num}</a></td>
								<td><a href='/fss/daily/ag113Modify${pageMaker.makeQuery(pageMaker.cri.page)}&num=${Ag113VO.num}&make_date=${Ag113VO.make_date}'> ${Ag113VO.issu_name} </a></td>
								<td>${Ag113VO.trade_type}</td>
								<td>${Ag113VO.trade_date}</td>
								<td>${Ag113VO.up_date}</td>
							</tr>

						</c:forEach>                        
                        
                        <c:if test="${list.size() == 0}">
                        	<tr>
								<td colspan=5 class="danger" align="center">No Data~!</td>
							</tr>
                        </c:if>

                        </tbody>
                    </table>
                </div>
            </div>
            
            <div class="box-footer">
			<div class="text-center">
				<ul class="pagination">

					<c:if test="${pageMaker.prev}">
						<li><a href="/fss/daily/ag113List${pageMaker.makeQuery(pageMaker.startPage-1)}&make_date=${make_date}">&laquo;</a></li>
					</c:if>

					<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
						<li	<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
							<a href="/fss/daily/ag113List${pageMaker.makeQuery(idx)}&make_date=${make_date}">${idx}</a>
						</li>
					</c:forEach>

					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<li><a href="/fss/daily/ag113List${pageMaker.makeQuery(pageMaker.endPage+1)}&make_date=${make_date}">&raquo;</a></li>
					</c:if>

				</ul>
			</div>
            </div>
            
            <div class="col-md-8">
            <p>
            <button type="button" class="btn btn-info btn-xs" id='excelBtn'>XLS DOWN</button>
            <button type="button" class="btn btn-primary btn-xs" id='excelXBtn'>XLSX DOWN</button>
            <button type="button" class="btn btn-success btn-xs" id='csvXBtn'>CSV DOWN</button>
            <button type="button" class="btn btn-danger btn-xs" id='pdfBtn'>PDF DOWN</button>
            </p>
            </div>
            
            <div class="col-md-8">
            <p>
            <button type="button" class="btn btn-danger btn-xs" id='popupBtn'>POP UP(get)</button>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal">Modal Popup</button>
            </p>
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
		formObj.attr("action", "/fss/daily/ag113List");
		formObj.attr("method", "get");
		formObj.submit();
	});

	$('#newBtn').on("click", function() {
		self.location = "ag113Register";
	});
	
	$('#fileupBtn').on("click", function() {
		$(location).attr("href","/fss/fileupdw/fileupload?menu_id=ag113List");
	});
	
	$('#delallBtn').on("click", function() {
		var make_date = $("#make_date").val();
		$(location).attr("href", "/fss/daily/ag113DelAll?make_date="+make_date);
	});
	
	$('#excelBtn').on("click", function() {
		var make_date = $("#make_date").val();
		$(location).attr("href", "/fss/daily/ag113XlsDown?make_date="+make_date);
	});
	
	$('#excelXBtn').on("click", function() {
		var make_date = $("#make_date").val();
		$(location).attr("href", "/fss/daily/ag113XlsXDown?make_date="+make_date);
	});
	
	$('#csvXBtn').on("click", function() {
		var make_date = $("#make_date").val();
		$(location).attr("href", "/fss/daily/ag113CsvDown?make_date="+make_date);
	});
	
	$('#pdfBtn').on("click", function() {
		var make_date = $("#make_date").val();
		$(location).attr("href", "/fss/daily/ag113PdfDown?make_date="+make_date);
	});
	
	// get 방식으로 popup으로 파라미터 넘겨서 띄우기 16.7.19
	$('#popupBtn').on("click", function() {
		var make_date = $("#make_date").val();
		window.open("/fss/daily/ag113Popup?make_date="+make_date,"_blank"
				,"toolbar=yes,menubar=yes,width=700,height=500").focus();
	});
	
	// modal popup detail
	popupModal = function(num, make_date){
		// prevent the form from submitting via the browser
		event.preventDefault();
		
		$.getJSON("/fss/ag113Rest/"+num+"/"+make_date, function(data) {
			
			$("#myModal").modal('show'); //modal popup open
			
			$("#popup_make_date").html(data.popupData.make_date);
			$("#popup_comp_code").html(data.popupData.comp_code);
			$("#popup_issues_no").html(data.popupData.issues_no);
		});
	}//end of popupModal
	
});

</script>


<!-- Modal popup -->
<div class="modal fade" id="myModal" role="dialog">
<div class="modal-dialog">
<!-- CONTENT -->
<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<h4 class="modal-title">Modal</h4>
		<div class="modal-body">
		
		<!-- popup 내용 start -->
		<div id="page-inner">
		    <div class="row">
		        <div class="panel panel-default">
		            <div class="col-md-12">
		            <h1 class="page-head-line">[AG113] Pop up</h1>
		            <br>
		            <h1 class="form-control">DATE : <span id="popup_make_date"></span></h1>
		            </div>
				</div>
			</div>
			
			<div class="row">
		        <div class="panel panel-default">
		            <div class="col-md-12">
		            COMP CODE : <span id="popup_comp_code"></span>
		            <br>
		            ISSUES NO : <span id="popup_issues_no"></span>
		            </div>
				</div>
			</div>
		</div>
		<!-- popup 내용 end -->
		
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>
	</div>
</div>
</div>
</div>


</body>
</html>

<script>
var result = '${msg}';
var result2 = "${count}"
if (result == 'SUCCESS') {
	if(result2 > 0){
		alert("["+result2+"]건 처리가 완료되었습니다.");	
	} else {
		alert("처리가 완료되었습니다.");
	}
}
</script>

