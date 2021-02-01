package chatting;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/chatting/chatListt")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		response.setContentType("text/html; charset=EUC-KR");
		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String listType = request.getParameter("listType");
		if(fromID == null || fromID.equals("")||toID == null || toID.equals("null") || toID.equals("")||listType == null || listType.equals("")){
			response.getWriter().write("");
			
		}else if (listType.equals("ten")){
			response.getWriter().write(getTen(URLDecoder.decode(fromID,"UTF-8"),URLDecoder.decode(toID,"UTF-8")));
	   }else {
		   try {
			   HttpSession session = request.getSession();
			   if(!URLDecoder.decode(fromID,"EUC-KR").equals((String)session.getAttribute("userID"))){
				   response.getWriter().write("");
				   //System.out.println("디버깅");
				   return;
			   }
				response.getWriter().write(getID(URLDecoder.decode(fromID,"UTF-8"),URLDecoder.decode(toID,"UTF-8"),listType));
		} catch (Exception e) {
			response.getWriter().write("");
		}
	   }
	}
	public String getTen(String fromID,String toID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO  chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(fromID, toID, 100);
		if(chatList.size()==0) return "";
		for(int i = 0 ; i <chatList.size(); i++) {
			result.append("[{\"value\": \""+ chatList.get(i).getFromID() +"\"},");
			result.append("{\"value\": \""+ chatList.get(i).getToID() +"\"},");
			result.append("{\"value\": \""+ chatList.get(i).getChatContent() +"\"},");
			result.append("{\"value\": \""+ chatList.get(i).getChatTime() +"\"}]");
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\""+ chatList.get(chatList.size()-1).getChatID()+"\"}");
		//채팅 읽음 메소드
		chatDAO.readChat(fromID, toID);
		return result.toString();
	}
	public String getID(String fromID,String toID,String chatID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO  chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByID(fromID, toID, chatID);
		//System.out.println("서블릿 메소드 체크: " + chatList.get(0).getFromID());
		if(chatList.size()==0) return "";
		for(int i = 0 ; i <chatList.size(); i++) {
			result.append("[{\"value\": \""+ chatList.get(i).getFromID() +"\"},");
			result.append("{\"value\": \""+ chatList.get(i).getToID() +"\"},");
			result.append("{\"value\": \""+ chatList.get(i).getChatContent() +"\"},");
			result.append("{\"value\": \""+ chatList.get(i).getChatTime() +"\"}]");
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\""+ chatList.get(chatList.size()-1).getChatID()+"\"}");
		chatDAO.readChat(fromID, toID);
		return result.toString();
	} 
}
