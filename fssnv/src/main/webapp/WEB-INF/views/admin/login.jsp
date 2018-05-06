<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%@include file="../include/dfg_include.jsp"%>

<head>
<title>Login</title>

</head>
<body style="background-color: #E2E2E2;">
	<div class="container">
		<div class="row text-center " style="padding-top: 100px;">
			<div class="login-logo">
				<b>FSS</b>
			</div>
		</div>
		<div class="row ">

			<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">

				<div class="panel-body">
					<form role="form" method="post">
						<hr />
						<h5>Enter Details to Login</h5>
						<br />
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-tag"></i></span>
							<input type="text" class="form-control"	placeholder="User ID" name="user_id"/>
						</div>
						<div class="form-group input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input type="password" class="form-control"	placeholder="Password" name="user_passwd"/>
						</div>
						<div class="form-group">
							<span class="pull-right"> 
								<a href="#" onclick="popupModal()">Forget password ? </a>
							</span>
						</div>

						<button type="submit" id="loginBtn" class="btn btn-primary">Login Now</button>
						<hr />
					</form>
				</div>

			</div>

		</div>
	</div>
	

<!-- Modal start -->
<div class="modal fade" id="myModal" role="dialog">
<div class="modal-dialog">
<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<h4 class="modal-title">Password Search</h4>
	</div>
	<div class="modal-boby">
		USER NAME : <input type="text" size="10" class="form-control" placeholder="User Name" id="search_input">
		<br>
		PASS WORD : <span id="popup_password"></span>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-primary" id='searchBtn'>SEARCH</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	</div>
</div>
</div>
</div>
<!-- Modal end -->	
	
	
<script>
$(document).ready(function() {
	
	var formObj = $("form[role='form']");
	
	$("#loginBtn").on("click", function() {
		formObj.attr("action", "/fss/admin/loginPost");
		formObj.attr("method", "post");
		formObj.submit();
	});
	
	// modal popup detail 16.08.09
	popupModal = function(){
		event.preventDefault();
		// 변수 초기화.. 이 방법밖엔?
		$("#search_input").val('');
		$("#popup_password").html('');
		
		$("#myModal").modal('show');
	}//end of popupModal
	
	// password search button 16.08.09
	$("#searchBtn").on("click", function() {
		// prevent the form from submitting via the browser
		event.preventDefault();
		
		var search_input = $("#search_input").val();
		
		// 이렇게하면되긴하는데 json으로 안되는데 16.08.10
		$.ajax({
			type : 'get',
			contentType : "application/json",
			url : '/fss/admin/searchPwd/',
			data : {search_input : search_input},
			dataType : 'text',
			timeout : 100000,
			success : function(result) {
				console.log("result: " + result);
				if(result == ''){
					result = "No Match~!";
				}
				$("#popup_password").html(result);
			}
		});
	});

});
</script>

</body>
</html>
