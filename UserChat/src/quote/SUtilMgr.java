package quote;


import java.util.*;

import javax.servlet.http.HttpServletRequest;

import java.text.*;

public class SUtilMgr {//맨첨에 사용하려고 했던 함수(우리 쇼핑몰할때 썻던거)

	 public static String getContent(String comment){
        return br("", "<br/>", comment);
   }

  public static String br(String first, String brTag, String comment){
        StringBuffer buffer = new StringBuffer();
        StringTokenizer st = new StringTokenizer(comment, "\n");
        int count = st.countTokens();
        buffer.append(first);
        int i = 1;
         while(st.hasMoreTokens()){ 
           if(i==count){
             buffer.append(st.nextToken());
           }else{ 
             buffer.append(st.nextToken()+ brTag);
		   }
           i++;
         }
       return buffer.toString(); 
  } 

  public static String monFormat(String b){//여기 바꿨었음 이름만ㅋㅋㅋ
         String cbm;
		 double bb = Double.parseDouble(b); 
		 cbm = NumberFormat.getIntegerInstance().format(bb);
		 return cbm;
  }
  public static String monFormat(int b){
         String cbm;
		 double bb = b;
		 cbm = NumberFormat.getIntegerInstance().format(bb);
		 return cbm;
  }
  public static String intFormat(int i){
         String s = String.valueOf(i);
		 return monFormat(s);		  
  }
  
  //2009. 9. 28
  public static String getDay(){
	  Date now = new Date();
	  DateFormat df = DateFormat.getDateInstance();
	  return df.format(now).toString();
  }
  
	public static int parseInt(HttpServletRequest request, 
			String name) {
		return Integer.parseInt(request.getParameter(name));
	}
}