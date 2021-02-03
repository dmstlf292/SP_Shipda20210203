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
		//인티저 변환 해야함(주소창에 있는 값들은 모두 int->string 형으로 변환해줘야 읽힌다)
		String no = request.getParameter("no");
		System.out.println("proc체크:"+no);
		System.out.println("출력테스트1"+pickupRate);
		
		//빈에 담겨있는 PickupRate 값을 세팅해서, get으로 가져오기
		fqbean.setPickupRate(pickupRate);
		System.out.println("bean값 체크"+fqbean.getPickupRate());	
		
		//fclReply 인설트
		boolean result= quotationMgr.insertFclQuotation(fqbean);
		
		String msg="견적 제출 실패";//오류
		String url="fclReply.jsp";
		if(result){
			msg="등록성공";
			url="mypageFcl.jsp?no="+no;
		}
		//usd 출력테스트
		String getUsd=request.getParameter("usd");
		System.out.println("usd출력테스트 :" + fqbean.getUsd());//0으로 뜬다.
		
%>
<script>
	alert("<%=msg%>");
	location.href = "<%=url%>";
</script>