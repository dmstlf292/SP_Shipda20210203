<%@page import="forwarders.UtilMgr"%>
<%@page import="forwarders.MonthBean"%>
<%@page import="fmember.ForwardersMemberBean"%>
<%@ page  contentType="text/html; charset=EUC-KR"%>
<%@page import="member.UserDAO"%>
<%@page pageEncoding="EUC-KR"%>
<jsp:useBean id="mMgr" class="fmember.ForwardersMemberMgr"/>
<jsp:useBean id="mBean" class="fmember.ForwardersMemberBean"/>
<jsp:useBean id="month" class="forwarders.MonthMgr"/>
<%
		request.setCharacterEncoding("EUC-KR");
		String id = (String)session.getAttribute("idKey");
		if(id==null){
			//현재 접속된 url값
			StringBuffer url = request.getRequestURL();
			response.sendRedirect("../user/login.jsp?url="+url);
			return;//이후에 jsp 코드 실행 안됨.
		}
		ForwardersMemberBean fbean = mMgr.getMember(id);
		String name = mBean.getName();
		String email = mBean.getEmail();
		int no = UtilMgr.parseInt(request,"no");
		MonthBean priceBean = month.getMonth(no);
		int price = priceBean.getPrice();
%>
<!DOCTYPE html>
<html lang="en">
	
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>디지털 수입물류 포워딩 Ship-da</title>
	<link rel="stylesheet" href="assets/plugins/css/plugins.css">	
    <link href="assets/css/style.css" rel="stylesheet">
	<link href="assets/css/responsiveness.css" rel="stylesheet"><link id="jssDefault" rel="stylesheet" href="assets/css/skins/default.css">
	</head>
	<body>
	<nav class="navbar navbar-default navbar-mobile navbar-fixed light bootsnav">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-menu">
						<i class="fa fa-bars"></i>
					</button>
					<a class="navbar-brand" href="index.jsp">
						<img src="assets/img/logo.png" class="logo logo-display" alt="">
						<img src="assets/img/logo.png" class="logo logo-scrolled" alt="">
					</a>
				</div>
				<div class="collapse navbar-collapse" id="navbar-menu">
					<ul class="nav navbar-nav navbar-left" data-in="fadeInDown" data-out="fadeOutUp">
						<li>
							<a href="index.jsp" class="dropdown-toggle">Home</a>
						</li>
						<li class="dropdown megamenu-fw"><a href="#" class="dropdown-toggle" data-toggle="dropdown">업무의뢰 리스트</a>
							<ul class="dropdown-menu megamenu-content" role="menu">
								<li>
									<div class="row">
										<div class="col-menu col-md-3">
											<div class="content">
												<ul class="menu-col">
													<li><a href="boardFclView.jsp">FCL업무의뢰 리스트</a></li>
													<li><a href="boardLclView.jsp">LCL업무의뢰 리스트</a></li>
												</ul>
											</div>
										</div>
									</div>
								</li>
							</ul>
						</li>
						<li>
							<a href="pricing.jsp">Pricing</a>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<%
							if(id!=null){
						%>
						<li class="dropdown megamenu-fw"><a href="#" class="dropdown-toggle" data-toggle="dropdown">MyPage</a>
							<ul class="dropdown-menu megamenu-content" role="menu">
								<li>
									<div class="row">
										<div class="col-menu col-md-3">
											<div class="content">
												<ul class="menu-col">
													<li><a href="../forwarders/mypageFcl.jsp">My Page</a></li>
												</ul>
											</div>
										</div>
									</div>
								</li>
							</ul>
						</li>
						<li class="br-right"><a href="../user/userLogout.jsp" ><i class="login-icon ti-user"></i>Logout</a></li>
						<%}%>
					</ul>
				</div>
			</div>  
		</nav>
		<div class="page-title image-title" style="background-image:url(assets/img/banner.jpg);">
			<div class="container">
				<div class="page-title-wrap">
				<h2>Payment Method</h2>
				<p><a href="#" class="theme-cl">Home</a> | <span>Payment Method</span></p>
				</div>
			</div>
		</div>
		<section class="gray-bg">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="tr-single-box">
							<div class="tr-single-header">
								<h4><i class="ti-write"></i>Billing Information</h4>
							</div>
							<div class="tr-single-body">
								<div class="row">
									<div class="col-sm-6">
										<label>ID</label>
										<input type="text" class="form-control" name ="id" value="<%=fbean.getId()%>">
									</div>
									<div class="col-sm-6">
										<label>Name</label>
										<input type="text" class="form-control" name ="name" value="<%=fbean.getName()%>">
									</div>
									<div class="col-sm-12">
										<label>Email</label>
										<input type="email" class="form-control" name="email" value="<%=fbean.getEmail()%>">
									</div>
								</div>	
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="tr-single-box">
							<div class="tr-single-header">
								<h4><i class="ti-credit-card"></i>Payment Methode</h4>
							</div>
							<div class="tr-single-body">
								
								<div class="payment-card">
									<header class="payment-card-header cursor-pointer" data-toggle="collapse" data-target="#debit-credit" aria-expanded="true">
										<div class="payment-card-title flexbox">
											<h4>Credit / Debit Card</h4>
										</div>
										<div class="pull-right">
											<img src="assets/img/credit.png" class="img-responsive" alt=""> 
										</div>
									</header>
									<div class="collapse" id="debit-credit" aria-expanded="false">
									<div class="col-sm-6 padd-top-10 text-right">
													<label>Total Price</label>
													<h2 class="mrg-0"><span class="theme-cl">$</span><%=priceBean.getPrice() %></h2>
												</div>
										<div class="payment-card-body">
											<div class="row mrg-bot-20">
												<div class="col-sm-12 bt-1 padd-top-15">
													<button type="submit" onclick="location.href='payProc.jsp?price=<%=priceBean.getPrice()%>'"class="btn btn-m btn-success">Checkout</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<%@ include file="../index/footer.jsp" %>
		<button class="w3-button w3-teal w3-xlarge w3-right" onclick="openRightMenu()"><i class="spin theme-cl fa fa-cog" aria-hidden="true"></i></button>
		<div class="w3-sidebar w3-bar-block w3-card-2 w3-animate-right" style="display:none;right:0;" id="rightMenu">
		  <button onclick="closeRightMenu()" class="w3-bar-item w3-button w3-large theme-bg">Close &times;</button>
		   <ul id="styleOptions" title="switch styling">
				<li>
					<a href="javascript: void(0)" class="cl-box cl-default" data-theme="skins/default"></a>
				</li>
				<li>
					<a href="javascript: void(0)" class="cl-box cl-red" data-theme="skins/red"></a>
				</li>
				<li>
					<a href="javascript: void(0)" class="cl-box cl-green" data-theme="skins/green"></a>
				</li>
				<li>
					<a href="javascript: void(0)" class="cl-box cl-blue" data-theme="skins/blue"></a>
				</li>
				<li>
					<a href="javascript: void(0)" class="cl-box cl-pink" data-theme="skins/pink"></a>
				</li>
				<li>
					<a href="javascript: void(0)" class="cl-box cl-purple" data-theme="skins/purple"></a>
				</li>
			</ul>
		</div>
		<script src="assets/plugins/js/jquery.min.js"></script>
		<script src="assets/plugins/js/bootstrap.min.js"></script>
		<script src="assets/plugins/js/viewportchecker.js"></script>
		<script src="assets/plugins/js/bootsnav.js"></script>
		<script src="assets/plugins/js/slick.min.js"></script>
		<script src="assets/plugins/js/jquery.nice-select.min.js"></script>
		<script src="assets/plugins/js/jquery.fancybox.min.js"></script>
		<script src="assets/plugins/js/jquery.downCount.js"></script>
		<script src="assets/plugins/js/freshslider.1.0.0.js"></script>
		<script src="assets/plugins/js/moment.min.js"></script>
		<script src="assets/plugins/js/daterangepicker.js"></script>
		<script src="assets/plugins/js/wysihtml5-0.3.0.js"></script>
		<script src="assets/plugins/js/bootstrap-wysihtml5.js"></script>
		<script src="assets/plugins/js/jquery.slimscroll.min.js"></script>
		<script src="assets/plugins/js/jquery.metisMenu.js"></script>
		<script src="assets/plugins/js/jquery.easing.min.js"></script>
		<script src="assets/js/custom.js"></script>
		<script src="assets/js/jQuery.style.switcher.js"></script>
		<script>
			function openRightMenu() {
				document.getElementById("rightMenu").style.display = "block";
			}
			function closeRightMenu() {
				document.getElementById("rightMenu").style.display = "none";
			}
		</script>

		<script type="text/javascript">
			$(document).ready(function() {
				$('#styleOptions').styleSwitcher();
			});
		</script>
    </body>
</html>