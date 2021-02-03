<%@page import="forwarders.UtilMgr"%>
<%@ page  contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="quotationMgr" class="forwarders.LclQuotationMgr"/>
<jsp:useBean id="lqbean" class="forwarders.LclQuotationBean"/>
<!-- 여기 계속 사용해왔던 테이블 bean을 꺼내서 사용하려면 setProperty 세팅 무조건 해줘야함 -->
<jsp:setProperty property="*" name="lqbean"/>
<%
		request.setCharacterEncoding("EUC-KR");
		String flag = request.getParameter("flag");
		String no = request.getParameter("no");
		
		int pickupRate = Integer.parseInt(request.getParameter("pickupRate"));
		System.out.println("proc체크:"+no);
		System.out.println("출력테스트1"+pickupRate);
	
		lqbean.setPickupRate(pickupRate);
		
		System.out.println("bean값 체크"+lqbean.getPickupRate());	
		
		//fclReply 인설트
		boolean result= quotationMgr.insertLclQuotation(lqbean);
		
		String msg="견적 제출 실패";//오류
		String url="lclReply.jsp";
		if(result){
			msg="등록성공";
			//여기 뒤에 no 추가
			url="mypageLcl.jsp?no="+no;
		}

%>
<script>
	alert("<%=msg%>");
	location.href = "<%=url%>";
</script>