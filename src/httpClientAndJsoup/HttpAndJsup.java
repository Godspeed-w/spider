package httpClientAndJsoup;

import java.io.IOException;


import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HttpAndJsup {
	public static void main(String[] args) throws IOException {
//		String url ="https://music.163.com/#/song?id=494865824";
//		String url ="http://music.taihe.com/artist/1097";
		String url ="https://movie.douban.com/subject/7054120/";
//		String url = "https://www.google.com";
//		String url ="http://www.baidu.com";
//		String url = "https://blog.csdn.net/df0128/article/details/83043457";
//		String url ="http://baijiahao.baidu.com/s?id=1600097476284446498&wfr=spider&for=pc";
		
		// ����httpclient����
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//���ó�ʱʱ��
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(5000)
				.setConnectTimeout(5000)
				.build();
		// ����get����
		HttpGet get = new HttpGet(url);
		get.setConfig(requestConfig);
		
		// ����context����
		HttpClientContext context = HttpClientContext.create();
		// ����response����
		CloseableHttpResponse response = httpclient.execute(get, context);
		//��ȡ��ҳԴ��
		if(response.getStatusLine().getStatusCode()==200) {
			//��ȡʵ�����
			HttpEntity entity = response.getEntity();
			String res = EntityUtils.toString(entity, "utf-8");
//			for (int i = 0; i < res.length(); i++) {
//				if(i%500<=0&&i!=0) {
//					System.out.println(res.substring(i-500, i));
//				}
//			}
//			System.out.println(res);
			//ʹ��Jsoup������ҳ
			Document doc = Jsoup.parse(res);
			Elements elements = doc.select(".title");
			System.out.println(elements);
			
		}else {
			System.out.println("���������ӳ�ʱ");
		}
		response.close();
		httpclient.close();
	}
}
