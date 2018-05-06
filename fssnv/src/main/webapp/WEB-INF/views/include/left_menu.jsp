<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<%@include file="../include/dfg_include.jsp"%>
 
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>FSS</title>
</head>
<body>
<div id="wrapper">
<!-- /. NAV TOP  -->
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li>
                <div class="user-img-div">
                    <div class="inner-text" align="center">
                    <c:if test="${not empty login}">
                    	LOGIN [ ${login.user_name} ] 
                    </c:if>
                    <c:if test="${empty login}">
                    	<a href="/fss/admin/login" class="btn btn-warning btn-block">Login Please</a>
                    </c:if>
                    </div>
                </div>
            </li>

            <li>
            <!-- 페이지 넘기기 test -->
             <a href='/fss/mainPage'><i class="fa fa-dashboard"></i>Home Call</a>
             </li>
             
             
             <li>
                 <a href="#"><i class="fa fa-desktop "></i>일보고<span class="fa arrow"></span></a>  
                     <ul class="nav nav-second-level">
                 	<li>
                         <a href="/fss/mainPage"><i class="fa fa-send "></i>사용자입력<span class="fa arrow"></span></a>
                         <ul class="nav nav-second-level">
                   	<li>
                           <a href="/fss/mainPage"><h6><i class="fa fa-circle-o "></i> 채권인수내역 ag132</h6></a>
                       </li>
                       <li>
                           <a href="/fss/mainPage"><h6><i class="fa fa-circle-o "></i> 자금거래내역 ag172</h6></a>
                       </li>
						<li>
                           <a href="/fss/mainPage"><h6><i class="fa fa-circle-o "></i> 특정금전신탁 거래내역 ag162</h6></a>
                       </li>
                   </ul> 
                     </li>
                     <li>
                         <a href="/fss/mainPage"><i class="fa fa-send "></i>KOSCOM 제공<span class="fa arrow"></span></a>
                         <ul class="nav nav-second-level">
                   	<li>
                           <a href="/fss/mainPage"><h6><i class="fa fa-circle-o "></i> Base File Upload</h6></a>
                       </li>
                       <li>
                           <a href="/fss/daily/ag113List"><h6><i class="fa fa-circle-o "></i> 상품주식 거래 내역 ag113</h6></a>
                       </li>
						<li>
                           <a href="/fss/mainPage"><h6><i class="fa fa-circle-o "></i> 위탁자 대량 매매 ag115</h6></a>
                       </li>
                   </ul> 
                     </li>
					<li>
                         <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>종합</a>
                     </li>
                 	</ul>  
             </li>
             
             
             <li>
                 <a href="#"><i class="fa fa-desktop "></i>월보고<span class="fa arrow"></span></a>  
                     <ul class="nav nav-second-level">
                 	<li>
                         <a href="/fss/mainPage"><i class="fa fa-send "></i>사용자입력<span class="fa arrow"></span></a>
                         <ul class="nav nav-second-level">
                   	<li>
                           <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>역외펀드 자산운용 내역 ag118</a>
                       </li>
                       <li>
                           <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>상품 외화 증권 인수 및 매매내역 ag119</a>
                       </li>
                   </ul> 
                     </li>
                     <li>
                         <a href="/fss/mainPage"><i class="fa fa-send "></i>KOSCOM 제공<span class="fa arrow"></span></a>
                         <ul class="nav nav-second-level">
                   	<li>
                           <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>Base File Upload</a>
                       </li>
						<li>
                           <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>선물옵션 손실과다 계좌현황 ag145</a>
                       </li>		                            
                       <li>
                           <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>점포별특이계좌 내역 ag112</a>
                       </li>
                   </ul> 
                     </li>
						<li>
                         <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>종합</a>
                     </li>
                 	</ul>  
             </li>
             
             
             <li>
                 <a href="#"><i class="fa fa-desktop "></i>ADMIN<span class="fa arrow"></span></a>  
                 <ul class="nav nav-second-level">
                 	<li>
                         <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>사용자 권한부여</a>
                     </li>
                     <li>
                         <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>사용자 추가</a>
                     </li>
                     <li>
                         <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>사용자 수정/삭제</a>
                     </li>
                     <li>
                         <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>비밀번호 변경</a>
                     </li>
                     <li>
                         <a href="/fss/mainPage"><i class="fa fa-circle-o "></i>사용자 정보</a>
                     </li>                                                                                    
                 </ul> 
             </li>
             
         </ul>
     </div>

 </nav>
 <!-- /. NAV SIDE  -->
</div>

</body>
</html>   