<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/dfg_include.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>FSS</title>
</head>

<body>
<!-- /. NAV SIDE  -->
<div id="page-inner">
	<div class="row">
		<div class="col-md-12">
        	<h1 class="page-head-line">상품주식 거래 내역 [AG113]</h1>
        </div>
    </div>

<form role="form" method="post">
					
	<div class="row">
    	<div class="col-md-12 col-sm-12 col-xs-12">
        	<div class="panel panel-info">
        	
            	<div class="panel-heading"> 
					<button type="submit" id="goRegistryBtn" class="btn btn-success">REGISTER</button>
					<button type="submit" id="goGoListBtn" class="btn btn-primary">GO LIST</button>
					
					<input type=hidden name='up_id' value='${login.user_id}'>
 				</div>
 				
				<div class="panel-body">
                        
                	<div class="col-md-6 col-sm-6 col-xs-6">
                    	<div class="form-group">
                       	<label>작성 기준일자</label> 
                        <input class="form-control" type="text" maxlength="8" name="make_date">
                        </div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
                    	<div class="form-group">
                       	<label>발생사 코드</label> 
                        <input class="form-control" type="text" maxlength="5" name="publish_code">
                    	</div>
					</div>
						
					<div class="col-md-6 col-sm-6 col-xs-6">
                    	<div class="form-group">
                       	<label>종목 코드</label> 
                        <input class="form-control" type="text" maxlength="12" name="issues_no">
                        </div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
                    	<div class="form-group">
                       	<label>종목명 </label> 
                        <input class="form-control" type="text" maxlength="40" name="issu_name">
                        </div>
					</div>
					
					<div class="col-md-6 col-sm-6 col-xs-6">
                    	<div class="form-group">
						<label>증권사와 발행사와의 관계</label> 
						<select class="form-control" name="publish_rel">
						<c:forEach items="${codelist_1}" var="CodeVO">
							<option value='${CodeVO.param}'>${CodeVO.p_name}</option>
						</c:forEach>
						</select>
                        </div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
                    	<div class="form-group">
						<label>거래구분</label> 
						<select class="form-control" name="trade_type">
						<c:forEach items="${codelist_2}" var="CodeVO">
							<option value='${CodeVO.param}'>${CodeVO.p_name}</option>
						</c:forEach>							
						</select>
                        </div>
					</div>			
					
					<div class="col-md-6 col-sm-6 col-xs-6">
                    	<div class="form-group">
                       	<label>거래 일자</label> 
                       	<input class="form-control" type="text" maxlength="8" name="trade_date">
                        </div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
                    	<div class="form-group">
						<label>주문 시각</label> 
						<input class="form-control" type="text" maxlength="6" name="ordr_time">
                        </div>
					</div>					
					
					<div class="col-md-6 col-sm-6 col-xs-6">
                    	<div class="form-group">
                       	<label>주문 수량</label> 
                       	<input class="form-control" type="text" maxlength="19" name="ordr_qty">
                        </div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
                    	<div class="form-group">
						<label>주문 단가</label> 
						<input class="form-control" type="text" maxlength="19" name="ordr_price">
                        </div>
					</div>
					
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>정정 회수</label> 
						<input class="form-control" type="text" maxlength="19" name="amd_rtn">
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>정정 수량</label> 
						<input class="form-control" type="text" maxlength="19" name="amd_qty">
						</div>
					</div>												
	
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>최종취소시각</label> 
						<input class="form-control" type="text" maxlength="6" name="last_ccl_time">
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>최종체결시각</label> 
						<input class="form-control" type="text" maxlength="6" name="last_cntr_time">
						</div>
					</div>
					
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>취소 회수</label> 
						<input class="form-control" type="text" maxlength="19" name="ccl_rtn">
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>취소 수량</label> 
						<input class="form-control" type="text" maxlength="19" name="ccl_qty">
						</div>
					</div>	
					
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>체결 수량</label> 
						<input class="form-control" type="text" maxlength="19" name="cntr_qty">
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>체결 단가</label> 
						<input class="form-control" type="text" maxlength="19" name="cntr_price">
						</div>
					</div>
					
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>장부가액</label> 
						<input class="form-control" type="text" maxlength="19" name="book_value">
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>보유수량/거래후</label> 
						<input class="form-control" type="text" maxlength="19" name="holding_qty">
						</div>
					</div>
	
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>증권사와 상대방과의 관계</label> 
						<select class="form-control" name="cpart_rel">
						<c:forEach items="${codelist_1}" var="CodeVO">
							<option value='${CodeVO.param}'>${CodeVO.p_name}</option>
						</c:forEach>
						</select>
						</div>
					</div>
	
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>시장조성관련 매매여부</label> 
						<select class="form-control" name="market_mature">
						<c:forEach items="${codelist_3}" var="CodeVO">
							<option value='${CodeVO.param}'>${CodeVO.p_name}</option>
						</c:forEach>
						</select>
						</div>
					</div>
	
					<div class="col-md-6 col-sm-6 col-xs-6">
						<div class="form-group">
						<label>상대방</label> 
						<input class="form-control" type="text" maxlength="20" name="counterpart">
						</div>
					</div>					
						
                </div>
             </div>
		</div>
	</div>					
</form>
					
<script>
$(document).ready(function() {

	var formObj = $("form[role='form']");

	console.log(formObj);

	$("#goRegistryBtn").on("click", function() {
		formObj.attr("action", "/fss/daily/ag113Register");
		formObj.attr("method", "post");
		formObj.submit();
	});

	$("#goGoListBtn").on("click", function() {
		formObj.attr("action", "/fss/daily/ag113List");
		formObj.attr("method", "get");
		formObj.submit();
	});

});
</script>					

</div>
</body>
</html>


