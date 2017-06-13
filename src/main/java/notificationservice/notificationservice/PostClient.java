package notificationservice.notificationservice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


import com.google.gson.Gson;
import notificationservice.notificationservice.input.RegisterUserInput;

public class PostClient {

	/**
	 * This class is responsible for the logic of the post method for remote rest service integration. It can be used for both to reach the pushbullet.com API or to test our own rest service functionality.
	 * */
	public HttpResponse restPostExecute(String url,Map<String,String> headers, Object postInput){
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		Gson gson = new Gson();
		StringEntity entity=null;
		try {
			entity = new StringEntity( gson.toJson(postInput));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		post.setEntity(entity);
		Iterator<Entry<String,String>> iter = headers.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String,String> entry = iter.next();
			post.addHeader(entry.getKey(), entry.getValue());
		}
		HttpResponse response =null;
		try {
			response = client.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
