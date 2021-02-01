<%@page import="forwarders.UtilMgr"%>
<%@ page  contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="quotationMgr" class="forwarders.FclQuotationMgr"/>
<jsp:useBean id="fqbean" class="forwarders.FclQuotationBean"/>
<!-- 여기 계속 사용해왔던 테이블 bean을 꺼내서 사용하려면 setProperty 세팅 무조건 해줘야함 -->
<jsp:setProperty property="*" name="fqbean"/>
<%
		request.setCharacterEncoding("EUC-KR");
		String flag = request.getParameter("flag");
		int pickupRate = Integer.parseInt(request.getParameter("pickupRate"));
		String no = request.getParameter("no");
		System.out.println("proc체크:"+no);
		
		System.out.println("출력테스트1"+pickupRate);
		
		fqbean.setPickupRate(pickupRate);
		
		System.out.println("bean값 체크"+fqbean.getPickupRate());	
		
		//fclReply 인설트
		boolean result= quotationMgr.insertFclQuotation(fqbean);
		
		String msg="견적 제출 실패";//오류
		String url="fclReply.jsp";
		if(result){
			msg="등록성공";
			url="mypage.jsp?no="+no;
		}

%>
<script>
	alert("<%=msg%>");
	location.href = "<%=url%>";
</script>