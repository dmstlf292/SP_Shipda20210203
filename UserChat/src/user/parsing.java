package user;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class ApiConnection{
		//파싱할 최종 데이터 담아주기 위한 문자열
		String json;
		
		ApiConnection() throws Exception{
		//api 주소
		String address = "http://api.currencylayer.com/live?access_key=d0a0b6fc03ef106cd7a526ffa378e0d7";
		BufferedReader br;
		URL url;
		HttpURLConnection conn;
		String protocol = "GET";

		url = new URL(address);
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		json = br.readLine();
	}
}

public class parsing {
	public static void main(String[] args) throws Exception {
		ApiConnection wc = new ApiConnection();
		
		//json 문자열 데이터
		String json = wc.json;
		JSONParser parser = new JSONParser();
		//json형식의 문자열을 json객체로 parsing
		JSONObject obj = (JSONObject)parser.parse(json);
		//Object
		JSONObject quotes = (JSONObject)obj.get("quotes");
		//Object key값 지정 : 밸류 산출 
		Object USEAED = quotes.get("USDKRW");
		System.out.println("원달러 : "+USEAED);
		}
	}
