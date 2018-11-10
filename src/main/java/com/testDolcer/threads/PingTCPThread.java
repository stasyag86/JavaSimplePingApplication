package com.testDolcer.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.testDolcer.myTest.StoredResult;

public class PingTCPThread extends Thread{
	
	private final String USER_AGENT = "Mozilla/5.0";
	private String hostName;
	private int delay;
	
	public PingTCPThread(String hostName, int delay) {
		this.hostName = hostName;
		this.delay = delay;
	}
	
	@Override
	public void run() {
		String lastPingTCPResult = "";
			BufferedReader in = null;
			try{
				String url = hostName;
				if (hostName.contains("http")){
					int indexOfHttp = url.indexOf("://");
					hostName = url.substring(indexOfHttp + 3);
				}
				
				long startTime = System.currentTimeMillis();
				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();

				// optional default is GET
				con.setRequestMethod("GET");

				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);

				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'GET' request to URL : " + url);
				System.out.println("Response Code : " + responseCode);

				
				in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				long elapsedTime = System.currentTimeMillis() - startTime;
				lastPingTCPResult = hostName + " Response Time : " + elapsedTime + " mSec " + " Response Code :" + responseCode;
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				
				Thread.sleep(this.delay * 1000);
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				try {
					in.close();
					StoredResult.storePingTCPResult(hostName, lastPingTCPResult);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}
