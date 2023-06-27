package com.villon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SpringBootApplication
public class MarvelApiExample {
	
	private static final String PUBLIC_KEY = "d978be33fe77c5794da41d171d991ac3";
	private static final String PRIVATE_KEY = "c8a8fc7286e7d4f45682ffcca07be488ef3e496e";
	private static final String BASE_URL = "https://gateway.marvel.com:443/v1/public/";

	public static void main(String[] args) throws  IOException, NoSuchAlgorithmException{
		OkHttpClient client = new OkHttpClient();
	
    String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
	        String hash = md5(timeStamp + PRIVATE_KEY + PUBLIC_KEY);
	        String url = BASE_URL + "characters?ts=" + timeStamp + "&apikey=" + PUBLIC_KEY + "&hash=" + hash;

	        Request request = new Request.Builder()
	                .url(url)
	                .build();

	        Response response = client.newCall(request).execute();
	        System.out.println(response.body().string());
	    }

	    private static String md5(String s) throws NoSuchAlgorithmException {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(s.getBytes());
	        StringBuilder sb = new StringBuilder();
	        for (byte b : array) {
	            sb.append(String.format("%02x", b));
	        }
	        return sb.toString();
	    }
	}
		
		
		
		
				
		
	
