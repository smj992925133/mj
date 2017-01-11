package com.example.administrator.hday1_02;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpUtils {

	/**
	 * 通过给定的url得到该url所对应的字节数组（可以用于图片，视频等流文件）
	 * @param url
	 * @return
	 */
	public static byte[] getBytesByUrl(String url){
		byte[] data = null;
		
		OkHttpClient client = new OkHttpClient();
		
		Request.Builder builder = new Request.Builder();
		Request request = builder.url(url).build();
		try {
			Response response = client.newCall(request).execute();
			if(response.isSuccessful()){
				data = response.body().bytes();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * 通过给定的url得到该url所对应的字符串（可以用于json字符串）
	 * @param url
	 * @return
	 */
	public static String getJSONStringByUrl(String url){
		String data = null;
		
		OkHttpClient client = new OkHttpClient();
		
		Request.Builder builder = new Request.Builder();
		Request request = builder.url(url).build();
		try {
			Response response = client.newCall(request).execute();
			if(response.isSuccessful()){
				data = response.body().string();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	/**
	 * 通过给定的url得到该url所对应的流对象（可以用于文件解析）
	 * @param url
	 * @return
	 */
	public static InputStream getInputStreamByUrl(String url){
		InputStream data = null;
		
		OkHttpClient client = new OkHttpClient();
		
		Request.Builder builder = new Request.Builder();
		Request request = builder.url(url).build();
		try {
			Response response = client.newCall(request).execute();
			if(response.isSuccessful()){
				data = response.body().byteStream();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
}
