<%@page contentType="text/html; charset=EUC-KR"%>
<%request.setCharacterEncoding("EUC-KR");%>
<jsp:useBean id="mMgr" class="fmember.ForwardersMemberMgr"/>
<jsp:useBean id="mBean" class="fmember.ForwardersMemberBean"/>
<jsp:setProperty property="*" name="mBean" />
<%
		boolean result = mMgr.insertMember(mBean);
		String msg = "가입실패";
		String url = "join.jsp";
		if(result){
			msg = "가입성공";
			url = "../forwarders/index.jsp";
			//가입성공과 동시에 로그인 처리를 위한 기능
			session.setAttribute("idKey", mBean.getId());
		}
%>
<script>
	alert("<%=msg%>");
	location.href = "<%=url%>";
</script>











