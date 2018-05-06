<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="./include/dfg_include.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8">
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
                	<a href="/fss/admin/logout" class="btn btn-danger btn-block">Log Out</a>
                </c:if>
                <c:if test="${empty login}">
                	<a href="/fss/admin/login" class="btn btn-warning btn-block">Login Please</a>
                </c:if>
        	</div>
        </div>
		</li>

		<li>
         	<a href='/fss/mainPage' target="f_main"><i class="fa fa-dashboard"></i>Home Call</a>
		</li>


<!-- MENU START -->
		<c:forEach items="${menuList}" var="MenuVO">
		
		<c:choose>
		
			<c:when test="${MenuVO.menu_code_2 eq 0 && MenuVO.menu_code_3 eq 0}">
				<c:if test="${mtmp_2 > 0}">
				   </ul>  
				  </li>
				 </ul>  
				</li>
				</c:if>
				
				<li>
					<a href="#"><i class="fa fa-desktop "></i> ${MenuVO.menu_name}<span class="fa arrow"></span></a>  
					<ul class="nav nav-second-level">
			</c:when>
			
			<c:when test="${MenuVO.menu_code_2 > 0 && MenuVO.menu_code_3 eq 0}">
				<c:if test="${mtmp_2 > 0 && MenuVO.menu_type eq ''}">
				 </ul>  
				</li>
				</c:if>
				
				<c:if test="${MenuVO.menu_type > '' && MenuVO.menu_type != 'A'}">
				</ul>  
				</li>
					<li>
		        		<a href="${MenuVO.menu_uri}" target="f_main"><h6><i class="fa fa-circle-o "></i> ${MenuVO.menu_name}</h6></a>
		        	</li>
		        <ul>
		        <li>			
				</c:if>
				
				<c:if test="${MenuVO.menu_type eq 'A'}">
					<li>
		        		<a href="${MenuVO.menu_uri}" target="f_main"><h6><i class="fa fa-circle-o "></i> ${MenuVO.menu_name}</h6></a>
		        	</li>
				</c:if>
				
				<c:if test="${MenuVO.menu_type eq ''}">
					<li>
						<a href="#"><i class="fa fa-desktop "></i> ${MenuVO.menu_name}<span class="fa arrow"></span></a>  
						<ul class="nav nav-second-level">				
				</c:if>
			</c:when>
		
			<c:when test="${MenuVO.menu_code_2 > 0 && MenuVO.menu_code_3 > 0}">
				<li>
	        		<a href="${MenuVO.menu_uri}" target="f_main"><h6><i class="fa fa-circle-o "></i> ${MenuVO.menu_name}</h6></a>
	        	</li>
			</c:when>
			
		</c:choose>
		
		<c:set var="mtmp_2" value="${MenuVO.menu_code_2}" />
		
		</c:forEach>

          
		 </ul>  
		</li>
<!-- MENU END -->
          
	</ul>
</div>

</nav>
<!-- /. NAV SIDE  -->
 
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
			<iframe name="f_main" id="f_main" src="/fss/mainPage" width="100%" height="100%" 
			frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" allowtransparency="true"></iframe>
            </div>
        </div>
    </div>
</div>
 
</div>		

</body>

</html>