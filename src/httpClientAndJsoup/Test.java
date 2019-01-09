package httpClientAndJsoup;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

public class Test {
	public static void main(String[] args) {
		BasicCookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie cookie = new BasicClientCookie("aaa", "bbb");
		cookie.setDomain(".mycompany.com");
		cookie.setPath("/");
		cookieStore.addCookie(cookie);
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://www.baidu.com");
		HttpClientContext context = new HttpClientContext();
		context.setCookieStore(cookieStore);
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get, context);
			System.out.println(response.getStatusLine().getStatusCode());//返回状态值
			Header[] headers = response.getAllHeaders();//获取所有的header信息
			boolean isContains = response.containsHeader("name");//是否包含ke为name的header
			ProtocolVersion version = response.getProtocolVersion();//获取协议版本
			// 使用响应对象获取响应实体
    	    HttpEntity entity = response.getEntity();
    	    //将响应实体转为字符串，此部分即为内容
            try {
			    String response2 = EntityUtils.toString(entity,"utf-8");
			    System.out.println(response2);
		    } catch (ParseException e) {
//			    log.error("获取响应内容转码失败，转码类型为：{}", "utf-8", e);
		   } catch (IOException e) {
//			    log.error("获取响应内容转码失败，转码类型为：{}", "utf-8", e);
		   }
		   //获取cookie
		   List<Cookie> returnCookie = context.getCookieStore().getCookies();
		} catch (ClientProtocolException e) {
			    e.printStackTrace();
		} catch (IOException e) {
			    e.printStackTrace();
		}finally 
		{
			try {
				response.close();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
